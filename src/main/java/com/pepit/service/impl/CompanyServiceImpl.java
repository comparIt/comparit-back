package com.pepit.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.cj.xdevapi.*;
import com.pepit.constants.TypeModelPropertyEnum;
import com.pepit.converters.CompanyConverter;
import com.pepit.dto.CompanyDto;
import com.pepit.exception.DataProvidedException;
import com.pepit.exception.InputException;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Company;
import com.pepit.model.Model;
import com.pepit.model.WebsiteConfiguration;
import com.pepit.repository.CompanyRepository;
import com.pepit.repository.ProductRepositoryCustom;
import com.pepit.service.CompanyService;
import com.pepit.service.WebsiteConfigurationService;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService {

    private ProductRepositoryCustom productRepository;
    private WebsiteConfigurationService websiteConfigurationService;
    private CompanyRepository companyRepository;
    private CompanyConverter companyConverter;


    @Autowired
    public CompanyServiceImpl(ProductRepositoryCustom productRepository, WebsiteConfigurationService websiteConfigurationService, CompanyRepository companyRepository, CompanyConverter companyConverter) {
        this.productRepository = productRepository;
        this.websiteConfigurationService = websiteConfigurationService;
        this.companyRepository = companyRepository;
        this.companyConverter = companyConverter;
    }

    public String fromUrlToDb(String url, String supplierId, String type) {
        log.info("DEB fromUrlToDb");
        List<DbDoc> dbDocList = new ArrayList<>();
        try {
            //Ajout d'un header http
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            // TODO : générer des headers
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode parsedArray = mapper.readTree(response.getBody());
            Model model = null;

            //pour tous les elements retournés par l'API on construit l'objet json enrichi
            for (JsonNode parsedJson : parsedArray) {
                DbDoc outerObject = updateJsonNode(supplierId, type, parsedJson, model);
                dbDocList.add(outerObject);
            }

        } catch (Exception ex) {
            log.error("error : ", ex);

        }

        DbDoc[] docs = dbDocList.toArray(new DbDoc[dbDocList.size()]);

        //TODO Utiliser le generateur de QUERY
        productRepository.removeDoc("supplierId = " + supplierId + " and type = '" + type.replace("\"", "") + "'");
        productRepository.addDoc(docs);
        log.info("FIN fromUrlToDb");
        return dbDocList.toString();
    }


    public String fromCsvToDb(MultipartFile file, String supplierId, String typeProduit) throws IOException, ReferentielRequestException, InputException, DataProvidedException {
        log.info("DEB fromCsvToDb");
        if (file == null) {
            throw new RuntimeException("You must select a file for uploading");
        }

        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String contentType = file.getContentType();
        long size = file.getSize();
        log.info("Receiving CsvFile: " + originalName + " contentType: " + contentType + " size: " + size);

        log.info("processing Csv from supplierId " + supplierId + " type= " + typeProduit);
        CsvParserSettings settings = new CsvParserSettings(); //configuration du parser
        settings.detectFormatAutomatically();

        // configure to grab headers from file. We want to use these names to get values from each record.
        settings.setHeaderExtractionEnabled(true);
        settings.setSkipEmptyLines(true);
        settings.setEmptyValue("");
        settings.setNullValue("");
        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);
        // parses all records in one go.
        List<Record> allRecords = parser.parseAllRecords(inputStream);
        //On vérifie la coherence du fichier avec le model
        Model model = compareModelWithFileHeader(typeProduit, parser);
        //L'objet dbDocList de sortie a passer au productRepo
        List<DbDoc> dbDocList = new ArrayList<>();

        List<String> refusedRecords = new ArrayList<>();

        for (Record record : allRecords) {
            //On retourne le resultat dans une map string string qui pourra s'intégrer dans properties d'un JSON
            //Ce sont les fields du header qui sont les clés !!
            Map<String, String> mymap = record.toFieldMap();

            String testedRecord = checkRecordFields(mymap, model);
            if (!testedRecord.isEmpty())
                refusedRecords.add(testedRecord);
            else {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.valueToTree(mymap);

                DbDoc outerObject = updateJsonNode(supplierId, typeProduit, jsonNode, model);
                dbDocList.add(outerObject);
            }
        }

        if (!dbDocList.isEmpty()) {
            //on le convertit en tableau car dependance du add mysql
            DbDoc[] docs = dbDocList.toArray(new DbDoc[dbDocList.size()]);

            //TODO Utiliser le generateur de QUERY
            productRepository.removeDoc("supplierId = " + supplierId + " and type = '" + typeProduit.replace("\"", "") + "'");
            Iterator<Warning> result = productRepository.addDoc(docs);

            //On évalue les bornes et valeurs des proprietes du modele selon les données en base
            recalculMinMaxValue(typeProduit);
        } else throw new DataProvidedException("FAILED : Aucun produit ajouté");

        if (!refusedRecords.isEmpty()) {
            log.info("Lignes rejettées: " + refusedRecords.toString());
            throw new DataProvidedException(refusedRecords.size() + " ligne(s) rejetées: \n" + refusedRecords.toString());
        }

        log.info("FIN fromCsvToDb");

        return refusedRecords.toString();
    }

    /**
     * Verification du contenu fourni pour verifier la coherence
     *
     * @param mymap
     * @param model
     */
    private String checkRecordFields(Map<String, String> mymap, Model model) {
        try {
            model.getModelProperties().forEach(prop -> {
                if (prop.getType().equals(TypeModelPropertyEnum.NUMERIC))
                    Integer.parseInt(mymap.get(prop.getTechnicalName()));
            });
        } catch (NumberFormatException ex) {
            return mymap.toString();
        }

        return "";
    }

    /**
     * recalculMinMaxValue fonction qui recalcule les valeurs de bornes des éléments d'un modèle
     *
     * @param typeProduit
     */
    private void recalculMinMaxValue(String typeProduit) {
        //On boucle sur les propriétes du modele pour calculer les bornes et valeurs
        try {
            WebsiteConfiguration wsc = websiteConfigurationService.findOneById(1);
            wsc.getModelByTechnicalName(typeProduit).getModelProperties().forEach(prop -> {
                if (prop.filtrable || prop.filtrableAdvanced) {
                    if (prop.getType().equals(TypeModelPropertyEnum.NUMERIC))
                        productRepository.updateBornes(wsc.getModelByTechnicalName(typeProduit), prop.getTechnicalName());
                    else
                        prop.setValues(productRepository.listeDistinct(typeProduit, prop.getTechnicalName()));
                }
            });
            websiteConfigurationService.save(wsc);
        } catch (ReferentielRequestException e) {
            log.error("error : ", e);
        }
    }

    /**
     * block de check columns comparaison de model et du fichier passé
     */
    private Model compareModelWithFileHeader(String typeProduit, CsvParser parser) throws ReferentielRequestException, InputException {
        //getting current model to have its properties
        List<String> modelProps = new ArrayList<>();
        //recuperation sous forme de liste des modelProperties
        Model model = websiteConfigurationService.findOneById(1).getModelByTechnicalName(typeProduit);
        model.getModelProperties().stream().forEach(prop -> modelProps.add(prop.getTechnicalName()));

        //Parcours des headers du fichier passé et comparaison au modele attendu
        List<String> headers = Arrays.asList(parser.getContext().headers());

        if (modelProps.equals(headers)) {
            log.info("OK: Fichier cohérent avec le modele de donnée en place");
        } else throw new InputException("Error: Fichier incoherent avec le modele de donnée en place"
                + "\n Attendu:" + modelProps.toString()
                + "\n Envoyé: " + headers.toString());

        //Fin check columns
        return model;
    }

    private DbDoc updateJsonNode(String supplierId, String typeProduit, JsonNode jsonNode, Model model) throws IOException, ReferentielRequestException {

        //On prepare un object a modifier.
        ObjectNode parsedObject = jsonNode.deepCopy();

        //pour toutes les proprietes NUMERIC du modele on update l'object JSON
        model.getModelProperties().forEach(prop -> {
            if (prop.getType().equals(TypeModelPropertyEnum.NUMERIC))
                parsedObject.put(prop.getTechnicalName(), parsedObject.get(prop.getTechnicalName()).asInt());
        });

        DbDoc properties = JsonParser.parseDoc(new StringReader(parsedObject.toString()));

        DbDoc outerObject = new DbDocImpl().add("supplierId", new JsonNumber().setValue(supplierId))
                .add("type", new JsonString().setValue(typeProduit))
                .add("properties", properties);
        return outerObject;
    }

    @Override
    public CompanyDto create(CompanyDto companyDto) {
        Company company = companyConverter.dtoToEntity(companyDto);
        return companyConverter.entityToDto(this.companyRepository.save(company));
    }
}

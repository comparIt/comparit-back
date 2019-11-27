package com.pepit.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mysql.cj.xdevapi.*;
import com.pepit.exception.InputException;
import com.pepit.exception.ReferentielRequestException;
import com.pepit.model.Model;
import com.pepit.model.ModelProperty;
import com.pepit.repository.CompanyRepository;
import com.pepit.repository.ProductRepository;
import com.pepit.service.CompanyService;
import com.pepit.service.WebsiteConfigurationService;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.ResultIterator;
import com.univocity.parsers.common.processor.BeanProcessor;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.*;
import java.util.logging.Logger;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ProductRepository productRepository;
    private WebsiteConfigurationService websiteConfigurationService;

    private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class.getName());

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ProductRepository productRepository, WebsiteConfigurationService websiteConfigurationService) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
        this.websiteConfigurationService = websiteConfigurationService;
    }

    public String fromUrlToDb(String url, String supplierId, String type) {
        logger.info("DEB fromUrlToDb");
        List<DbDoc> dbDocList = new ArrayList<>();
        try {
            //Ajout d'un header http
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode parsedArray = mapper.readTree(response.getBody());

            //pour tous les elements retournés par l'API on construit l'objet json enrichi
            for (JsonNode parsedJson : parsedArray) {
                DbDoc outerObject = updateJsonNode(supplierId, type, mapper, parsedJson);
                dbDocList.add(outerObject);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        DbDoc[] docs = dbDocList.toArray(new DbDoc[dbDocList.size()]);

        //TODO Utiliser le generateur de QUERY
        productRepository.removeDoc("supplierId = "+supplierId + " and type = '" + type.replace("\"", "") + "'" );
        productRepository.addDoc(docs);
        logger.info("FIN fromUrlToDb");
        return dbDocList.toString();
    }


    public String fromCsvToDb(MultipartFile file, String supplierId, String typeProduit) throws IOException, ReferentielRequestException, InputException {
        logger.info("DEB fromCsvToDb");
        if (file == null) {
            throw new RuntimeException("You must select a file for uploading");
        }

        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();
        logger.info("inputStream: " + inputStream + "\noriginalName: " + originalName + "\nname: " + name + "\ncontentType: " + contentType + "\nsize: " + size);

        System.out.println("processing Csv from supplierId "+ supplierId + " type= "+ typeProduit);
        CsvParserSettings settings = new CsvParserSettings(); //configuration du parser
        settings.detectFormatAutomatically();

        // configure to grab headers from file. We want to use these names to get values from each record.
        settings.setHeaderExtractionEnabled(true);

        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);

        // parses all records in one go.
        List<Record> allRecords = parser.parseAllRecords(inputStream);


        /** block de check columns
         *
         */

        //getting current model to have its properties
        Model model = websiteConfigurationService.findOneById(1).getModelByTechnicalName(typeProduit);
        List<ModelProperty> mProps = model.getModelProperties();
        List<String> modelProps = new ArrayList<>();
        mProps.forEach(prop -> modelProps.add(prop.getTechnicalName()));

        //Parcours des headers du fichier passé et comparaison au modele attendu
        List<String> headers = Arrays.asList(parser.getContext().headers());
        headers.forEach( header -> {
            System.out.println("Checking column: " + header);
        });

        System.out.println(headers.toString());
        System.out.println(modelProps.toString());

        if(modelProps.equals(headers)){
            System.out.println("OK: Fichier cohérent avec le modele de donnée en place");
        }
        else throw new InputException("Error: Fichier incoherent avec le modele de donnée en place");

        //Fin check columns


        //L'objet dbDocList de sortie a passer au productRepo
        List<DbDoc> dbDocList = new ArrayList<>();

        for(Record record : allRecords){
            //On retourne le resultat dans une map string string qui pourra s'intégrer dans properties d'un JSON
            //Ce sont les fields du header qui sont les clés !!
            Map<String, String> mymap = record.toFieldMap();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.valueToTree(mymap);

            DbDoc outerObject = updateJsonNode(supplierId, typeProduit, mapper, jsonNode);
            dbDocList.add(outerObject);
        }
        //on le convertir en tableau car dependance du add mysql
        DbDoc[] docs = dbDocList.toArray(new DbDoc[dbDocList.size()]);

        //TODO Utiliser le generateur de QUERY
        productRepository.removeDoc("supplierId = "+supplierId + " and type = '" + typeProduit.replace("\"", "") + "'" );
        productRepository.addDoc(docs);

        logger.info("FIN fromCsvToDb");
        return dbDocList.toString();
    }

    private DbDoc updateJsonNode(String supplierId, String type, ObjectMapper mapper, JsonNode jsonNode) throws IOException {
        ObjectNode parsedObject = jsonNode.deepCopy();
        //TODO a voir si on laisse prix, pour l'instant on l'ajoute comme properties pour mocker les intervals de prix
        Random random = new Random();
        int min = 1;
        int max = 10;
        Integer randomInt = random.nextInt(max - min + 1) + min;

        DbDoc properties = JsonParser.parseDoc(new StringReader(jsonNode.toString())).add("prix", new JsonNumber().setValue(randomInt.toString()));

        DbDoc outerObject = new DbDocImpl().add("supplierId", new JsonNumber().setValue(supplierId))
                .add("type", new JsonString().setValue(type))
                .add("properties", properties);
        return outerObject;
    }
}

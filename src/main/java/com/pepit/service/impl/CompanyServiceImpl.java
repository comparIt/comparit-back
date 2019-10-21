package com.pepit.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pepit.repository.CompanyRepository;
import com.pepit.repository.ProductRepository;
import com.pepit.service.CompanyService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;
    private ProductRepository productRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository, ProductRepository productRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    public String getFromUrl(String url, String supplierId, String type) {

        JSONArray myJsonArray = new JSONArray();
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
                ObjectNode parsedObject = parsedJson.deepCopy();
                //TODO a voir si on laisse prix
                Random random = new Random();
                int min = 1; int max = 10;
                parsedObject.putPOJO("prix", random.nextInt(max - min + 1) + min);
                ArrayNode outerArray = mapper.createArrayNode(); //le json de sortie
                ObjectNode outerObject = mapper.createObjectNode(); //l'objet json que 'on surcharge
                outerObject.putPOJO("supplierId",supplierId);
                outerObject.putPOJO("type",type);
                outerObject.putPOJO("properties",parsedObject);
                outerArray.add(outerObject);

                myJsonArray.add(outerObject);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        productRepository.importJson(myJsonArray);
        return myJsonArray.toString();
    }
}

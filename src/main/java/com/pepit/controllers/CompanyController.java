package com.pepit.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pepit.business.CompanyBusiness;
import com.pepit.model.Product;
import com.pepit.model.ProductDto;
import com.pepit.repository.ProductRepository;
import com.pepit.service.CompanyService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

@RestController
@RequestMapping(value = "company", produces = MediaType.APPLICATION_JSON_VALUE)
public class CompanyController {

    private CompanyBusiness companyBusiness;
    private CompanyService companyService;
    private static ProductRepository productRepository;

    @Autowired
    public CompanyController(CompanyBusiness companyBusiness, CompanyService companyService, ProductRepository productRepository) {
        this.companyBusiness = companyBusiness;
        this.companyService = companyService;
        this.productRepository = productRepository;
    }

    @PostMapping(value = "/")
    public ResponseEntity<ProductDto> post(@RequestBody ProductDto product) {

        if (product != null) {
            System.out.println(product.getProperties());
        }

        // TODO: call persistence layer to update
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }



    @CrossOrigin
    @GetMapping("/byUrl")
    @ResponseBody
    public String sendUrlToGet(@RequestParam("url") String url, @RequestParam("fournId") String fournId){
        System.out.println(url);
        return getFromUrl(url, fournId);
    }

    private static String getFromUrl(String url, String fournId) {

        //String output="";
        JSONArray myJsonArray = new JSONArray();

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);

            //System.out.println("REPONSE API: "+response);
            ObjectMapper mapper = new ObjectMapper();
            //JsonNode root = null;
            JsonNode parsedArray = mapper.readTree(response.getBody());

            //pour tous les elements retournés par l'API on construit l'objet json enrichi

            for (JsonNode parsedJson : parsedArray) {
                ArrayNode outerArray = mapper.createArrayNode(); //le json de sortie
                ObjectNode outerObject = mapper.createObjectNode(); //the object with the "data" array
                outerObject.putPOJO("Fournisseur",fournId);
                outerObject.putPOJO("properties",parsedJson);
                outerArray.add(outerObject);

                //output+= "output: "+outerObject.toString();
                myJsonArray.add(outerObject);
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        productRepository.importJson(myJsonArray);
        return myJsonArray.toString();
    }
}

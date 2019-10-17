package com.pepit.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;
import com.pepit.business.CompanyBusiness;
import com.pepit.model.Product;
import com.pepit.model.ProductDto;
import com.pepit.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @Autowired
    public CompanyController(CompanyBusiness companyBusiness, CompanyService companyService) {
        this.companyBusiness = companyBusiness;
        this.companyService = companyService;
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
    public String sendUrlToGet(@RequestParam("url") String url){
        System.out.println(url);
        getFromUrl(url);
        return "OK";
    }

    private static void getFromUrl(String url) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET,entity,String.class);

            System.out.println(response);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = null;
            root = mapper.readTree(response.getBody());
            Iterator<String> fieldNames = root.fieldNames();
            while (fieldNames.hasNext()) {
                JsonNode productNodes = root.get("");
                Iterator<JsonNode> elements = productNodes.iterator();
                while (elements.hasNext()) {
                    JsonNode element = elements.next();
                    JsonNodeType nodeType = element.getNodeType();

                    if (nodeType == JsonNodeType.STRING) {
                        System.out.println("Group: " + element.textValue());
                    }

                    if (nodeType == JsonNodeType.ARRAY) {
                        Iterator<JsonNode> fields = element.iterator();
                        while (fields.hasNext()) {
                            System.out.println(fields.next());
                        }
                    }
                }
                fieldNames.next();
            };
            System.out.println(root.toString());

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }
}

package com.pepit.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.model.ProductDto;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Repository
public class ProductRepository {

    static Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=" + System.getenv("DATABASE_PASSWORD") );
    static Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));
    static Collection myColl = myDb.getCollection("produit");

    public List<ProductDto> find(String query){
        DocResult docs = myColl.find(query).execute();

        List<DbDoc> docList = docs.fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for ( DbDoc onedoc : docList) {

            String jsonP = "{\"properties\": " + onedoc.toString() + "}";
            System.out.println(jsonP);

            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto productDto = null;
            try {
                productDtos.add(objectMapper.readValue(jsonP.toString(), ProductDto.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productDtos;
    }

    public void importJson(JSONArray arr){
        //TODO delete de la collection pour TEST mais il faudra seulement supprimer les object d'un founisseur a l'import
        myDb.dropCollection("produit");
        myDb.createCollection("produit");
        myDb.getCollection("produit");
        //Fin TODO a REVOIR

        //Parcours du json array pour ajout oneByone dans la collection
        for (int i=0; i < arr.size(); i++) {
            String current = arr.get(i).toString();
            AddResult result = myColl.add(current).execute();
        }
    }
}

package com.pepit.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.bean.ProductDB;
import com.pepit.dto.ProductDto;
import com.pepit.util.Query;
import com.pepit.repository.ProductRepositoryCustom;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;

@NoArgsConstructor
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @Autowired
    ProductDB productDB;

    public List<ProductDto> testRequest(Query query){
        List<DbDoc> docList = productDB.find(query).fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();
        System.out.println("passed");
        for ( DbDoc doc : docList) {

            System.out.println(doc.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                productDtos.add(objectMapper.readValue(doc.toString(), ProductDto.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("done");
        return productDtos;
    }


    public List<ProductDto> find(String query){
        DocResult docs = productDB.getCollection().find(query).execute();

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

    public Iterator<Warning> addDoc(DbDoc[] dbDocs) {

        AddResult result = null;

        result = productDB.getCollection().add(dbDocs).execute();

        //Mettre a jour le fournisseur pour stocker qu'il a mis a jour son referentiel de produits
        return result.getWarnings();

    }

    public void removeDoc(String query){
        System.out.println("sending RemoveDoc Query: "+ query);
        Result res = productDB.getCollection().remove(query).execute();
        System.out.println( "removeResult: " + res.getAffectedItemsCount() + " Warnings: " + res.getWarnings().toString() + " Warningscount: " + res.getWarningsCount());
    }

    public Long count(){
        return productDB.getCollection().count();
    }
}

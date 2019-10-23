package com.pepit.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.bean.ProductDB;
import com.pepit.dto.ProductDto;
import com.pepit.util.Query;
import com.pepit.repository.ProductRepositoryCustom;
import lombok.NoArgsConstructor;
import org.json.simple.JSONArray;
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
        List<DbDoc> docList = productDB.runQuery(query).fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for ( DbDoc doc : docList) {

            System.out.println(doc.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                productDtos.add(objectMapper.readValue(doc.toString(), ProductDto.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productDtos;
    }

    public JSONArray importJson(JSONArray arr){
        //TODO delete de la collection pour TEST mais il faudra seulement supprimer les object d'un founisseur a l'import
        productDB.getDb().dropCollection("produit");
        productDB.getDb().createCollection("produit");
        productDB.getDb().getCollection("produit");
        //Fin TODO a REVOIR

        //Parcours du json array pour ajout oneByone dans la collection
        for (int i=0; i < arr.size(); i++) {
            String current = arr.get(i).toString();
            AddResult result = productDB.getCollection().add(current).execute();
        }

        return arr;

        //Mettre a jour le fournisseur pour stocker qu'il a mis a jour son referentiel de produits
    }
}

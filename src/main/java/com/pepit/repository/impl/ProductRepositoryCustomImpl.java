package com.pepit.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.mysql.cj.xdevapi.Collection;
import com.pepit.bean.ProductDB;
import com.pepit.model.ProductDto;
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

    public List<ProductDto> testRequest(String query){
        List<DbDoc> docList = productDB.runQuery(query).fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for ( DbDoc doc : docList) {
            String jsonP = "{\"details\": " + doc.toString() + "}";
            System.out.println(jsonP);

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                productDtos.add(objectMapper.readValue(jsonP, ProductDto.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return productDtos;
    }
}

package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.model.ProductDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Repository
public class ProductRepository {

    public List<ProductDto> find(String query){
        Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=root");
        Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));
        Collection myColl = myDb.getCollection("products");

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
}

package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.model.Product;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class ProductRepository {

    public void testRequest(){
        Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=rootP@ssw0rd");

        Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));

        Collection myColl = myDb.getCollection("products");

        DocResult docs = myColl.find("os like :os AND bluetooth like :bluetooth")
                .bind("os", "Android 9.0%").bind("bluetooth", "5.0,%").execute();

        List<DbDoc> doc = docs.fetchAll();
        System.out.println(doc);


        String json = "{\"name\": \"Pear yPhone 72\",\"category\": \"cellphone\",\"details\": {\"displayAspectRatio\": \"97:3\",\"audioConnector\": \"none\"}}";
        System.out.println(json.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        Product product = null;
        try {
            product = objectMapper.readValue(json, Product.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(product.getName());
        //System.out.println(product.getName());
        //System.out.println(product.getDetails().get("audioConnector"));
    }
}

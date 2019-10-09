package com.pepit.repository;

import com.mysql.cj.xdevapi.*;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class ProductRepository {

    public void testRequest(){
        Session mySession = new SessionFactory().getSession("mysqlx://192.168.99.100:33060/compareIt?user=root&password=rootP@ssw0rd");

        Schema myDb = mySession.getSchema("compareIt");

        Collection myColl = myDb.getCollection("products");

        DocResult docs = myColl.find("os like :os AND bluetooth like :bluetooth").fields("os")
                .bind("os", "Android 9.0%").bind("bluetooth", "5.0,%").execute();

        List<DbDoc> doc = docs.fetchAll();
        System.out.println(doc);
    }
}

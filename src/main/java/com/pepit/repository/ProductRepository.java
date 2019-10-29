package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.dto.ProductDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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

    public Iterator<Warning> addDoc(DbDoc[] dbDocs) {

        AddResult result = null;

        result = myColl.add(dbDocs).execute();

        //Mettre a jour le fournisseur pour stocker qu'il a mis a jour son referentiel de produits
        return result.getWarnings();

    }

    public void removeDoc(String query){
        System.out.println("sending RemoveDoc Query: "+ query);
        Result res = myColl.remove(query).execute();
        System.out.println( "removeResult: " + res.getAffectedItemsCount() + " Warnings: " + res.getWarnings().toString() + " Warningscount: " + res.getWarningsCount());
    }
}

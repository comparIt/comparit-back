package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.*;
import com.pepit.dto.ProductDto;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@NoArgsConstructor
@Repository
public class ProductRepository {

    static Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=" + System.getenv("DATABASE_PASSWORD") );
    static Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));
    static Collection myColl;

    public ProductRepository() {
        try {
            myColl = myDb.getCollection("produit", true);
        } catch (WrongArgumentException e) {
            myColl = myDb.createCollection("produit");
        }
    }

    public List<ProductDto> find(String query){
        DocResult docs = myColl.find(query).execute();

        //tentative de passer une query sur la database pour obtenir les minmax
        /*
        Result sqlTest = mySession.sql("SELECT MAX(CAST(doc->'$.properties.prix' AS DECIMAL(10,2))) AS min_price FROM produit;").execute();
        System.out.println(sqlTest);
        */

        List<DbDoc> docList = docs.fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for ( DbDoc onedoc : docList) {

            String jsonP = "{\"properties\": " + onedoc.toString() + "}";
            //System.out.println(jsonP);

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

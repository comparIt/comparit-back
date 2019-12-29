package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.bean.ProductDB;
import com.pepit.dto.ProductDto;
import com.pepit.util.Query;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@Repository
@Slf4j
public class ProductRepositoryCustom {

    @Autowired
    ProductDB productDB;


    public List<ProductDto> testRequest(Query query) {
        log.info("Query :" + query);
        List<DbDoc> docList = productDB.find(query).fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();
        log.info("passed");
        for (DbDoc doc : docList) {

            // log.info(doc.toString());

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                productDtos.add(objectMapper.readValue(doc.toString(), ProductDto.class));
            } catch (IOException e) {
                log.error("error :", e);
            }
        }
        log.info("done");
        return productDtos;
    }


    public List<ProductDto> find(String query) {
        DocResult docs = productDB.getCollection().find(query).execute();

        List<DbDoc> docList = docs.fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for (DbDoc onedoc : docList) {

            String jsonP = "{\"properties\": " + onedoc.toString() + "}";

            ObjectMapper objectMapper = new ObjectMapper();
            try {
                productDtos.add(objectMapper.readValue(jsonP, ProductDto.class));
            } catch (IOException e) {
                log.error("error : ", e);
            }
        }

        return productDtos;
    }

    public Iterator<Warning> updateBornes(String technicalName) {
        //On passe une query sur la database pour setter les minmax
        String query = "UPDATE model_property SET min = (SELECT min(CAST(doc->'$.properties." + technicalName + "'  AS DECIMAL(10,2))) FROM produit)" +
                ", max = (SELECT max(CAST(doc->'$.properties." + technicalName + "' AS DECIMAL(10,2))) " +
                "FROM produit) where technical_name = '" + technicalName + "';";
        return productDB.getSession().sql(query).execute().getWarnings();

    }

    public List<String> listeDistinct(String technicalName) {
        String query = "SELECT distinct(doc->'$.properties." + technicalName + "') as list  FROM compareIt.produit;";
        SqlResult myResult = productDB.getSession().sql(query).execute();
        List<String> result = new ArrayList<>();
        // Gets the row and prints the first column
        List<Row> rowList = myResult.fetchAll();
        rowList.forEach(row -> result.add(row.getString(0).replace("\"", "")));
        return result;
    }

    public Iterator<Warning> addDoc(DbDoc[] dbDocs) {

        AddResult result = productDB.getCollection().add(dbDocs).execute();
        //Mettre a jour le fournisseur pour stocker qu'il a mis a jour son referentiel de produits
        return result.getWarnings();

    }

    public void removeDoc(String query) {
        log.info("sending RemoveDoc Query: " + query);
        Result res = productDB.getCollection().remove(query).execute();
        log.info("removeResult: " + res.getAffectedItemsCount() + " Warnings: " + res.getWarnings().toString() + " Warningscount: " + res.getWarningsCount());
    }

    public Long count() {
        return productDB.getDb().getCollection("produit", true).count();
    }

    public ProductDto getProductById(String productId) {
        DbDoc doc = productDB.getCollection().getOne(productId);
        ProductDto productDto = new ProductDto();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            productDto = objectMapper.readValue(doc.toString(), ProductDto.class);
        } catch (IOException e) {
            log.error("error : ", e);
        }
        return productDto;
    }
}

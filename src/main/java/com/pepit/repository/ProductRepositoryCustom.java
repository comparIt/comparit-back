package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.pepit.dto.ProductDto;
import com.pepit.model.Model;
import com.pepit.util.Query;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
@Repository
@Slf4j
public class ProductRepositoryCustom {

    @Resource(name = "schema")
    private Schema schema;

    @Resource(name = "collection")
    private Collection collection;

    @Resource(name = "client")
    private Client client;

    @Resource(name = "clientFactory")
    private ClientFactory clientFactory;

    @Resource(name = "session")
    private Session session;

    public DocResult find(Query query) {
        log.info(query.toString());
        return query.find(collection).execute();
    }

    public List<ProductDto> testRequest(Query query) {
        log.info("Query :" + query);

        List<DbDoc> docList = find(query).fetchAll();

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
        if (collection.getSession().isOpen()) {
            log.info("collection.getSession().isOpen(): closing");
            collection.getSession().close();
            collection.getSession();
        }
        return productDtos;

    }

    public List<ProductDto> find(String query) {
        DocResult docs = collection.find(query).execute();

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

    public Iterator<Warning> updateBornes(Model model, String technicalName) {
        //On passe une query sur la database pour setter les minmax
        String query = "UPDATE model_property SET min = (SELECT min(CAST(doc->'$.properties." + technicalName + "' AS DECIMAL(10,2))) FROM produit where doc->'$.type' ='" + model.getTechnicalName() + "' )" +
                ", max = (SELECT max(CAST(doc->'$.properties." + technicalName + "' AS DECIMAL(10,2))) " +
                "FROM produit where doc->'$.type' ='" + model.getTechnicalName() + "' ) where id IN (select model_properties_id from compareIt.model_model_properties where model_id = '" + model.getId() + "')" +
                "and technical_name = '" + technicalName + "';";
        return schema.getSession().sql(query).execute().getWarnings();

    }

    public List<String> listeDistinct(String typeProduit, String technicalName) {
        String query = "SELECT distinct(doc->'$.properties." + technicalName + "') as list  FROM compareIt.produit where doc->'$.type' ='" + typeProduit + "' and doc->'$.properties.name' is not NULL;";
        SqlResult myResult = schema.getSession().sql(query).execute();
        List<String> result = new ArrayList<>();
        // Gets the row and prints the first column
        List<Row> rowList = myResult.fetchAll();
        rowList.forEach(row -> result.add(row.getString(0).replace("\"", "")));
        return result;
    }

    public Iterator<Warning> addDoc(DbDoc[] dbDocs) {

        AddResult result = collection.add(dbDocs).execute();
        //Mettre a jour le fournisseur pour stocker qu'il a mis a jour son referentiel de produits
        return result.getWarnings();

    }

    public void removeDoc(String query) {
        log.info("sending RemoveDoc Query: " + query);
        Result res = collection.remove(query).execute();
        log.info("removeResult: " + res.getAffectedItemsCount() + " Warnings: " + res.getWarnings().toString() + " Warningscount: " + res.getWarningsCount());
    }

    public Long count() {
        return collection.count();
    }

    public ProductDto getProductById(String productId) {
        DbDoc doc = collection.getOne(productId);
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

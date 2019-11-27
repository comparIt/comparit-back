package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.*;
import com.pepit.dto.ProductDto;
import com.pepit.service.impl.CompanyServiceImpl;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

//@NoArgsConstructor
@Repository
public class ProductRepository {

    static Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=" + System.getenv("DATABASE_PASSWORD") );
    static Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));
    static Collection myColl;

    private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class.getName());

    public ProductRepository() {
        try {
            myColl = myDb.getCollection("produit", true);
        } catch (WrongArgumentException e) {
            myColl = myDb.createCollection("produit");
        }
    }

    public List<ProductDto> find(String query){
        DocResult docs = myColl.find(query).execute();

        List<DbDoc> docList = docs.fetchAll();

        List<ProductDto> productDtos = new ArrayList<>();

        for ( DbDoc onedoc : docList) {

            String jsonP = "{\"properties\": " + onedoc.toString() + "}";
            //logger.info(jsonP);

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

    public SqlResult updateBornes(String technicalName) {
        //On passe une query sur la database pour setter les minmax
        String query = "UPDATE model_property SET min = (SELECT min(CAST(doc->'$.properties."+technicalName+"'  AS DECIMAL(10,2))) FROM produit)" +
                ", max = (SELECT max(CAST(doc->'$.properties."+technicalName+"' AS DECIMAL(10,2))) " +
                "FROM produit) where technical_name = '"+technicalName+"';";
        logger.info(query);
        return mySession.sql(query).execute();

    }

    public Iterator<Warning> addDoc(DbDoc[] dbDocs) {

        AddResult result = null;

        result = myColl.add(dbDocs).execute();

        //Mettre a jour le fournisseur pour stocker qu'il a mis a jour son referentiel de produits
        return result.getWarnings();

    }

    public void removeDoc(String query){
        logger.info("sending RemoveDoc Query: "+ query);
        Result res = myColl.remove(query).execute();
        logger.info( "removeResult: " + res.getAffectedItemsCount() + " Warnings: " + res.getWarnings().toString() + " Warningscount: " + res.getWarningsCount());
    }
}

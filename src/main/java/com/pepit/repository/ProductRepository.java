package com.pepit.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.*;
import com.mysql.cj.xdevapi.Collection;
import com.pepit.model.ProductDto;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.*;

@NoArgsConstructor
public class ProductRepository {

    public void testRequest(){
        Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/"+System.getenv("DATABASE_NAME")+"?user="+System.getenv("DATABASE_USERNAME")+"&password="+System.getenv("DATABASE_PASSWORD")+"");

        Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));

        Collection myColl = myDb.getCollection("products");

        String fromFront="os=%Oreo%&bluetooth=5.0,%";
        List<String> argList = Arrays.asList(fromFront.split("&"));
        Map<String,String> getArgs = new HashMap<>();

        for (String oneArg : argList) {
            String[] table = oneArg.split("=");
            getArgs.put(table[0], table[1]);
        }

        String query = "";

        Iterator it = getArgs.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> pair = (Map.Entry)it.next();
            String key = pair.getKey();
            String value = pair.getValue();
            query+=key+" like '"+value+"'";
            if(it.hasNext())
                query+=" AND ";
        }
        it.remove();

        DocResult docs = myColl.find(query).execute();

        List<DbDoc> docList = docs.fetchAll();

        List<ProductDto> productDtos = new ArrayList<ProductDto>();

        for ( DbDoc onedoc : docList) {

            String jsonP = "{\"details\": " + onedoc.toString() + "}";
            System.out.println(jsonP);

            ObjectMapper objectMapper = new ObjectMapper();
            ProductDto productDto = null;
            try {
                productDtos.add(objectMapper.readValue(jsonP.toString(), ProductDto.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

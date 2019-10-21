package com.pepit.bean;

import com.mysql.cj.xdevapi.*;
import com.pepit.util.Query;


public class ProductDB {

    private Collection collection;

    public ProductDB(){
        Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=rootP@ssw0rd");
        Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));
        this.collection = myDb.getCollection("produit");
    }

    public DocResult runQuery(Query query){
        System.out.println(query.toString());
        FindStatement statement = collection.find(query.criteriasAsStatement());
        statement.offset(query.getOffset());
        statement.limit(query.getLimit());
        statement.sort(query.getSort());
        statement.bind(query.getBoundParams());

        return statement.execute();
    }

}

package com.pepit.bean;

import com.mysql.cj.xdevapi.*;


public class ProductDB {

    private Collection collection;

    public ProductDB(){
        Session mySession = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=root");
        Schema myDb = mySession.getSchema(System.getenv("DATABASE_NAME"));
        this.collection = myDb.getCollection("products");
        System.out.println("poppin'");
    }

    public DocResult runQuery(String query){
        return collection.find(query).execute();
    }

}

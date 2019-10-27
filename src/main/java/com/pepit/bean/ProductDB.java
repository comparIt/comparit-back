package com.pepit.bean;

import com.mysql.cj.xdevapi.*;
import com.pepit.util.Query;


public class ProductDB {

    private Collection collection;
    private Session session;
    private Schema db;

    public ProductDB(){
        this.session = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user=root&password=rootP@ssw0rd");
        this.db = this.session.getSchema(System.getenv("DATABASE_NAME"));
        this.collection = this.db.getCollection("produit");
    }

    public DocResult find(Query query){
        System.out.println(query.toString());
        return query.find(this.collection).execute();
    }

    public Result delete(Query query){
        return query.delete(this.collection).execute();
    }

    public Collection getCollection() {
        return collection;
    }

    public Schema getDb() {
        return db;
    }
}

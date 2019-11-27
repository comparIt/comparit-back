package com.pepit.bean;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.*;
import com.pepit.util.Query;


public class ProductDB {

    private Collection collection;
    private Session session;
    private Schema db;

    public ProductDB(){
        this.session = new SessionFactory().getSession("mysqlx://" + System.getenv("DATABASE_HOST") + ":" + System.getenv("DATABASE_XPORT") +"/compareIt?user="+System.getenv("DATABASE_USERNAME")+"&password="+System.getenv("DATABASE_PASSWORD"));
        this.db = this.session.getSchema(System.getenv("DATABASE_NAME"));
        try {
            this.collection = this.db.getCollection("produit", true);
        } catch (WrongArgumentException e) {
            this.collection = this.db.createCollection("produit");
        }
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

    public Session getSession() {
        return session;
    }

    public Schema getDb() {
        return db;
    }
}

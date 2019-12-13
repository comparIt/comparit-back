package com.pepit.bean;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.*;
import com.pepit.config.Conf;
import com.pepit.util.Query;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductDB {

    private Collection collection;
    private Session session;
    private Schema db;

    public ProductDB(){
        this.session = new SessionFactory().getSession("mysqlx://" + Conf.getInstance().getHOST() + ":" + Conf.getInstance().getXPORT() + "/compareIt?user=root&password=" + Conf.getInstance().getPASSWORD());
        this.db = session.getSchema(Conf.getInstance().getNAME());
        try {
            this.collection = this.db.getCollection("produit", true);
        } catch (WrongArgumentException e) {
            this.collection = this.db.createCollection("produit");
        }
    }

    public DocResult find(Query query){
        log.debug(query.toString());
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

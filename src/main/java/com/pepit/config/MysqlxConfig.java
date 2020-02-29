package com.pepit.config;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.*;
import com.pepit.properties.Mysqlx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;



@Configuration
@Slf4j
public class MysqlxConfig {
    @Bean("mysqlX")
    @ConfigurationProperties(prefix = "mysqlx.datasource")
    public Mysqlx mysqlx() {
        log.debug("[XDEVAPI]using bean Mysqlx");
        return new Mysqlx();
    }

    @Bean("clientFactory")
    public ClientFactory cFact() {
        log.debug("[XDEVAPI]using bean clientFactory");
        return new ClientFactory();
    }

    @DependsOn({"clientFactory", "mysqlX"})
    @Bean("client")
    public Client client(ClientFactory clientFactory, Mysqlx mysqlX) {
        log.debug("[XDEVAPI]using bean Client");
        return clientFactory.getClient(mysqlX.getUrl(), mysqlX.getSettings());
    }

    @DependsOn({"client"})
    @Bean("session")
    public Session sess(Client client) {
        log.debug("[XDEVAPI]using bean Session");
        return client.getSession();
    }

    @DependsOn({"session", "mysqlX"})
    @Bean("schema")
    public Schema schema(Session session, Mysqlx mysqlX) {
        log.debug("[XDEVAPI]using bean Schema");
        return session.getSchema(mysqlX.getSchema());
    }

    @DependsOn({"schema"})
    @Bean("collection")
    public Collection collection(Schema schema) {
        log.debug("[XDEVAPI]using bean collection");

        Collection collection;
        try {
            collection = schema.getCollection("produit", true);
        } catch (WrongArgumentException e) {
            collection = schema.createCollection("produit");
        }
        return collection;
    }
}

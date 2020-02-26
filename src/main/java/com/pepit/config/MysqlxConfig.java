package com.pepit.config;

import com.mysql.cj.exceptions.WrongArgumentException;
import com.mysql.cj.xdevapi.*;
import com.pepit.properties.Mysqlx;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class MysqlxConfig {
    @Bean("mysqlX")
    @ConfigurationProperties(prefix = "mysqlx.datasource")
    public Mysqlx mysqlx() {
        return new Mysqlx();
    }

    @Bean("clientFactory")
    public ClientFactory cFact() {
        log.debug("using bean clientFactory");
        return new ClientFactory();
    }

    @DependsOn({"clientFactory", "mysqlX"})
    @Bean("client")
    public Client client(ClientFactory clientFactory, Mysqlx mysqlX) {
        return clientFactory.getClient(mysqlX.getUrl(), mysqlX.getSettings());
    }

    @Bean("sessionFactory")
    public SessionFactory sFact() {
        return new SessionFactory();
    }

    @DependsOn({"client"})
    @Bean("session")
    public Session sess(Client client) {
        log.debug("using bean session");
        return client.getSession();
    }

    @DependsOn({"session", "mysqlX"})
    @Bean("schema")
    public Schema schema(Session session, Mysqlx mysqlX) {
        return session.getSchema(mysqlX.getSchema());
    }

    @DependsOn({"schema"})
    @Bean("collection")
    public Collection collection(Schema schema) {
        log.debug("using bean collection");

        Collection collection;
        try {
            collection = schema.getCollection("produit", true);
        } catch (WrongArgumentException e) {
            collection = schema.createCollection("produit");
        }
        return collection;
    }
}

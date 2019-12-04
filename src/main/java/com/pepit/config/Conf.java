package com.pepit.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Conf {

    private static Conf INSTANCE = new Conf();

    public static Conf getInstance()
    {   return INSTANCE;
    }

    String XPORT = System.getenv("DATABASE_XPORT")!=null? System.getenv("DATABASE_XPORT") : "33060";
    String HOST = System.getenv("DATABASE_HOST")!=null? System.getenv("DATABASE_HOST") : "192.168.99.100";
    String PASSWORD = System.getenv("DATABASE_PASSWORD")!=null? System.getenv("DATABASE_PASSWORD") : "root";
    String NAME = System.getenv("DATABASE_NAME")!=null? System.getenv("DATABASE_NAME") : "compareIt";
}

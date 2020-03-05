package com.pepit.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mysqlx {
    private String url;
    private String schema;
    private String settings;
}
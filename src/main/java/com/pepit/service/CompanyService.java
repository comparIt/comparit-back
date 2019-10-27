package com.pepit.service;

import java.io.InputStream;

public interface CompanyService {

    String fromUrlToDb(String url, String supplierId, String type);

    String fromCsvToDb(InputStream inputStream, String supplierId, String type);
}

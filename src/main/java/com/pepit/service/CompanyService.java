package com.pepit.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CompanyService {

    String fromUrlToDb(String url, String supplierId, String type);

    String fromCsvToDb(MultipartFile file, String supplierId, String type) throws IOException;
}

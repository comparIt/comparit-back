package com.pepit.service;

import com.pepit.exception.DataProvidedException;
import com.pepit.exception.InputException;
import com.pepit.exception.ReferentielRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CompanyService {

    String fromUrlToDb(String url, String supplierId, String type);

    String fromCsvToDb(MultipartFile file, String supplierId, String type) throws IOException, ReferentielRequestException, InputException, DataProvidedException;
}

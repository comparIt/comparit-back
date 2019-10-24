package com.pepit.controllers;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.logging.Logger;

@Controller
@RequestMapping(value = "/supplier", produces = MediaType.APPLICATION_JSON_VALUE)

public class FileUploadController {
    private static final Logger logger = Logger.getLogger(FileUploadController.class.getName());
    @PostMapping("/upload")
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new RuntimeException("You must select a file for uploading");
        }
        InputStream inputStream = file.getInputStream();
        String originalName = file.getOriginalFilename();
        String name = file.getName();
        String contentType = file.getContentType();
        long size = file.getSize();
        logger.info("inputStream: " + inputStream);
        logger.info("originalName: " + originalName);
        logger.info("name: " + name);
        logger.info("contentType: " + contentType);
        logger.info("size: " + size);
        //TODO Do processing with uploaded file data in Service layer
        processCsv();
        return new ResponseEntity<String>(originalName, HttpStatus.OK);
    }

    private void processCsv() {
        System.out.println("processCsv");
        CsvParserSettings settings = new CsvParserSettings(); //configuration du parser
        settings.detectFormatAutomatically();
        CsvParser parser = new CsvParser(settings);

        for (Record record : parser.iterateRecords()) {
            Short age = record.getShort("age");
        }
    }
}

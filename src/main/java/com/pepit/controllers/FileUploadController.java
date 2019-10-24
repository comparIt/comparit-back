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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        processCsv(inputStream);
        return new ResponseEntity<String>(originalName, HttpStatus.OK);
    }

    private void processCsv(InputStream inputStream) {
        System.out.println("processCsv");
        CsvParserSettings settings = new CsvParserSettings(); //configuration du parser
        settings.detectFormatAutomatically();

        /** v0 working
        CsvParser parser = new CsvParser(settings);
        parser.beginParsing(inputStream);

        String[] row;
        while ((row = parser.parseNext()) != null) {
            System.out.println(row.toString());
        }*/

        // configure to grab headers from file. We want to use these names to get values from each record.
        settings.setHeaderExtractionEnabled(true);
        // creates a CSV parser
        CsvParser parser = new CsvParser(settings);

        // parses all records in one go.
        List<Record> allRecords = parser.parseAllRecords(inputStream);
        for(Record record : allRecords){
            //On retourne le resultat dans une map string string qui pourra s'intégrer dans properties d'un JSON
            //Ce sont les fields du header qui sont les clés !!
            Map<String, String> mymap = record.toFieldMap();
            System.out.println(mymap.toString());
        }
    }
}

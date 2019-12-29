package com.pepit.controllers;

import com.pepit.business.FilterBusiness;
import com.pepit.constants.Routes;
import com.pepit.dto.FilterCriteriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Routes.FILTER)
public class FilterController {

    @Autowired
    FilterBusiness filterBusiness;

    @PostMapping
    public ResponseEntity createFilter(@RequestBody FilterCriteriaDto filterDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filterBusiness.createFromCriteriaDto(filterDto));
    }

    @GetMapping
    public ResponseEntity getAllFilters() {
        return ResponseEntity.status(200).body(filterBusiness.getAllFiltersByUser());
    }


}

package com.pepit.controllers;

import com.pepit.constants.Routes;
import com.pepit.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Routes.ALERT)
public class AlertController {

    private AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

//    @GetMapping()
//    public ResponseEntity getAlertsByUser() {
//        return ResponseEntity.status(200).body(alertService.getAlertsByUser());
//    }
//
//    @PutMapping()
//    public ResponseEntity createAlert(AlertDto alertDto) {
//        return ResponseEntity.status(200).body(alertService.getAlertsByUser());
//    }

    @GetMapping("/count")
    public ResponseEntity countData(){
        return ResponseEntity.status(200).body(alertService.countData());
    }

}

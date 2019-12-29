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

    @Autowired
    private AlertService alertService;

    @GetMapping()
    public ResponseEntity getAlertsByUser() {
        return ResponseEntity.status(200).body(alertService.getAllAlertsByUser());
    }

    @GetMapping("/unseen")
    public ResponseEntity getUnseenAlertsByUser() {
        return ResponseEntity.status(200).body(alertService.getUnconsultedAlertsByUser());
    }

    @GetMapping("/unseen/count")
    public ResponseEntity getUnseenCountAlertByUser(){
        return ResponseEntity.status(200).body(alertService.getNumberOfUnseenNotifications());
    }

}

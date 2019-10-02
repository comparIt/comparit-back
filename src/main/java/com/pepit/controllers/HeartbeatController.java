package com.pepit.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("heartbeat")
public class HeartbeatController {

    @GetMapping
    public String heartBeat() {
        return "I'm alive";
    }
}

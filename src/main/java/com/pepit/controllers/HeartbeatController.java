package com.pepit.controllers;

import com.pepit.constants.Routes;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Test if alive")
@RestController
@RequestMapping(Routes.HEARTBEAT)
public class HeartbeatController {

    @GetMapping
    public String heartBeat() {
        return "I'm alive";
    }
}

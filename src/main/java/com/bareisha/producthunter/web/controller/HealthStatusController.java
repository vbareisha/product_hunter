package com.bareisha.producthunter.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/status")
@Slf4j
public class HealthStatusController {

    // Quick check app status
    @GetMapping
    public ResponseEntity<?> checkStatus() {
        log.debug("Health check...");
        Map<String, String> status = new HashMap<>();
        status.put("status", "ok");
        return new ResponseEntity<>(status, OK);
    }
}

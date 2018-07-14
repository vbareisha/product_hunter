package com.bareisha.producthunter.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/status")
public class HealthStatusController {

	// Quick check app status
	@GetMapping
	public ResponseEntity<?> checkStatus() {
		Map<String, String> status = new HashMap<>();
		status.put("status", "ok");
		return new ResponseEntity<>(status, OK);
	}
}

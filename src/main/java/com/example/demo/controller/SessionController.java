package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @Autowired
    private HttpSession httpSession;

    @CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
    @GetMapping("/session-info")
    public ResponseEntity<String> getSessionInfo() {
        if (httpSession.isNew()) {
            return new ResponseEntity<>("New session started", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Existing session", HttpStatus.OK);
        }
    }
}
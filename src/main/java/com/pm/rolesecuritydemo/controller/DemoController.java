package com.pm.rolesecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    @GetMapping("/api/public")
    public Map<String, String> publicEndpoint() {
        return Map.of(
                "message", "Bu endpoint herkese açıktır.",
                "access", "PUBLIC"
        );
    }

    @GetMapping("/api/guest")
    public Map<String, String> guestEndpoint() {
        return Map.of(
                "message", "Guest endpoint'ine eriştin.",
                "role", "GUEST"
        );
    }

    @GetMapping("/api/user")
    public Map<String, String> userEndpoint() {
        return Map.of(
                "message", "User endpoint'ine eriştin.",
                "role", "USER"
        );
    }

    @GetMapping("/api/admin")
    public Map<String, String> adminEndpoint() {
        return Map.of(
                "message", "Admin endpoint'ine eriştin.",
                "role", "ADMIN"
        );
    }
}

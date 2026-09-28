package com.pm.rolesecuritydemo.controller;

import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.model.Role;
import com.pm.rolesecuritydemo.service.AppUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    private final AppUserService appUserService;

    public DemoController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/api/public")
    public Map<String, String> publicEndpoint() {
        return Map.of(
                "message", "Bu endpoint herkese açıktır.",
                "access", "PUBLIC"
        );
    }

    @GetMapping("/api/guest")
    public List<AppUser> guestEndpoint() {
        return appUserService.getGuests(Role.GUEST);
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

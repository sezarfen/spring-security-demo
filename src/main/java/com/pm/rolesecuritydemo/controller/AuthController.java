package com.pm.rolesecuritydemo.controller;

import com.pm.rolesecuritydemo.dto.RegisterRequest;
import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest){
        AppUser savedUser = this.authService.register(registerRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Kullanıcı başarıyla oluşturuldu",
                        "username", savedUser.getUsername(),
                        "role", savedUser.getRole().name()
                ));
    }
}

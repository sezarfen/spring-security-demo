package com.pm.rolesecuritydemo.controller;

import com.pm.rolesecuritydemo.dto.LoginRequest;
import com.pm.rolesecuritydemo.dto.LoginResponse;
import com.pm.rolesecuritydemo.dto.RegisterRequest;
import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.service.AuthService;
import com.pm.rolesecuritydemo.service.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService,
                          AuthenticationManager authenticationManager,
                          JwtUtil jwtUtil) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest
            ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        String token = jwtUtil.generateToken(authentication);

        return ResponseEntity.ok(
                new LoginResponse(token, "Bearer")
        );
    }

}

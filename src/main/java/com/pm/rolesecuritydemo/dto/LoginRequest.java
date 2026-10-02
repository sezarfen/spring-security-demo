package com.pm.rolesecuritydemo.dto;

public record LoginRequest(
        String username,
        String password
) {
}

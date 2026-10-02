package com.pm.rolesecuritydemo.dto;

public record LoginResponse(
        String token,
        String tokenType
) {
}

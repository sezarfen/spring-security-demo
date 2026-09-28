package com.pm.rolesecuritydemo.dto;

import com.pm.rolesecuritydemo.model.Role;

public record RegisterRequest(
        String username,
        String password,
        String role
) {
}

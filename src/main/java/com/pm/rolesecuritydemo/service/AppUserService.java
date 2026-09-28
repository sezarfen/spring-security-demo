package com.pm.rolesecuritydemo.service;

import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.model.Role;
import com.pm.rolesecuritydemo.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository){
        this.appUserRepository = appUserRepository;
    }

    public List<AppUser> findAll() {
        return this.appUserRepository.findAll();
    }

    public List<AppUser> getGuests(Role role) {
        // can also return DTO as response hiding password
        return this.appUserRepository.findByRole(role).orElse(null);
    }
}

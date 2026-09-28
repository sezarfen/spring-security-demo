package com.pm.rolesecuritydemo.service;

import com.pm.rolesecuritydemo.dto.RegisterRequest;
import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.model.Role;
import com.pm.rolesecuritydemo.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser register(RegisterRequest registerRequest){
        if (appUserRepository.existsByUsername(registerRequest.username())){
            throw new IllegalArgumentException(
                    "Bu kullanıcı adı zaten kullanılıyor"
            );
        }

        if (registerRequest.username() == null || registerRequest.username().isBlank()){
            throw new IllegalArgumentException(
                    "Kullanıcı adı boş olamaz"
            );
        }

        if (registerRequest.password() == null || registerRequest.password().length() < 6){
            throw new IllegalArgumentException(
                    "Şifre en az 6 karakter içermeli"
            );
        }

        Role role;

        try {
            role = Role.valueOf(registerRequest.role());
        } catch (Exception exception){
            throw new IllegalArgumentException(
                    "Geçersiz rol. GUEST, USER veya ADMIN rollerini kullanmalısın"
            );
        }

        AppUser appUser = new AppUser(registerRequest.username(),
                passwordEncoder.encode(registerRequest.password()),
                role);

        return this.appUserRepository.save(appUser);
    }
}

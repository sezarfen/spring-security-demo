package com.pm.rolesecuritydemo.config;

import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.model.Role;
import com.pm.rolesecuritydemo.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer { // başlangıçta default olarak userlar oluşturmak için

    @Bean
    CommandLineRunner initUsers(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            if (appUserRepository.count() > 0) {
                return;
            }

            AppUser guest = new AppUser(
                    "guest",
                    passwordEncoder.encode("guest123"),
                    Role.GUEST
            );

            AppUser user = new AppUser(
                    "user",
                    passwordEncoder.encode("user123"),
                    Role.USER
            );

            AppUser admin = new AppUser(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    Role.ADMIN
            );

            appUserRepository.save(guest);
            appUserRepository.save(user);
            appUserRepository.save(admin);
        };
    }
}

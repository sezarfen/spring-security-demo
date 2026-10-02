package com.pm.rolesecuritydemo.repository;

import com.pm.rolesecuritydemo.model.AppUser;
import com.pm.rolesecuritydemo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);

    Optional<List<AppUser>> findByRole(Role role);

    boolean existsByUsername(java.lang.String username);
}

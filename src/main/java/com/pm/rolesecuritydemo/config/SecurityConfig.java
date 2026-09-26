package com.pm.rolesecuritydemo.config;

import com.pm.rolesecuritydemo.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AppUserRepository appUserRepository){
        return username -> appUserRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Kullanıcı adı bulunamadı: " + username));
    }

    /* Yukarıda lambda expression ile aşağıdaki kullanımı kullanmadan yazıyor olduk
            @Bean
        public UserDetailsService userDetailsService(
                AppUserRepository appUserRepository
        ) {
            return new UserDetailsService() {

                @Override
                public UserDetails loadUserByUsername(String username) {
                    return appUserRepository.findByUsername(username)
                            .orElseThrow(() ->
                                    new UsernameNotFoundException(
                                            "Kullanıcı bulunamadı: " + username
                                    )
                            );
                }
            };
        }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/public").permitAll()
                        .requestMatchers("/api/guest").hasRole("GUEST")
                        .requestMatchers("/api/user").hasRole("USER")
                        .requestMatchers("/api/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

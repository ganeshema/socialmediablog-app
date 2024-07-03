package com.ganeshgc.socialmediablog_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    // This bean is responsible for encoding passwords using the BCrypt hashing algorithm
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This bean defines the security filter chain which configures the HTTP security settings
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Disables Cross-Site Request Forgery (CSRF) protection
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/v1/api/**").permitAll() // Allows all GET requests to "/v1/api/**" without authentication
                        .anyRequest().authenticated()) // Requires authentication for any other request
                .httpBasic(Customizer.withDefaults()); // Enables HTTP Basic authentication
        return http.build(); // Builds and returns the security filter chain
    }

    // This bean provides user details service which stores users and their roles in memory
    @Bean
    public UserDetailsService userDetailsService() {
        // Creates a user named "ganesh" with the role "USER" and encodes the password "ganesh"
        UserDetails ganesh = User.builder()
                .username("ganesh")
                .password(passwordEncoder().encode("ganesh"))
                .roles("USER")
                .build();

        // Creates a user named "ema" with the role "ADMIN" and encodes the password "ema"
        UserDetails ema = User.builder()
                .username("ema")
                .password(passwordEncoder().encode("ema"))
                .roles("ADMIN")
                .build();

        // Returns an in-memory user details manager containing the created users
        return new InMemoryUserDetailsManager(ganesh, ema);
    }
}

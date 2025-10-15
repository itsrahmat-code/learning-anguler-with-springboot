package com.emranhss.merchandise.security;

import com.emranhss.merchandise.jwt.JwtAuthFilter;
import com.emranhss.merchandise.jwt.JwtService;
import com.emranhss.merchandise.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;
@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           JwtAuthFilter jwtAuthFilter,
                                           UserService userService) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(req -> req
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/auth/login/**",
                                "/api/auth/logout",
                                "/api/auth/logout/**",
                                "/api/auth/active",
                                "/api/auth/active/**",
                                "/api/admin",
                                "/api/admin/**",
                                "/api/auth/all",
                                "/api/auth/all/**",
                                "/api/admin",
                                "/api/admin/**",
                                "/api/admin/reg",
                                "/api/admin/reg/**",
                                "/api/cashier",
                                "/api/cashier/**",
                                "/api/category",
                                "/api/category/**",
                                "/api/cashier/reg",
                                "/api/cashier/reg/**",
                                "/api/cashier/profile",
                                "/api/cashier/profile/**",
                                "/api/manager/reg",
                                "/api/manager/reg/**",
                                "/api/manager/profile",
                                "/api/manager/profile/**",
                                "/images",
                                "/images/**",
                                "/api/returnproduct",
                                "/api/returnproduct/**",
                                "/api/resellproduct",
                                "/api/resellproduct/**",
                                "/api/reinvoices",
                                "/api/reinvoices/**",
                                "/api/replaceUnit",
                                "/api/replaceUnit/**",
                                "/api/invoice",
                                "/api/invoice/**",
                                "/api/expense",
                                "/api/expense/**",
                                "/api/category",
                                "/api/category/**",
                                "/api/cogs",
                                "/api/cogs/**",
                                "/api/customer",
                                "/api/customer/**",
                                "/api/duelist",
                                "/api/duelist/**",
                                "/api/employee",
                                "/api/employee/**",
                                "/api/brand",
                                "/api/brand/**",
                                "/api/goods",
                                "/api/goods/**",
                                "/api/manager",
                                "/api/manager/**",
                                "/api/product",
                                "/api/product/**",
                                "/api/replaceUnit",
                                "/api/replaceUnit/**",
                                "/api/supplier",
                                "/api/supplier/**",
                                "/api/resellproduct/add",
                                "/api/invoice/sellsummary",
                                "/api/invoice/duesummary",
                                "/api/invoice/expensesummary"
                        ).permitAll()


                )
                .userDetailsService(userService)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }


    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserService userService) {
        return new JwtAuthFilter(jwtService, userService);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Cache_Control", "Content-type"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
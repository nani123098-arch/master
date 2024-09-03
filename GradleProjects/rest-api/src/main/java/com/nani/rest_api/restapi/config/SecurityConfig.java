package com.nani.rest_api.restapi.config;

import com.nani.rest_api.restapi.jwtfilter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public UserDetailsService authorization()
    {
//        UserDetails admin = User.withUsername("nani").password(passwordEncoder().encode("pwd1"))
//                .roles("ADMIN").build();
//
//        UserDetails user = User.withUsername("chan").password(passwordEncoder().encode("pwd2")).roles("USER").build();

        return new UserInfoDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

     return   http.csrf(a -> a.disable()).sessionManagement(session->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(authenticationProvider()).formLogin(Customizer.withDefaults())
        .authorizeHttpRequests(
                authorize ->
                {
                    authorize.requestMatchers("api/v1/findAll","api/v1/saveUser", "api/v1/auth").permitAll()
//                            .requestMatchers("api/v1/saveUser").permitAll()
                            .requestMatchers("api/v1/**").authenticated();

                }
        ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();

    }


    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();

        dao.setUserDetailsService(authorization());
        dao.setPasswordEncoder(passwordEncoder());

        return dao;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}

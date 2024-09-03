package com.nani.spring_security.springsecurity.config;

import com.nani.spring_security.springsecurity.filter.JwtAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.net.http.HttpClient;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    private final StringHttpMessageConverter stringHttpMessageConverter;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(StringHttpMessageConverter stringHttpMessageConverter) {
        this.stringHttpMessageConverter = stringHttpMessageConverter;
    }

//    @Bean
//    public UserDetailsManager authorizeRequest(DataSource dataSource)
//    {
//        UserDetailsManager userDetailsManager =  new JdbcUserDetailsManager(dataSource);
//
//
//
//        return  userDetailsManager;
//    }



//    @Bean
//    public SecurityFilterChain roleBaseAuthorization(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeHttpRequests((authorize)->
//        {
//            authorize.requestMatchers(HttpMethod.GET,"api/v1/**").hasAnyRole("TEACHER","STUDENT")
//
//                    .requestMatchers(HttpMethod.POST,"api/v1/**", "api/v1/authenticate").hasAuthority("ROLE_STUDENT")
//                    .requestMatchers(HttpMethod.POST,"api/v2/**") .hasAuthority("ROLE_USER").anyRequest().authenticated().and()
//                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        }).httpBasic(Customizer.withDefaults()).csrf(a-> a.disable());
//
//        return httpSecurity.build();
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

package com.nani.spring_security.springsecurity.filter;

import com.nani.spring_security.springsecurity.entity.AuthRequest;
import com.nani.spring_security.springsecurity.entity.User;
import com.nani.spring_security.springsecurity.mapper.OwnRowMapper;
import com.nani.spring_security.springsecurity.repository.UserRepository;
import com.nani.spring_security.springsecurity.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {



    private JwtService jwtService;


    private UserRepository userRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }
    
    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username=null;
//        OwnRowMapper rowMapper = new OwnRowMapper();
//        AuthRequest authRequest = (AuthRequest) jdbcTemplate.queryForObject("select * from users where username=?",rowMapper,username);

        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            token = authHeader.substring(7);
           // System.out.println("Token is "+token);
            username =jwtService.extractUsername(token);
           // System.out.println("Nanaji user name is "+username);
        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
           // UserDetails userDetails = (UserDetails) userRepository.findByName(username).get();
            OwnRowMapper rowMapper = new OwnRowMapper();
            UserDetails userDetails = (UserDetails) jdbcTemplate.queryForObject("select * from users where username=?", rowMapper, username);

            //UserDetails userDetails = (UserDetails) userRepository.findByName(username).get();


            System.out.println("Printing the user name"+userDetails.getPassword());

            if(jwtService.validateToken(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}

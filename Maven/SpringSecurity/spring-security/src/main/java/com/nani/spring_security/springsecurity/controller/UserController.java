package com.nani.spring_security.springsecurity.controller;

import com.nani.spring_security.springsecurity.entity.AuthRequest;
import com.nani.spring_security.springsecurity.entity.User;
import com.nani.spring_security.springsecurity.service.JwtService;
import com.nani.spring_security.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("user")
    public ResponseEntity<User> saveUser(@RequestBody User user)
    {

        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id)
    {
        return  new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

//    @GetMapping("userbyname/{name}")
//    public ResponseEntity<Users> getById(@PathVariable String name)
//    {
//        return  new ResponseEntity<>(userService.getUserByName(name), HttpStatus.FOUND);
//    }

    @PostMapping("authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws Exception {
        Authentication authenticate = authenticationManager.authenticate((new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword()
        )));

        if(authenticate.isAuthenticated())
        {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw  new Exception("Username/Passsword is not found");
        }
    }



}

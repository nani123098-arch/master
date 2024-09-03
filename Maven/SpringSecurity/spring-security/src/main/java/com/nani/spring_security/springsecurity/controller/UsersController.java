package com.nani.spring_security.springsecurity.controller;

import com.nani.spring_security.springsecurity.entity.Users;
import com.nani.spring_security.springsecurity.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v2")
public class UsersController {

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/users")
    public ResponseEntity<Users> saveUsers(@RequestBody Users users)
    {
        users  = usersRepository.save(users);

        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }


}

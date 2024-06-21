package com.naniguides.springboot.controller;

import com.naniguides.springboot.dto.UserDto;
import com.naniguides.springboot.exception.EmailExistsException;
import com.naniguides.springboot.exception.ErrorDetails;
import com.naniguides.springboot.exception.ResourceNotFoundException;
import com.naniguides.springboot.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
@Tag( name =  "Crud operation controller",
        description = "Some Decscription C,r,u,d"


)
@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private UserService userService;

    //Creating a method to create an user
    @Operation(
            summary = "Operation to create the request",
            description = "Creating the table for the user"
    )
    @PostMapping
    public ResponseEntity<UserDto> createUser( @RequestBody @Valid UserDto user)
    {
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Build get user by ID Rest API
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") Long userId)
    {
       UserDto u = userService.getUserById(userId);

       return u;
    }
    @GetMapping("allusers")
    public ResponseEntity<List<UserDto>> getAllUsers()
    {
        List<UserDto> allUsers = userService.getAllUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @PutMapping("updateUser")
    public ResponseEntity<UserDto> updateUser( @RequestBody @Valid UserDto user)
    {
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser , HttpStatus.OK);
    }
    @DeleteMapping("deleteUser/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable("id") Long userId)
    {
       UserDto deletedUser = userService.deleteUser(userId);
        return new ResponseEntity<>(deletedUser , HttpStatus.OK);
    }


}

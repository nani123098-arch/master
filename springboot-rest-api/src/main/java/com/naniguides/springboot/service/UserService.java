package com.naniguides.springboot.service;
import java.util.*;

import com.naniguides.springboot.dto.UserDto;
import com.naniguides.springboot.entity.User;

public interface UserService {

    UserDto createUser(UserDto user);
    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto user);
    UserDto deleteUser(Long id);
}

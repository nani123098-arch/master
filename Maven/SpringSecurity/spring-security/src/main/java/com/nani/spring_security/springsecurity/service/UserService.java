package com.nani.spring_security.springsecurity.service;

import com.nani.spring_security.springsecurity.entity.User;
import com.nani.spring_security.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    private UsersRepository usersRepository;

//    public UserService(UserRepository userRepository, UsersRepository usersRepository) {
//        this.userRepository = userRepository;
//        this.usersRepository = usersRepository;
//    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public User getUser(Long id)
    {
        return userRepository.findById(id).get();
    }

//    public Users getUserByName(String name) {
//
//        return usersRepository.findByName(name).get();
//    }
}

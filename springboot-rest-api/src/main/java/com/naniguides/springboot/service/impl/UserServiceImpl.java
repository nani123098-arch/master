package com.naniguides.springboot.service.impl;

import com.naniguides.springboot.dto.UserDto;
import com.naniguides.springboot.entity.User;
import com.naniguides.springboot.exception.EmailExistsException;
import com.naniguides.springboot.exception.ResourceNotFoundException;
import com.naniguides.springboot.repository.UserRepository;
import com.naniguides.springboot.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;


    @Override
    public UserDto createUser(UserDto userDto)  {

        //Convert UserDTO to user entity

      //  User user = UserMapper.mapToUser(userDto);
       User user = modelMapper.map(userDto , User.class);


       // User user = AutoMapper.MAPPER.maptoUser(userDto);

        Optional<User>  u = userRepository.findByEmail(user.getEmail());

        if(u.isPresent())
        {
            throw new EmailExistsException("Email already exists");
        }



        User savedUser =  userRepository.save(user);

        //Conver userEntity to userDto
       UserDto userDto1 = modelMapper.map(savedUser , UserDto.class);
       // UserDto userDto1 = AutoMapper.MAPPER.maptoUserDto(savedUser);

        return userDto1;
    }

    public UserDto getUserById(Long id)
    {
        User u = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user","id", id)
        );

       UserDto userDto = modelMapper.map(u , UserDto.class);
       // UserDto userDto = AutoMapper.MAPPER.maptoUserDto(u);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {


        List<User> users = userRepository.findAll();


        return users.stream().map(a -> modelMapper.map(a, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = modelMapper.map(userDto , User.class);
       User existinguser = userRepository.findById(user.getId()).orElseThrow(
               () -> new ResourceNotFoundException("user","id", user.getId())
       );
       existinguser.setFirstName(user.getFirstName());
       existinguser.setLastName(user.getLastName());
       existinguser.setEmail(user.getEmail());

      User savedUser =  userRepository.save(existinguser);

     UserDto userDto1 = modelMapper.map(savedUser , UserDto.class);

       // UserDto userDto1 = AutoMapper.MAPPER.maptoUserDto(savedUser);
      return userDto1;

    }

    @Override
    public UserDto deleteUser(Long id) {
        User u = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("user","id", id)
        );

       UserDto userDto = modelMapper.map(u , UserDto.class);
//        UserDto userDto = AutoMapper.MAPPER.maptoUserDto(u);
        userRepository.deleteById(id);
        return userDto;
    }

}

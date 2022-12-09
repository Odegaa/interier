package com.example.webservice.services;

import com.example.webservice.models.User;
import com.example.webservice.payloads.UserDto;
import com.example.webservice.utils.ApiResponse;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Long userId);

    User getUserByFirstName(String firstName);

    User getUserByLastName(String lastName);

    ApiResponse addUser(UserDto userDto);

    ApiResponse updateUserById(Long userId, UserDto userDto);

    ApiResponse deleteUser(Long userId);

}
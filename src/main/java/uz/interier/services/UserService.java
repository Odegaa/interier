package uz.interier.services;

import uz.interier.models.User;
import uz.interier.payloads.UserDto;
import uz.interier.utils.ApiResponse;

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
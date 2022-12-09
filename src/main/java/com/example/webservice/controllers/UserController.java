package com.example.webservice.controllers;

import com.example.webservice.models.User;
import com.example.webservice.payloads.UserDto;
import com.example.webservice.services.UserService;
import com.example.webservice.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public HttpEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = service.addUser(userDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.CREATED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @GetMapping
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{userId}")
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }

    @GetMapping("/{userFirstName}")
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<User> getUserByFirstName(@PathVariable String userFirstName) {
        return ResponseEntity.ok(service.getUserByFirstName(userFirstName));
    }

    @GetMapping("/{userLastName}")
    @PreAuthorize(value = "hasAuthority('read')")
    public ResponseEntity<User> getUserByLastName(@PathVariable String userLastName) {
        return ResponseEntity.ok(service.getUserByLastName(userLastName));
    }

    @PutMapping("/{userId}")
    @PreAuthorize(value = "hasAuthority('write')")
    public HttpEntity<?> updateUser(@PathVariable Long userId,
                                    @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = service.updateUserById(userId, userDto);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize(value = "hasAuthority('write')")
    public HttpEntity<?> deleteUser(@PathVariable Long userId) {
        ApiResponse apiResponse = service.deleteUser(userId);
        return ResponseEntity.status(apiResponse.getStatus() ?
                HttpStatus.ACCEPTED.value() : HttpStatus.BAD_REQUEST.value()).body(apiResponse);
    }
}
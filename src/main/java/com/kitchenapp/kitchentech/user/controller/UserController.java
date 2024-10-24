package com.kitchenapp.kitchentech.user.controller;

import com.kitchenapp.kitchentech.user.model.User;
import com.kitchenapp.kitchentech.user.model.UserDto;
import com.kitchenapp.kitchentech.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/kitchentech/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/user
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<List<UserDto>>(users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/user/{userId}
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "userId") Long userId) {
        userService.existsUserById(userId);
        User user = userService.getUserById(userId);
        UserDto userDto = convertToDto(user);
        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/user/{userId}
    // Method: PUT
    @Transactional
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable(name="userId")Long userId,@RequestBody UserDto user) {
        userService.existsUserById(userId);
        userService.validateUser(user);

        User responseUser = new User();
        responseUser.setId(userId);
        responseUser.setFirstName(user.getFirstName());
        responseUser.setLastName(user.getLastName());
        responseUser.setUsername(user.getUsername());
        responseUser.setEmail(user.getEmail());
        responseUser.setBirthDate(user.getBirthDate());
        responseUser.setPhoto(user.getPhoto());
        responseUser.setPhone(user.getPhone());
        responseUser.setRestaurant(user.getRestaurant());
        responseUser.setRole(user.getRole());

        User userSaved = userService.updateUser(responseUser);
        return new ResponseEntity<User>(userSaved,HttpStatus.OK);
    }

    private UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .birthDate(user.getBirthDate())
                .photo(user.getPhoto())
                .role(user.getRole())
                .restaurant(user.getRestaurant())
                .build();
    }
}

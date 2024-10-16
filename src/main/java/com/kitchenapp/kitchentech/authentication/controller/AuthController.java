package com.kitchenapp.kitchentech.authentication.controller;

import com.kitchenapp.kitchentech.authentication.model.AuthResponse;
import com.kitchenapp.kitchentech.authentication.model.LoginRequest;
import com.kitchenapp.kitchentech.authentication.model.RegisterRequest;
import com.kitchenapp.kitchentech.authentication.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/kitchentech/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    // URL: http://localhost:8080/api/kitchentech/v1/auth/register
    // Method: POST
    @Transactional
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) {
        authService.existsUserByUsername(request);
        authService.validateRegisterRequest(request);
        AuthResponse registeredUser = authService.register(request);
        return new ResponseEntity<AuthResponse>(registeredUser, HttpStatus.CREATED);
    }

    // URL: http://localhost:8080/api/kitchentech/v1/auth/login
    // Method: POST
    @Transactional(readOnly = true)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest request) {

        AuthResponse loggedUser = authService.login(request);
        return new ResponseEntity<AuthResponse>(loggedUser, HttpStatus.OK);
    }
}

package com.kitchenapp.kitchentech.authentication.service.impl;

import com.kitchenapp.kitchentech.authentication.model.AuthResponse;
import com.kitchenapp.kitchentech.authentication.model.LoginRequest;
import com.kitchenapp.kitchentech.authentication.model.RegisterRequest;
import com.kitchenapp.kitchentech.authentication.service.AuthService;
import com.kitchenapp.kitchentech.exception.ValidationException;
import com.kitchenapp.kitchentech.jwt.JwtService;
import com.kitchenapp.kitchentech.user.model.Role;
import com.kitchenapp.kitchentech.user.model.User;
import com.kitchenapp.kitchentech.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService,
                           PasswordEncoder passwordEncoder,AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .birthDate(registerRequest.getBirthDate())
                .phone(registerRequest.getPhone())
                .photo(registerRequest.getPhoto())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .id(user.getId())
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        long id = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow().getId();
        return AuthResponse.builder()
                .token(token)
                .id(id)
                .build();
    }

    @Override
    public void validateRegisterRequest(RegisterRequest registerRequest) {
        if(registerRequest.getUsername()==null  ||
                registerRequest.getUsername().isEmpty())
        {
            throw new ValidationException("El username debe ser obligatorio");
        }
        if(registerRequest.getUsername().length()>100)
        {
            throw new ValidationException("El username no debe exceder los 100 caracteres");
        }
        if(registerRequest.getFirstName()==null  ||
                registerRequest.getFirstName().isEmpty())
        {
            throw new ValidationException("El nombre del usuario debe ser obligatorio");
        }
        if(registerRequest.getFirstName().length()>200)
        {
            throw new ValidationException("El nombre del usuario no debe exceder los 200 caracteres");
        }
        if(registerRequest.getLastName()==null || registerRequest.getLastName().isEmpty())
        {
            throw new ValidationException("El apellido del usuario debe ser obligatorio");
        }
        if(registerRequest.getLastName().length()>200)
        {
            throw new ValidationException("El apellido del usuario no debe exceder los 200 caracteres");
        }
        if (registerRequest.getEmail() == null || registerRequest.getEmail().isEmpty()) {
            throw new ValidationException("El email del usuario debe ser obligatorio");
        }
        if (registerRequest.getEmail().length() > 300) {
            throw new ValidationException("El email del usuario no debe exceder los 300 caracteres");
        }
        if (registerRequest.getPassword() == null || registerRequest.getPassword().isEmpty()) {
            throw new ValidationException("La contraseña del usuario debe ser obligatorio");
        }
        if (registerRequest.getPassword().length() > 100) {
            throw new ValidationException("La contraseña del usuario no debe exceder los 100 caracteres");
        }
    }

    @Override
    public void existsUserByUsername(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new ValidationException("Ya existe un usuario con el username " + registerRequest.getUsername());
        }
    }
}

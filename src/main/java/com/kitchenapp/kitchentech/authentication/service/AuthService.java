package com.kitchenapp.kitchentech.authentication.service;

import com.kitchenapp.kitchentech.authentication.model.AuthResponse;
import com.kitchenapp.kitchentech.authentication.model.LoginRequest;
import com.kitchenapp.kitchentech.authentication.model.RegisterRequest;

public interface AuthService {
    public abstract AuthResponse register(RegisterRequest registerRequest);
    public abstract AuthResponse login(LoginRequest loginRequest);
    public void validateRegisterRequest(RegisterRequest registerRequest);
    public void existsUserByUsername(RegisterRequest registerRequest);
}

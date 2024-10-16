package com.kitchenapp.kitchentech.user.service;

import com.kitchenapp.kitchentech.user.model.User;
import com.kitchenapp.kitchentech.user.model.UserDto;

import java.util.List;

public interface UserService {

    public abstract User createUser(User user);
    public abstract User getUserById(Long id);
    public abstract User updateUser(User user);
    public abstract void deleteUser(Long id);
    public abstract List<User> getAllUsers();
    public void existsUserById(Long Id);
    public void validateUser(UserDto user);
}

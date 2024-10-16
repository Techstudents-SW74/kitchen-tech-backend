package com.kitchenapp.kitchentech.user.repository;

import com.kitchenapp.kitchentech.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsById(Long id);
    boolean existsByUsername(String username);
    List<User> findAll();
    Optional<User> findByUsername(String username);

}

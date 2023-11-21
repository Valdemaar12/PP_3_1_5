package ru.bogomolov.springwithbootstrap.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.bogomolov.springwithbootstrap.entity.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);

    void saveUser(User user);

    void update(Long id, User updatedUser);

    void deleteById(Long id);

    List<User> findAll();

    User findById(Long id);
}

package ru.bogomolov.springwithbootstrap.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bogomolov.springwithbootstrap.entity.Role;
import ru.bogomolov.springwithbootstrap.repository.RoleRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}

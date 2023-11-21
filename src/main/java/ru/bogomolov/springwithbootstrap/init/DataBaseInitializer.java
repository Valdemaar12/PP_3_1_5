package ru.bogomolov.springwithbootstrap.init;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.bogomolov.springwithbootstrap.entity.Role;
import ru.bogomolov.springwithbootstrap.entity.User;
import ru.bogomolov.springwithbootstrap.repository.RoleRepository;
import ru.bogomolov.springwithbootstrap.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataBaseInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct // при использовании hbm2ddl.auto=create-drop при создании таблиц создадутся роли admin и user
    public void init() {
        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
            Role userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
            User adminUser = new User();
            adminUser.setName("Administrator");
            adminUser.setLastName("Administrator");
            adminUser.setAge((byte) 20);
            adminUser.setUsername("admin");
            adminUser.setPassword(encoder.encode("admin"));
            adminUser.setRoles(Set.of(adminRole, userRole));
            userRepository.save(adminUser);
            User regularUser = new User();
            regularUser.setName("Standartuser");
            regularUser.setLastName("Standartuser");
            regularUser.setAge((byte) 10);
            regularUser.setUsername("user");
            regularUser.setPassword(encoder.encode("user"));
            regularUser.setRoles(Set.of(userRole));
            userRepository.save(regularUser);
        }
    }
}
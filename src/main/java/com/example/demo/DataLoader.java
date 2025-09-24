package com.example.demo;

import com.example.demo.model.EndpointPermission;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.EndpointPermissionRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EndpointPermissionRepository endpointPermissionRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        EndpointPermission adminPerm = new EndpointPermission("PERM_ADMIN_HELLO", "GET", "/api/admin/hello");
        EndpointPermission userPerm = new EndpointPermission("PERM_USER_HELLO", "GET", "/api/user/hello");
        EndpointPermission permHello = new EndpointPermission("PERM_HELLO", "GET", "/api/permission/hello");
        endpointPermissionRepository.save(adminPerm);
        endpointPermissionRepository.save(userPerm);
        endpointPermissionRepository.save(permHello);

        Role adminRole = new Role("ADMIN", Set.of(adminPerm, userPerm, permHello));
        Role userRole = new Role("USER", Set.of(userPerm, permHello));
        roleRepository.save(adminRole);
        roleRepository.save(userRole);

        User admin = new User("admin", passwordEncoder.encode("admin123"), Set.of(adminRole));
        User user = new User("user", passwordEncoder.encode("user123"), Set.of(userRole));
        userRepository.save(admin);
        userRepository.save(user);
    }
}
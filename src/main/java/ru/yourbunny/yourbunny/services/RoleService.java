package ru.yourbunny.yourbunny.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.models.Role;
import ru.yourbunny.yourbunny.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public Role getUserRole() {
        Role role = roleRepository.findByName("user").get();
        return roleRepository.findByName("user").get();
    }
}

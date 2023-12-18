package ru.yourbunny.yourbunny.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.exceptions.RoleNotFoundException;
import ru.yourbunny.yourbunny.models.Role;
import ru.yourbunny.yourbunny.repositories.RoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    @Transactional
    public Role getUserRole() {
        // Role role = roleRepository.findByName("user").get();
        return roleRepository.findByName("USER").get();
    }

    @Transactional
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }
}

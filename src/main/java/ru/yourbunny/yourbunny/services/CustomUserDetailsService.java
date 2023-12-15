package ru.yourbunny.yourbunny.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.models.Role;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.RoleRepository;
import ru.yourbunny.yourbunny.repositories.UserRepository;
import ru.yourbunny.yourbunny.security.SiteUserDetails;
import ru.yourbunny.yourbunny.utils.RoleNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
        );
    }

    @Transactional
    public void createNewUser(User user) {
        Optional<Role> role =  roleRepository.findByName("USER");
        if (role.isPresent()) {
            user.setRoles(List.of(role.get()));
            userRepository.save(user);
        }
        else {
            throw new RoleNotFoundException();
        }
    }

}

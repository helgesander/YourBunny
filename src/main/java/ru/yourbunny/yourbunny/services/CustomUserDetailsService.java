package ru.yourbunny.yourbunny.services;

import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.yourbunny.yourbunny.dtos.RegistrationDto;
import ru.yourbunny.yourbunny.exceptions.EmailAlreadyExistException;
import ru.yourbunny.yourbunny.exceptions.EmailNotFoundException;
import ru.yourbunny.yourbunny.exceptions.UserAlreadyExistException;
import ru.yourbunny.yourbunny.exceptions.UserNotFoundException;
import ru.yourbunny.yourbunny.models.Privilege;
import ru.yourbunny.yourbunny.models.Role;
import ru.yourbunny.yourbunny.models.User;
import ru.yourbunny.yourbunny.repositories.UserRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
@AllArgsConstructor
@Setter
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailSenderService mailSender;

    @Transactional
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Transactional
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public boolean existsById(UUID id) {
        return userRepository.existsById(id);
    }

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public User findById(UUID id) {
        Optional<User> user = userRepository.findByUserId(id);
        return userRepository.findByUserId(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user.getRoles()));
    }
    @Transactional
    public UserDetails loadByEmail(String email) throws EmailNotFoundException {
        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null)
            throw new EmailNotFoundException(email);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(Arrays.asList(roleService.findByName("ROLE_USER")))
        );
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Sweater. Please, visit next link: http://%s/activate/%s",
                    user.getUsername(),
                    "localhost",
                    user.getActivationCode()
            );

            mailSender.send(user.getEmail(), "Activation code", message);
        }
    }

    @Transactional
    public User createNewUser(RegistrationDto registrationDto) throws UserAlreadyExistException, EmailAlreadyExistException, RoleNotFoundException {
        return getUser(registrationDto, "USER");
    }

    @Transactional
    public User createNewUser(RegistrationDto registrationDto, String role) throws RoleNotFoundException {
        return getUser(registrationDto, role);
    }

    private User getUser(RegistrationDto registrationDto, String role) throws RoleNotFoundException {
        User user = userRepository.findByUsername(registrationDto.getUsername()).orElse(null);
        User emailUser = userRepository.findByEmail(registrationDto.getEmail()).orElse(null);
        if (emailUser != null ) throw new EmailAlreadyExistException(emailUser.getEmail());
        if (user == null) {
            user = new User();
            user.setUsername(registrationDto.getUsername());
            user.setEmail(registrationDto.getEmail());
            user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
            List<Role> roleList = List.of(roleService.findByName("ROLE_" + role));
            if (roleList.isEmpty()) throw new RoleNotFoundException( );
            user.setRoles(List.of(roleService.findByName("ROLE_" + role)));
            user.setPhone(registrationDto.getPhone());
            user.setEnabled(true);
            userRepository.save(user);
        } else throw new UserAlreadyExistException(registrationDto.getUsername());
        return user;
    }

    @Transactional
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new EmailAlreadyExistException(email));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(
            Collection<Role> roles) {
        return getGrantedAuthorities(getPrivileges(roles));
    }

    private List<String> getPrivileges(Collection<Role> roles) {

        List<String> privileges = new ArrayList<>();
        List<Privilege> collection = new ArrayList<>();
        for (Role role : roles) {
            privileges.add(role.getName());
            collection.addAll(role.getPrivileges());
        }
        for (Privilege item : collection) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}

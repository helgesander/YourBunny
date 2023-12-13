package ru.yourbunny.yourbunny.configs;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import ru.yourbunny.yourbunny.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthProviderImpl authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf( (csrf) -> csrf
                .csrfTokenRepository(new CookieCsrfTokenRepository())
                .disable()
                ).authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/users/**", "/home").permitAll()
                        .requestMatchers("/admin/**").hasRole("admin")
                        .requestMatchers("/login", "/register").permitAll().anyRequest()
                        .authenticated()
                ).logout(logout -> logout
                        .permitAll()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutSuccessHandler((request, response, authentication) -> {
                                    response.setStatus(HttpServletResponse.SC_OK);
                                }
                        ));

        return http.build();
    }
}

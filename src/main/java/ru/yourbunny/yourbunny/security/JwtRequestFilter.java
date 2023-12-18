package ru.yourbunny.yourbunny.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.yourbunny.yourbunny.utils.JwtTokenUtils;

import java.io.IOException;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
// Проверка защищенной области
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String clientIP = request.getRemoteAddr();
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        String requestURI = ((HttpServletRequest) request).getRequestURI();
        if (authHeader != null && authHeader.startsWith("Bearer ") && !requestURI.contains("/auth/get-token")) {
            jwt = authHeader.substring(7);
            boolean isExpired = false;
            Claims claims = jwtTokenUtils.getAllClaimsFromToken(jwt);
            username = claims.getSubject();
            Date expiration = claims.getExpiration();
            isExpired = expiration.before(new Date());
            if (isExpired) {
                log.info("Client from {}: token is expired", clientIP);
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Token is expired");
                return;
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).toList()
                );
                SecurityContextHolder.getContext().setAuthentication(token);
            }
        }
        filterChain.doFilter(request, response);
    }
}
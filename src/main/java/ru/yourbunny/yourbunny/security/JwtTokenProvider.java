package ru.yourbunny.yourbunny.security;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

@Component
public class JwtTokenProvider {
    private static final long validityInMilliseconds = 3600000; // token is valid during one hour
}

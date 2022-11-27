package com.company.sintra.config;

import com.company.sintra.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtService {

    private Key key;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode("VGhpcyBpcyBteSBzZWNyZXQga2V5IGJhc2U2NCBiYXNlNjQgYmFzZTY0IGJhc2U2NA==");
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String issueToken(User user) {
        log.info("Issue JWT token to {}", user);
        final JwtBuilder jwtBuilder = Jwts.builder()
                .setIssuedAt(new Date())
                .claim("username", user.getUsername())
                .claim("userId", user.getId())
                .claim("role", user.getAuthorities().stream().map(authority -> authority.getRole().name()).collect(
                        Collectors.toList()))
                .setExpiration(Date.from(Instant.now().plusSeconds(600)))
                .setHeader(Map.of("type", "JWT"))
                .signWith(key, SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }

    public Authentication getAuthenticationBearer(Claims claims) {

        List<?> roles = claims.get("role", List.class);

        List<GrantedAuthority> authorityList = roles.stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims, "", authorityList);
    }

}

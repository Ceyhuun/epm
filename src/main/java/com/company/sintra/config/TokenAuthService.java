package com.company.sintra.config;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenAuthService implements AuthService {

    public static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";
    private final JwtService jwtService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        log.info("Jwt Auth Request Filter");
        var authorization = request.getHeader(AUTHORIZATION);

        if (authorization != null && authorization.startsWith(BEARER)) {
            String token = authorization.substring(BEARER.length());
            Claims claims = jwtService.parseToken(token);
            Authentication authenticationBearer = jwtService.getAuthenticationBearer(claims);
            return Optional.of(authenticationBearer);
        } else {
            return Optional.empty();
        }
    }
}

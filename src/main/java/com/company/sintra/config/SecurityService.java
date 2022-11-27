package com.company.sintra.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final ObjectMapper objectMapper;

    public JwtCredentials getCurrentJwtCredentials() {
        var securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        return objectMapper.convertValue(authentication.getPrincipal(), JwtCredentials.class);
    }
}

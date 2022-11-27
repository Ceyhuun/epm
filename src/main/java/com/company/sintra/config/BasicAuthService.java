package com.company.sintra.config;

import com.company.sintra.entity.Authority;
import com.company.sintra.entity.User;
import com.company.sintra.exception.LengthNotValidException;
import com.company.sintra.exception.UserNotFoundException;
import com.company.sintra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BasicAuthService implements AuthService {

    public static final String AUTHORIZATION = "Authorization";
    private static final String BASIC = "Basic ";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        log.info("Jwt Auth Request Filter");
        var authorization = request.getHeader(AUTHORIZATION);
        if (authorization != null && authorization.startsWith(BASIC)) {
            String token = authorization.substring(BASIC.length());
            byte[] decode = Base64.getDecoder().decode(token);
            String[] credentials = new String(decode).split(":");
            if (credentials.length != 2) {
                throw new LengthNotValidException();
            }

            User user = userRepository.findByUsername(credentials[0])
                    .orElseThrow(UserNotFoundException::new);
            boolean matches = passwordEncoder.matches(credentials[1], user.getPassword());
            if (matches) {
                Authentication authenticationBearer = getAuthenticationBearer(user);
                return Optional.of(authenticationBearer);
            }
        }
        return Optional.empty();
    }


    public Authentication getAuthenticationBearer(User user) {

        List<?> roles = user.getAuthorities().stream().map(Authority::getRole).collect(Collectors.toList());

        List<GrantedAuthority> authorityList = roles.stream()
                .map(a -> new SimpleGrantedAuthority(a.toString()))
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(user, "", authorityList);
    }
}

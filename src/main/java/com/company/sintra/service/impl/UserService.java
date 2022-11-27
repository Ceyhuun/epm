package com.company.sintra.service.impl;


import com.company.sintra.config.JwtService;
import com.company.sintra.dto.UserRegisterRequest;
import com.company.sintra.dto.UserRegisterResponse;
import com.company.sintra.entity.User;
import com.company.sintra.exception.AuthorityNotFoundException;
import com.company.sintra.exception.ExistsByUsernameException;
import com.company.sintra.exception.InvalidPasswordException;
import com.company.sintra.exception.UserNotFoundException;
import com.company.sintra.repository.AuthorityRepository;
import com.company.sintra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.company.sintra.entity.Role.MANAGER;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRegisterRequest dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new ExistsByUsernameException();
        }

        var authority = authorityRepository.findByRole(MANAGER)
                .orElseThrow(AuthorityNotFoundException::new);

        User user = User.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .isAccountNonExpired(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .isAccountNonLocked(true)
                .authorities(Set.of(authority))
                .build();
        userRepository.save(user);
    }

    public UserRegisterResponse login(UserRegisterRequest userRegisterRequest) {
        User user = userRepository.findByUsername(userRegisterRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundException(userRegisterRequest.getUsername()));
        boolean matches = passwordEncoder.matches(userRegisterRequest.getPassword(), user.getPassword());
        if (matches) {
            return UserRegisterResponse.builder()
                    .verificationToken(jwtService.issueToken(user))
                    .build();
        } else {
            throw new InvalidPasswordException();
        }
    }
}

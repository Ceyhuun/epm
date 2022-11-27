package com.company.sintra.controller;


import com.company.sintra.dto.UserRegisterRequest;
import com.company.sintra.dto.UserRegisterResponse;
import com.company.sintra.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody UserRegisterRequest userRegisterRequest) {
        userService.register(userRegisterRequest);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserRegisterResponse login(@RequestBody UserRegisterRequest userRegisterRequest){
        return userService.login(userRegisterRequest);
    }
}

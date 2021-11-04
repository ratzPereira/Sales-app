package com.ratz.controller;

import com.ratz.dto.CredentialsDTO;
import com.ratz.dto.TokenDTO;
import com.ratz.entity.User;
import com.ratz.exception.WrongPasswordException;
import com.ratz.security.JwtService;
import com.ratz.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {


    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid User user) {

        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        System.out.println(user);
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO authenticate(@RequestBody CredentialsDTO credentials) {

        try {
            User userAuthenticated = User.builder().userName(credentials.getUserName()).password(credentials.getPassword()).build();
            UserDetails user = userService.authUser(userAuthenticated);
            String token = jwtService.tokenGeneration(userAuthenticated);

            return new TokenDTO(userAuthenticated.getUserName(), token);
        } catch (UsernameNotFoundException | WrongPasswordException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}

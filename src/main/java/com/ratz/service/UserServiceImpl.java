package com.ratz.service;


import com.ratz.entity.User;
import com.ratz.exception.WrongPasswordException;
import com.ratz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserDetailsService {


    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;


    public UserDetails authUser(User user){
        UserDetails userDetails = loadUserByUsername(user.getUserName());
        boolean isPasswordCorrect = encoder.matches(user.getPassword(), userDetails.getPassword());

        if (isPasswordCorrect) return userDetails;
        throw new WrongPasswordException("Your password or username are incorrect");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        String[] roles = user.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return org.springframework.security.core.userdetails.
                User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }

    public User saveUser(User user) {
        return repository.save(user);
    }
}

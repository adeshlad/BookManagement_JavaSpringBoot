package com.demo.bookmanagement.user;

import com.demo.bookmanagement.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    public UserService(UserRepository repo, AuthenticationManager authManager, JwtService jwtService) {
        this.userRepository = repo;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    public User register(UserLoginRequest request) {
        User user = new User(request.getEmail(), encoder.encode(request.getPassword()));
        userRepository.save(user);
        return user;
    }

    public String login(UserLoginRequest request) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getEmail());
        } else {
            return "fail";
        }
    }
}
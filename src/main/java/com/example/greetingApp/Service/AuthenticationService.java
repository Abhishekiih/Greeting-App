package com.example.greetingApp.Service;



import com.example.greetingApp.dto.AuthUserDTO;
import com.example.greetingApp.dto.LoginDTO;
import com.example.greetingApp.Model.AuthUser;
import com.example.greetingApp.Repository.AuthUserRepository;
import com.example.greetingApp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String registerUser(AuthUserDTO authUserDTO) {
        if (authUserRepository.existsByEmail(authUserDTO.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }

        AuthUser newUser = new AuthUser();
        newUser.setFirstName(authUserDTO.getFirstName());
        newUser.setLastName(authUserDTO.getLastName());
        newUser.setEmail(authUserDTO.getEmail());
        newUser.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        authUserRepository.save(newUser);
        return "User registered successfully!";
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<AuthUser> userOptional = authUserRepository.findByEmail(loginDTO.getEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        AuthUser user = userOptional.get();
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password!");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}

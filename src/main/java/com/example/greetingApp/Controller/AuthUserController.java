package com.example.greetingApp.Controller;

import com.example.greetingApp.dto.AuthUserDTO;
import com.example.greetingApp.dto.LoginDTO;
import com.example.greetingApp.Service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthUserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        return ResponseEntity.status(201).body(authenticationService.registerUser(authUserDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        String token = authenticationService.loginUser(loginDTO);
        return ResponseEntity.ok().body("{\"message\": \"Login successful!\", \"token\": \"" + token + "\"}");
    }
}


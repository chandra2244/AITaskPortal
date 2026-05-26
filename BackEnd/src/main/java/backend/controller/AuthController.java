package backend.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;

import backend.security.JwtUtil;

import backend.dto.LoginRequest;
import backend.dto.RegisterRequest;
import backend.entity.User;
import backend.repository.UserRepository;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());

        user.setEmail(request.getEmail());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        boolean passwordMatch = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword());

        if (!passwordMatch) {
            return ResponseEntity.badRequest()
                    .body("Invalid Password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(token);
    }
}
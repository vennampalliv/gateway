package com.example.newgateway;

import com.example.newgateway.configuration.JwtUtil;
import com.example.newgateway.model.User;
import com.example.newgateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private JwtUtil util;

    @Autowired
    private UserRepository repository;


    @PostMapping("/register")
    public Mono<ResponseEntity<?>> registerUser(@RequestBody User user) {
        user.setActive(true);
        return repository.save(user).map(ResponseEntity::ok);
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody User user) {
        return repository.findByEmail(user.getEmail()).map(user1 -> {
            if (user1 != null && user1.getPassword().equals(user.getPassword())) {
                String token = util.generateToken(user1.getEmail());
                return ResponseEntity.ok(token);

            }
            return ResponseEntity.status(401).body("User not found!!");

        });

    }

}

package com.example.newgateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/spring-boot")
public class UserRestController {

    @GetMapping("/srikara")
    public String getSrikara() {
        return "Hello Srikara how are you, it's time to sleep!!";
    }
}

package app.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HelloController {

    @GetMapping("/header")
    public String getHeader() {
        return "Hello from Spring Boot Header!";
    }

    @GetMapping("/content")
    public String getContent() {
        return "This is the content from Spring Boot!";
    }
}



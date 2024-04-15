package app.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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



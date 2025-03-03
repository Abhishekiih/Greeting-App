package com.example.greetingApp.Controller;

import com.example.greetingApp.Model.Greeting;
import com.example.greetingApp.Service.GreetingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public Greeting getGreeting() {
        return new Greeting(greetingService.getGreetingMessage());
    }
}

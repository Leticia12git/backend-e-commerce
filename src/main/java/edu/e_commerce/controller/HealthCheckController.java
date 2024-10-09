package edu.e_commerce.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/healthCheck")
public class HealthCheckController {

    @GetMapping
    public String healthCheck (){
        return "Deu Certo";
    }
}

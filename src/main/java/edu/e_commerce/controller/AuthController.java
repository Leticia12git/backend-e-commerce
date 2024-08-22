package edu.e_commerce.controller;

import edu.e_commerce.dtos.AuthenticatedDTO;
import edu.e_commerce.model.User;
import edu.e_commerce.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid AuthenticatedDTO data) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var authentication = authenticationManager.authenticate(userNamePassword);
        // Extrai o usuário autenticado a partir do objeto `authentication`
        User user = (User) authentication.getPrincipal();
        // Gera o token usando o usuário autenticado
        String token = tokenService.generateToken(user);
        return ResponseEntity.ok(token);
    }
}
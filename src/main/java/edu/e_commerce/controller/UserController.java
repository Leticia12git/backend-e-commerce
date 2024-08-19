package edu.e_commerce.controller;

import edu.e_commerce.model.User;
import edu.e_commerce.model.dto.UserDto;
import edu.e_commerce.model.dto.UserDtoResponse;
import edu.e_commerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta classe contem todos os endpoints relacionados ao usuario
 *
 * @author Leticia Lima
 */

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDtoResponse> register() {
        return userService.findAll();
    }

    @PostMapping
    public ResponseEntity<UserDtoResponse> register(@RequestBody @Valid UserDto userDto) {
        final var saved = userService.registerUser(userDto);

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(saved);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
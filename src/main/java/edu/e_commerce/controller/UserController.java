package edu.e_commerce.controller;

import edu.e_commerce.dtos.request.UserRequest;
import edu.e_commerce.dtos.response.UserResponse;
import edu.e_commerce.model.User;
import edu.e_commerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint responsavel por listar todos os usuarios
     *
     * @return List<Users></Users>
     */

    @GetMapping("/all")
    public List<User> findAllUsers() {
        return userService.findAll();
    }

    /**
     * Endpoint responsavel por cadastrar um usuario
     *
     * @param user
     * @return User
     */

    @PostMapping("/create")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest user) {
        UserResponse saved = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Endpoint responsavel por deleter um usuario
     *
     * @param id
     * @return
     */

    @DeleteMapping(name = "/delete", value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
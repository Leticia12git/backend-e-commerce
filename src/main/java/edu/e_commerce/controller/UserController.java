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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> register() {
        return userService.findAll();
    }

//    @PostMapping("/create")
//    public ResponseEntity<User> register(@RequestBody UserRequest user) {
//        User saved = userService.registerUser(user);
//        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
//    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest user) {
        UserResponse saved = userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }




    @DeleteMapping(name = "/delete", value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
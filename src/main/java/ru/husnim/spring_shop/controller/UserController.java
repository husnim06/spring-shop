package ru.husnim.spring_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import ru.husnim.spring_shop.dto.UserDTO;
import ru.husnim.spring_shop.dto.UserResponseDTO;
import ru.husnim.spring_shop.model.User;
import ru.husnim.spring_shop.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.FOUND);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<UserResponseDTO> getUserByName(@PathVariable String name) {
        return new ResponseEntity<>(userService.getUserByName(name), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Validated @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @Validated @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.updateUser(id, userDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

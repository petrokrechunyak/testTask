package org.alphabetas.testtask.controller;

import jakarta.validation.Valid;
import org.alphabetas.testtask.model.User;
import org.alphabetas.testtask.model.UserDto;
import org.alphabetas.testtask.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping("/")
    public User create(@RequestBody @Valid User user) {
        return userService.save(user);
    }

    @PatchMapping("/")
    public User updateSomeFields(@RequestBody UserDto user) {
        return userService.update(user);
    }

    @PutMapping("/")
    public User updateAllFields(@RequestBody @Valid User user) {
        return userService.update(user);
    }

    @DeleteMapping("/{email}")
    public void delete(@PathVariable String email) {
        userService.deleteById(email);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return userService.findById(id);
    }

    @GetMapping("/{from}/{to}")
    public List<User> findAllInRange(@PathVariable String from, @PathVariable String to) {
        return userService.findAllInRange(from, to);
    }

}

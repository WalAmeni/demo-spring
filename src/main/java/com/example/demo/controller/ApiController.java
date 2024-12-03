package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.model.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // il faut que ça soit RestController pour envoyer du json,
@RequestMapping("/api")
public class ApiController {

    @Autowired //injecté directement par spring
    private UserService userService;

    @GetMapping
    public List<UserDto> getHello() {
        return userService.getAllUsers();
    }

    @GetMapping("/:id")
    public User getUser(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        return  userService.update(user);
    }

    // Endpoint pour insérer un utilisateur
    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam Long id) {
        userService.delete(id);
    }

    @PostMapping("{id}/follow/{followId}")
    public ResponseEntity<UserDto> followUser(@PathVariable Long id, @PathVariable Long followId) {
        try {
            return ResponseEntity.ok( userService.followUser(id, followId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("{id}/unfollow/{followId}")
    public ResponseEntity<UserDto> unfollowUser(@PathVariable Long id, @PathVariable Long followId) {
        try {
            return ResponseEntity.ok(userService.unfollowUser(id, followId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

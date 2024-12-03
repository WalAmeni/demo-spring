package com.example.demo.controller;

import com.example.demo.model.LikeTable;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    @Autowired
    private UserService userService;

    @PostMapping("/like")
    public ResponseEntity<LikeTable> createLike(@RequestBody LikeTable likeTable) {
        try {
           return ResponseEntity.ok(userService.likeMessage(likeTable));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{id}")
    public List<LikeTable> getLikesByUser(@PathVariable Long userId) {
        return userService.findLikeByUserId(userId);
    }


}

package com.example.demo.controller;

import com.example.demo.model.Profile;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/profiles")
 class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Profile> getProfiles() {
        return userService.getAllProfiles();
    }

    @PostMapping("/profile")
    public Profile createUser(@RequestBody Profile profile) {
        return userService.save(profile);
    }

    @GetMapping("/:id")
    public Profile getProfile(@RequestParam Long id) {
        return userService.getProfileById(id);
    }
}

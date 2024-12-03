package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private List<MessageDto> messages = new ArrayList<>();

    private List<Long> following = new ArrayList<>();

    private List<LikeDto> likes = new ArrayList<>();

}

package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@JsonSerialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // permet d'éviter de surcharger les users
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) //permet de supprimer les messages si le user est supprimer
    @ToString.Exclude
    private List<Message> messages = new ArrayList<>();

    @ManyToMany
    @JoinTable(name="followers",
    joinColumns = @JoinColumn(name="follower_id"),
    inverseJoinColumns = @JoinColumn(name="following_id"))
    @ToString.Exclude
    private List<User> following = new ArrayList<User>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value ="user-likes")
    @ToString.Exclude
    private List<LikeTable> likes = new ArrayList<>();

    public List<LikeTable> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeTable> likes) {
        this.likes = likes;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public UserDto toDto() {
        return new UserDto(id, name, email, messages.stream().map(message -> message.toDto()).toList(), following.stream().map(User::getId).collect(Collectors.toList()), likes.stream().map(likeTable -> likeTable.toDto()).toList());
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
}

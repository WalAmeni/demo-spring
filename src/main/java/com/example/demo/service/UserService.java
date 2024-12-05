package com.example.demo.service;

import com.example.demo.model.LikeTable;
import com.example.demo.model.Message;
import com.example.demo.model.MessageDto;
import com.example.demo.model.Profile;
import com.example.demo.model.User;
import com.example.demo.model.UserAuthDto;
import com.example.demo.model.UserDto;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.ProfileRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    //@Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private LikeRepository likeRepository;

    @Autowired //deuxieme façon de faire
    public void setUpDependency(UserRepository userRepository) {
        System.out.println("la focntion est appeléé");
        this.userRepository = userRepository;
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(User::toDto).toList();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public void delete(Long id) {
        System.out.println("id :" + id);
        userRepository.deleteById(id);
    }

    public Profile getProfileById(Long id) {
        return profileRepository.getReferenceById(id);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Message save(Message message) {
        return messageRepository.save(message);
    }

    public List<MessageDto> getAllMessages() {
        return messageRepository.findAll().stream().map(Message::toDto).toList();
    }

    public List<MessageDto> findMessageByUserId(Long id) {
        return messageRepository.findByUserId(id).stream().map(Message::toDto).collect(Collectors.toList());
    }

    public UserDto followUser(Long id, Long followId) {
        User currentUser = this.getUserById(id);
        User followUser = this.getUserById(followId);
        currentUser.getFollowing().add(followUser);
        return  userRepository.save(currentUser).toDto();
    }

    public UserDto unfollowUser(Long id, Long followId) {
        User currentUser = this.getUserById(id);
        User followUser = this.getUserById(followId);
        currentUser.getFollowing().remove(followUser);
        return  userRepository.save(currentUser).toDto();
    }

    public LikeTable likeMessage(LikeTable like) {
        return likeRepository.save(like);
    }


    private Message getMessageById(Long messageId) {
        return messageRepository.getReferenceById(messageId);
    }

    public List<LikeTable> findLikeByUserId(Long userId) {
        return likeRepository.findByUserId(userId);
    }

    public User login(UserAuthDto user) {
        User newUser = userRepository.findUserByEmail(user.getEmail());
        if (Objects.isNull(newUser)) {
            throw new RuntimeException("User pas trouvé");
        }
        if (newUser.getPassword().equals(user.getPassword())) {
            return  newUser;
        }
        throw new RuntimeException("MDP invalide");
    }
}

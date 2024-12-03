package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@IdClass(value = LikeKey.class)
@Data
public class LikeTable {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "message_id")
    private Long messageId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference(value= "user-likes") // Regarder si à l'interieur de user il existe un type LikeTable il va l'ignorer pour empecher la boucle infinie
    private User user;

    @ManyToOne
    @JoinColumn(name = "message_id", insertable = false, updatable = false)
    @JsonBackReference(value = "message-likes")
    private Message message;


    public LikeDto toDto() {
        return new LikeDto(userId, messageId);
    }
}

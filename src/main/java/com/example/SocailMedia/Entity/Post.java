package com.example.SocailMedia.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private int postId;
    private String Tittle;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

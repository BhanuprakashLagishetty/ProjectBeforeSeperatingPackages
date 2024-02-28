package com.example.SocailMedia.Models;

import com.example.SocailMedia.Entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {

    private int postId;
    private String Tittle;
    private String content;

    private User user;
}

package com.example.SocailMedia.Models;

import com.example.SocailMedia.Entity.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendModel {
    private int friendsId;
    private String fname;
    private Set<UserModel> members=new HashSet<>();
}

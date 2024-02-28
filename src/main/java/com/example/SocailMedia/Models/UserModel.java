package com.example.SocailMedia.Models;

import com.example.SocailMedia.Entity.Freinds;
import com.example.SocailMedia.Entity.Post;
import com.example.SocailMedia.Entity.UserProfile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private int userId;
    private String userName;
    private String password;

    private UserProfileModel userProfileModel;

    //one to many connection

    private List<PostModel> postsModel;

    //many to many connection

    private Set<FriendModel> friendModel ;

}

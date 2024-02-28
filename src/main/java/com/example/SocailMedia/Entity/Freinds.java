package com.example.SocailMedia.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Freinds {
    @Id
    @GeneratedValue
    private int friendsId;
    private String fname;
    @ManyToMany(mappedBy = "friends")
    private Set<User> members = new HashSet<>();
}

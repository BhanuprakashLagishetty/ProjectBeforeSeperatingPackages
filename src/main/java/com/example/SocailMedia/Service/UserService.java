package com.example.SocailMedia.Service;

import com.example.SocailMedia.Entity.Freinds;
import com.example.SocailMedia.Entity.Post;
import com.example.SocailMedia.Entity.User;
import com.example.SocailMedia.Entity.UserProfile;
import com.example.SocailMedia.Models.FriendModel;
import com.example.SocailMedia.Models.PostModel;
import com.example.SocailMedia.Models.UserModel;
import com.example.SocailMedia.Models.UserProfileModel;
import com.example.SocailMedia.Repository.Friends_Repo;
import com.example.SocailMedia.Repository.Post_Repo;
import com.example.SocailMedia.Repository.User_Repo;
import com.example.SocailMedia.Repository.userProfile_Repository;
import com.oracle.wls.shaded.org.apache.bcel.generic.PUSH;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    User_Repo userRepo;
    @Autowired
    Post_Repo post;
    @Autowired
    Friends_Repo friends;
    @Autowired
    userProfile_Repository userprofile;
    public int  CheckLogin(String userName,String Password)
    {
        List<User>user=userRepo.findAll();
        System.out.println(user);
        for(User user1: user)
        {
            System.out.println("Successfully login");
            System.out.println(userName);
            System.out.println("THSIS LIST"+ user1.getUserName());
            if(user1.getUserName().equals(userName))
            {
                if(user1.getPassword().equals(Password))
                {
                    return user1.getUserId();
                }
            }

        }
        return 0;
    }
    public String addUser(UserModel userModel)
    {
        System.out.println(userModel+"this is usermodel");
        User user=new User();
        UserProfile userProfile=new UserProfile();
        UserProfileModel userProfileModel=userModel.getUserProfileModel();
        BeanUtils.copyProperties(userProfileModel,userProfile);
        BeanUtils.copyProperties(userModel,user);
        user.setUserProfile(userProfile);
      userRepo.save(user);
      return "Saved succesfully";
    }
    public List<User> display()
    {
        return userRepo.findAll();
    }
    public void remove(int id)
    {
        userRepo.deleteById(id);
    }
    public User userProfile(int id)
    {
        User user=userRepo.findById(id).orElse(null);
        return user;
    }
    public String savepost(Post post,int userId)
    {
      User user1=userRepo.findById(userId).orElse(null);
      List<Post> postList=user1.getPosts();
      postList.add(post);
      user1.setPosts(postList);
      post.setUser(user1);
      userRepo.save(user1);
      return "userentered succesfully";
    }
    public List<Post> viewPost(int userId)
    {
        User user1=userRepo.findById(userId).orElse(null);
        return user1.getPosts();
    }
    public String createGroup(FriendModel friendModel)
    {
        Freinds fr=new Freinds();
        BeanUtils.copyProperties(friendModel,fr);
        friends.save(fr);
        return "Successfully saved";

    }
    public List<FriendModel>viewAllGroups()
    {
        List<Freinds>fr=friends.findAll();
        List<FriendModel>frm=new ArrayList<>();
        fr.stream().forEach((e)->{
            FriendModel friendModel=new FriendModel();
            BeanUtils.copyProperties(e,friendModel);
            frm.add(friendModel);
        });
        System.out.println(frm);
        return frm;

    }
    public Set<UserModel> viewGroupMembers(int groupId)
    {
        Freinds fr=friends.findById(groupId).orElse(null);
        FriendModel friendModel=new FriendModel();
        Set<User>user =fr.getMembers();
        Set<UserModel>userModelSet=new HashSet<>();
        user.stream().forEach((e)->{
            UserModel userModel=new UserModel();
            BeanUtils.copyProperties(e,userModel);
            userModelSet.add(userModel);
        });
        return userModelSet;

    }
    public String addUserToGroup(int userId,int groupId)
    {

        System.out.println("Method called");
        User user = userRepo.findById(userId).orElse(null);
        Freinds group = friends.findById(groupId).orElse(null);
        System.out.println("this is proifle"+user.getUserProfile());
        System.out.println(user);
        System.out.println(group);

        if (user != null && group != null) {
            Set<Freinds> userGroups = user.getFriends();
            Set<User> groupMembers = group.getMembers();
            System.out.println("if statemetn");


            if (!userGroups.contains(group)) {
                userGroups.add(group);
                user.setFriends(userGroups);
            }


            if (!groupMembers.contains(user)) {
                groupMembers.add(user);
                group.setMembers(groupMembers);
            }
            System.out.println("THIS AFTER IF"+user);
            System.out.println("THis kjahsdf"+group);
           userRepo.save(user);
           friends.save(group);

            return "Successfully Added";
        } else {
            return "User or Group not found";
        }




    }
    public String removeGroup(int groupId)
    {
      Freinds fri=friends.findById(groupId).orElse(null);
      if(fri!=null)
      {
          Set<User> user=fri.getMembers();
          for(User user1:user)
          {
              Set<Freinds>fr=user1.getFriends();
              if(fr.contains(fri))
              {
                  fr.remove(fr);
                  user1.setFriends(fr);
                  userRepo.save(user1);
              }
          }
          fri.setMembers(new HashSet<>());
          friends.deleteById(groupId);
          return "Deleted succesfully";
      }
      else{
          return "Unable to find users";
      }


    }
    public String removeMember(int eid,int groupId)
    {
        System.out.println("THIS IS MY GROUP Id: "+groupId);
        User user=userRepo.findById(eid).orElse(null);
        Freinds fri=friends.findById(groupId).orElse(null);
        Set<User>user1=fri.getMembers();
        //removing group from the user
//        for(User us:user1)
//        {
//            Set<Freinds>fr=us.getFriends();
//            if(fr.contains(fri))
//            {
//                fr.remove(fri);
//                us.setFriends(fr);
//                userRepo.save(us);
//            }
//        }

        //removing user from the group
        if(user1.contains(user))
        {

            user1.remove(user);
            fri.setMembers(user1);
            Set<Freinds>fr=user.getFriends();
            if(fr.contains(fri))
            {
                fr.remove(fri);
                user.setFriends(fr);
            }


            userRepo.save(user);
            friends.save(fri);
        }
        return "Succesfully Deleted";

    }
}

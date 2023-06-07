package com.example.demo.Service;

import com.example.demo.Model.MyUser;
import com.example.demo.Repository.RepositoryUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceUser {
    private final RepositoryUser repositoryUser;
    public List<MyUser> getAllUsers()
    {
        List<MyUser> users= repositoryUser.findAll();
        return users;
    }

    public void register(MyUser myUser){
        myUser.setRole("CUSTOMER");

        String hash=new BCryptPasswordEncoder().encode(myUser.getPassword());
        myUser.setPassword(hash);
       repositoryUser.save(myUser);
    }
}



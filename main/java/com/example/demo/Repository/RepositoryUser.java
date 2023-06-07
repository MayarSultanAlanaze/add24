package com.example.demo.Repository;

import com.example.demo.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryUser extends JpaRepository<MyUser,Integer> {

    User findMyUserBy(Integer id);
    User findUserByUsername(String username);
    User findUserByPassword(String password);


}

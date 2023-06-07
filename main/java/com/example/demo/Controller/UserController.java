package com.example.demo.Controller;
import com.example.demo.Model.MyUser;
import com.example.demo.Service.ServiceUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Repository
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final ServiceUser serviceUser;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        List<MyUser>myUsers=serviceUser.getAllUsers();
        return ResponseEntity.status(200).body(myUsers);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){

        serviceUser.register(myUser);
        return ResponseEntity.status(200).body("registered");
    }


    @PostMapping("/login")
    public ResponseEntity login(){

        return ResponseEntity.status(200).body("logged");
    }

    @PostMapping("/logout")
    public ResponseEntity logout(){

        return ResponseEntity.status(200).body("logging");
    }
}



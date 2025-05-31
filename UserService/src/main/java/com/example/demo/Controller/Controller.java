package com.example.demo.Controller;


import com.example.demo.Service.UserService;
import com.example.demo.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @Autowired
     private UserService userService;



    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void adduser( @RequestBody User user){
        userService.addUser(user);

    }

    @DeleteMapping("/delete")

    public void deleteItem(@RequestParam int id){

     userService.deleteUser(id);
    }

    @PutMapping("/update")

    public void updateItem(@RequestBody User user){

       userService.updateUser(user);
    }


    @GetMapping("/search")

    public User searchUser(@RequestParam String email){

        return   userService.searchUser(email);
    }


    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public String hello(){

        return "Hello user Service";
    }

}

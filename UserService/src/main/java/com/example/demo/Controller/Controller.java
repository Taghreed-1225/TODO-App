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

//    @DeleteMapping("/delete")
//
//    public void deleteItem(@RequestParam int id){
//
//     userService.deleteUser(id);
//    }

    @PutMapping("/update")

    public void updateItem(@RequestBody User user){

       userService.updateUser(user);
    }


    @GetMapping("/search")

    public User searchUser(@RequestParam String email){

        return   userService.searchUser(email);
    }


    @PostMapping("/forgetPassword")
    public String forgetPassword(@RequestHeader String Authorization)
    {
        return userService.forgetPassword(Authorization);

    }

    @PutMapping("/changePassword")
    public String changePassword(@RequestHeader String Authorization,@RequestHeader String otp ,@RequestBody User user  )
    {
        return userService.changePassword(Authorization,otp,user);

    }
    @PutMapping("/activateUser")
    public boolean activateUser(@RequestHeader String email, @RequestHeader String otp)
    {
        return userService.activateUser(email ,otp);
    }

    @PostMapping("/regenerateOtp")
    public String regenerateOtp(@RequestHeader String email )
    {
        return userService.regenerateOtp(email);
    }

    @PostMapping("/delete")

    public void deleteItem(@RequestBody User user){

        userService.deleteUser(user);
    }

    @GetMapping("/validateToken")
    public String validateToken (@RequestHeader String Authorization){

      return   userService.validateToken(Authorization);

    }




    @GetMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public String hello(){

        return "Hello user Service";
    }

}

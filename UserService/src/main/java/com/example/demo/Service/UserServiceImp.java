package com.example.demo.Service;

import com.example.demo.Exceptions.NotFoundException;
import com.example.demo.Repository.UserRepository;
import com.example.demo.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    @Autowired
     private UserRepository userRepository;


    @Override
    public void addUser(@Valid User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(int id) {
        if (!userRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");}
        userRepository.deleteById(id);
    }


    @Override
    public void updateUser(User user) {
        Optional<User> existingUser = userRepository.findById(user.getId());

        if (existingUser.isPresent()) {
            User user2 = existingUser.get();  // extract item
            user2.setEmail(user.getEmail());
            user2.setPassword(user.getPassword());
            user2.setEnabled(user.isEnabled());

          userRepository.save(user2);
        } else {
            throw new NotFoundException("Item not found with id: " + user.getId());
        }

    }

   @Override
    public User searchUser(String email) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        User user = new User();
        if (existingUser.isPresent()) {
            user= existingUser.get();  // extract item

        } else {
            throw new NotFoundException("User not found with email: " + email);
        }
        return user;


    }
}

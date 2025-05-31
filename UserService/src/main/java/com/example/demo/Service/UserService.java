package com.example.demo.Service;

import com.example.demo.entity.User;


public interface UserService {

    public void addUser(User user);
    public void deleteUser(int id);
    public void updateUser(User user);
    public User searchUser(String email);
}

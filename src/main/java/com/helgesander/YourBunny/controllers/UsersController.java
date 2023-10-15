package com.helgesander.YourBunny.controllers;

import com.helgesander.YourBunny.models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("profile")
public class UsersController {
    @GetMapping
    public ArrayList<User> getUsers() {
        //TODO: fix getUsers
        return new ArrayList<>();
    }
    @GetMapping("{id}")
    public User getUser() {
        //TODO: fix getUser
        return new User();
    }
    @DeleteMapping("{id}")
    public void deleteUser()
    {
        //TODO: fix deleteUser
    }
}

package com.persistenthuang.lan108admin.controller;

import com.persistenthuang.lan108admin.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

}

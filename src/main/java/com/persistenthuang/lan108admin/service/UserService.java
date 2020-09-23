package com.persistenthuang.lan108admin.service;

import com.persistenthuang.lan108admin.pojo.User;

public interface UserService {
    boolean isExistByName(String username);

    boolean isExistByEmail(String email);

    boolean isExistByID(int id);

    boolean isAuthor(int id);

    int getAuthorID(int id);

    User getByName(String username);

    User getAllMessageByName(String username);

    User getUserById(int id);

    void add(User user);

    void updateAuthor(User user);
}

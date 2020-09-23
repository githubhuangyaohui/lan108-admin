package com.persistenthuang.lan108admin.service;

import com.persistenthuang.lan108admin.pojo.LanAuthor;

import java.util.List;

public interface LanAuthorService {
    List<LanAuthor> ListAll();

    LanAuthor getByAuthorID(int authorID);

    LanAuthor getAllAuthorMessageById(int authorID);

    int addAuthor(LanAuthor lanAuthor);

    int updateAuthor(LanAuthor lanAuthor);

    boolean isExitByAuthorEmail(String email);

    boolean isExitByAuthorPhone(String phone);
}

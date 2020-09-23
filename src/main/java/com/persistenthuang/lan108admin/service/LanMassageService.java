package com.persistenthuang.lan108admin.service;

import com.persistenthuang.lan108admin.pojo.LanMassage;

import java.util.List;

public interface LanMassageService {
    boolean sendMessage(int userID, int BlogID);

    List<LanMassage> getMassageListByUserId(int userID);

    int setIsRead(int userID, int blogID);
}

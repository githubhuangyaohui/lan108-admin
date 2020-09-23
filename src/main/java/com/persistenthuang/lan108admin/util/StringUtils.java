package com.persistenthuang.lan108admin.util;

import java.util.UUID;

public class StringUtils {
    public static String getUUID32() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}

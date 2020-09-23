package com.persistenthuang.lan108admin.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.persistenthuang.lan108admin.config.BlogStatus;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("userRole", 109, metaObject);
        this.setFieldValByName("userAuthorId", 0, metaObject);
        this.setFieldValByName("userRegisterTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("userIsDelete", false, metaObject);
        this.setFieldValByName("userLevel", 0, metaObject);

        this.setFieldValByName("blogsCreateData", LocalDateTime.now(), metaObject);
        this.setFieldValByName("blogsStatus", BlogStatus.TEMP, metaObject);
        this.setFieldValByName("blogsPassword", "123456", metaObject);

        this.setFieldValByName("authorCreateData", LocalDateTime.now(), metaObject);
        this.setFieldValByName("authorIsDelete", false, metaObject);

        this.setFieldValByName("rolesState", 0, metaObject);
        this.setFieldValByName("msgCreatData", LocalDateTime.now(), metaObject);
        this.setFieldValByName("msgIdRead", false, metaObject);
        this.setFieldValByName("msgType", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}

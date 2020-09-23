package com.persistenthuang.lan108admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.persistenthuang.lan108admin.mapper")
public class Lan108AdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(Lan108AdminApplication.class, args);
    }

}

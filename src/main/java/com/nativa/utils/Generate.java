package com.nativa.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

public class Generate {

    public static String uuid(){
        return UUID.randomUUID().toString();
    }
    public static  String hashPassword(String password){
        BCryptPasswordEncoder hashPassowrd = new BCryptPasswordEncoder();
        return hashPassowrd.encode(password);
    }
}

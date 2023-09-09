package com.assignment.client.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

public class BCryptUtil {
    public static String encodePassword(String password) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        return encryptedPassword;
    }

    public static Boolean matchPassword(String encryptedPassword, String rawPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Boolean matched = bCryptPasswordEncoder.matches(rawPassword,encryptedPassword);
        return matched;
    }
}

package com.assignment.client.utility;

import com.assignment.client.dto.UserRegisterDto;
import com.assignment.client.entity.User;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class Utils {

    public static User convertUserRegDtoToUser(UserRegisterDto userRegisterDto) {
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(BCryptUtil.encodePassword(userRegisterDto.getPassword()));
        user.setPhoneNumber(userRegisterDto.getPhoneNumber());
        log.info("Final User::{}", user);
        return user;
    }
}

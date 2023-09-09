package com.assignment.client.controller;

import com.assignment.client.dto.UserLoginDto;
import com.assignment.client.dto.UserRegisterDto;
import com.assignment.client.entity.User;
import com.assignment.client.service.UserService;
import com.assignment.client.utility.BCryptUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user-register")
    public ResponseEntity<?> userRegister(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        log.info("User Register::{}", userRegisterDto);
        try {
            User checkExistingUserEmail = userService.findByUserEmail(userRegisterDto.getEmail());
            if(Objects.nonNull(checkExistingUserEmail)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already Exists");
            }
            userService.createUser(userRegisterDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/user-login")
    public ResponseEntity<?> userLogin(@Valid @RequestBody UserLoginDto userLoginDto) {
        try {
            User checkExistingUser = userService.findByUserEmail(userLoginDto.getEmail());
            if(Objects.isNull(checkExistingUser)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Email Found");
            }
            String password = checkExistingUser.getPassword();
            if(Boolean.FALSE.equals(BCryptUtil.matchPassword(checkExistingUser.getPassword(), password))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password Wrong");
            }
            return new ResponseEntity<>(checkExistingUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getUserSearch(@PathVariable("userId") UUID userId) {
        try {
            Optional<User> user = userService.findByUserId(userId);
            if (!user.isPresent()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseEntity.ok(user.get());
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

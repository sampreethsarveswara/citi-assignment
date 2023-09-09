package com.assignment.client.service;

import com.assignment.client.dto.UserRegisterDto;
import com.assignment.client.entity.User;
import com.assignment.client.repository.UserRepository;
import com.assignment.client.utility.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserRegisterDto userRegisterDto) {
        User user = Utils.convertUserRegDtoToUser(userRegisterDto);
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User findByUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public Optional<User> findByUserId(UUID userId) {
        return userRepository.findById(userId);
    }
}

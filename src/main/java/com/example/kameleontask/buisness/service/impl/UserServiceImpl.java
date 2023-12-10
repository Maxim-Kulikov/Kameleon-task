package com.example.kameleontask.buisness.service.impl;

import com.example.kameleontask.buisness.service.UserService;
import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.data.repository.UserRepository;
import com.example.kameleontask.presentation.dto.request.SaveUserReq;
import com.example.kameleontask.presentation.dto.response.UserResp;
import com.example.kameleontask.util.exception.conflict.UserNotFoundException;
import com.example.kameleontask.util.exception.conflict.UserAlreadyExistException;
import com.example.kameleontask.util.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MainMapper mapper;

    @Override
    public UserResp saveUser(SaveUserReq saveUserReq) {
        if (userRepository.findByEmailOrUsername(saveUserReq.getEmail(), saveUserReq.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(saveUserReq.getEmail(), saveUserReq.getUsername());
        }

        return mapper.toUserResp(userRepository.save(
                User.builder()
                        .email(saveUserReq.getEmail())
                        .password(saveUserReq.getPassword())
                        .username(saveUserReq.getUsername())
                        .createdAt(LocalDateTime.now(UTC))
                        .build())
        );
    }

    @Override
    public User findUserOrElseThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

}

package com.example.kameleontask.buisness.service;

import com.example.kameleontask.data.entity.User;
import com.example.kameleontask.presentation.dto.request.SaveUserReq;
import com.example.kameleontask.presentation.dto.response.UserResp;

public interface UserService {
    UserResp saveUser(SaveUserReq saveUserReq);

    User findUserOrElseThrow(Long id);
}

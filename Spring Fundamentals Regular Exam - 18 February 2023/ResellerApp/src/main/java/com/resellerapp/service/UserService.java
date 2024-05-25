package com.resellerapp.service;

import com.resellerapp.model.dto.user.UserLoginDTO;
import com.resellerapp.model.dto.user.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}

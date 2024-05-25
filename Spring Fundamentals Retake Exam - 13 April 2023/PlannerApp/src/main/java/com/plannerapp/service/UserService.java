package com.plannerapp.service;

import com.plannerapp.model.dto.user.UserLoginDTO;
import com.plannerapp.model.dto.user.UserRegisterDTO;

public interface UserService {

    boolean register(UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}

package com.paintingscollectors.service;

import com.paintingscollectors.model.dto.user.UserLoginDTO;
import com.paintingscollectors.model.dto.user.UserRegisterDTO;

public interface UserService {
    boolean register (UserRegisterDTO userRegisterDTO);

    boolean login(UserLoginDTO userLoginDTO);

    void logout();
}

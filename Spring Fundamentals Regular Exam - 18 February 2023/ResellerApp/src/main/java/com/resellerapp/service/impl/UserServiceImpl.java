package com.resellerapp.service.impl;

import com.resellerapp.init.LoggedUser;
import com.resellerapp.model.dto.user.UserLoginDTO;
import com.resellerapp.model.dto.user.UserRegisterDTO;
import com.resellerapp.model.entity.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, LoggedUser loggedUser, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }

        Optional<User> optionalUser = this.userRepository.
                findByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());

        if (optionalUser.isPresent()) {
            return false;
        }

        userRegisterDTO.setPassword(this.passwordEncoder.encode(userRegisterDTO.getPassword()));

        User user = this.modelMapper.map(userRegisterDTO, User.class);

        this.userRepository.save(user);
        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {

        String username = userLoginDTO.getUsername();

        User user = this.userRepository.findByUsername(username);

        if (user != null && this.passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            this.loggedUser.login(username);

            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        this.loggedUser.logout();
    }
}

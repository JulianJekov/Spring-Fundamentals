package com.paintingscollectors.service.impl;

import com.paintingscollectors.model.dto.user.UserLoginDTO;
import com.paintingscollectors.model.dto.user.UserRegisterDTO;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.UserRepository;
import com.paintingscollectors.service.UserService;
import com.paintingscollectors.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public boolean register(UserRegisterDTO userRegisterDTO) {

        if (!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())) {
            return false;
        }
        Optional<User> optionalUser =
                userRepository.findByUsernameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail());
        if (optionalUser.isPresent()) {
            return false;
        }

        User user = modelMapper.map(userRegisterDTO, User.class);

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean login(UserLoginDTO userLoginDTO) {
        Optional<User> optionalUser = userRepository.findByUsername(userLoginDTO.getUsername());

        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();

        if (!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())) {
            return false;
        }

        currentUser.login(user.getUsername(), user.getId());

        return true;
    }

    @Override
    public void logout() {
        currentUser.logout();
    }
}

package com.bonappetit.service.impl;

import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.RecipeRepository;
import com.bonappetit.repo.UserRepository;
import com.bonappetit.service.UserService;
import com.bonappetit.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;
    private final RecipeRepository recipeRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, CurrentUser currentUser, RecipeRepository recipeRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
        this.recipeRepository = recipeRepository;
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

    @Override
    @Transactional
    public boolean addRecipeToFavourite(Long id) {

        Optional<User> optionalUser = userRepository.findByUsername(currentUser.getUsername());
        if (optionalUser.isEmpty()) {
            return false;
        }
        User user = optionalUser.get();

        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        if (optionalRecipe.isEmpty()) {
            return false;
        }
        Recipe recipe = optionalRecipe.get();

        if (user.getFavouriteRecipes().contains(recipe)) {
           return false;
        }
        user.getFavouriteRecipes().add(recipe);
        userRepository.save(user);

        return true;
    }
}

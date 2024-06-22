package com.bonappetit.config;

import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<String, String> passwordConverter =
                context -> context.getSource() == null ? null : passwordEncoder().encode(context.getSource());

        modelMapper.createTypeMap(UserRegisterDTO.class, User.class)
                .addMappings(mapping -> mapping
                        .using(passwordConverter)
                        .map(UserRegisterDTO::getPassword, User::setPassword));

        return modelMapper;
    }

}

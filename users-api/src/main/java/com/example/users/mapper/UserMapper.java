package com.example.users.mapper;

import com.example.users.dto.UserRequestDTO;
import com.example.users.dto.UserResponseDTO;
import com.example.users.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setRole(dto.getRole());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getRole()
        );
    }
}


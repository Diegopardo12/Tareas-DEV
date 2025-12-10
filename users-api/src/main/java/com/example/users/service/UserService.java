package com.example.users.service;

import com.example.users.dto.UserRequestDTO;
import com.example.users.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO create(UserRequestDTO request);
    List<UserResponseDTO> findAll();

    UserResponseDTO findById(Long id);
}


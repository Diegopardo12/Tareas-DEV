package com.example.users.service.impl;

import com.example.users.dto.UserRequestDTO;
import com.example.users.dto.UserResponseDTO;
import com.example.users.exception.ResourceNotFoundException;
import com.example.users.mapper.UserMapper;
import com.example.users.model.User;
import com.example.users.repository.UserRepository;
import com.example.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO request) {
        User user = mapper.toEntity(request);
        User saved = repository.save(user);
        return mapper.toResponseDTO(saved);
    }
    @Override
    public List<UserResponseDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public UserResponseDTO findById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id" + id)
                );
        return mapper.toResponseDTO(user);
    }
}


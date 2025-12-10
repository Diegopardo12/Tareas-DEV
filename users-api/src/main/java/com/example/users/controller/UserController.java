package com.example.users.controller;

import com.example.users.dto.UserRequestDTO;
import com.example.users.dto.UserResponseDTO;
import com.example.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(
            @RequestBody UserRequestDTO request) {
        return ResponseEntity.ok(service.create(request));
    }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
// pruebaaaaa get---
//    @GetMapping
//    public String test() {
//        return "OK";
//    }

}

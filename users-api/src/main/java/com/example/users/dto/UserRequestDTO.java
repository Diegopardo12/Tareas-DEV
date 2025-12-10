package com.example.users.dto;

import lombok.Getter;

@Getter
public class UserRequestDTO {

    private String name;
    private String role;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String name, String role) {
        this.name = name;
        this.role = role;
    }

}

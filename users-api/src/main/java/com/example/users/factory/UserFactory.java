package com.example.users.factory;

import com.example.users.model.User;

public class UserFactory {

    private UserFactory() {}

    public static User create(String name, String role) {
        if ("ADMIN".equalsIgnoreCase(role)) {
            return new User(null, name, "ADMIN");
        }
        return new User(null, name, "USER");
    }
}

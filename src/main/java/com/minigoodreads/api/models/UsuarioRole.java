package com.minigoodreads.api.models;

import lombok.Getter;

@Getter
public enum UsuarioRole {
    USER("user"),
    ADMIN("admin");

    private final String role;

    UsuarioRole(String role) {
        this.role = role;
    }

}

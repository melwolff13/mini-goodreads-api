package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosNovoUsuario(
        @Email @NotBlank
        String email,
        @NotBlank
        String nick,
        @NotBlank
        String senha,
        @NotBlank
        String role
){}

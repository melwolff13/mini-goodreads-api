package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosLoginUsuario(
        @NotBlank
        String nick,
        @NotBlank
        @Size(min = 6)
        String senha
) {}

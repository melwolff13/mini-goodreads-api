package com.minigoodreads.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosNovoLivro(
        @NotBlank
        String titulo,
        @NotBlank
        String autor,
        @NotBlank
        String editora,
        @NotBlank
        String genero,
        @NotNull
        Integer anoPublicacao
) { }

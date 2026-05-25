package com.minigoodreads.api.DTO.request;

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
        @NotBlank
        String sinopse,
        @NotNull
        Integer anoPublicacao
) { }

package com.minigoodreads.api.DTO.request;

import com.minigoodreads.api.models.Genero;

public record LivroFiltro(
        String titulo,
        String autor,
        String genero,
        Integer anoPublicacao
) {}

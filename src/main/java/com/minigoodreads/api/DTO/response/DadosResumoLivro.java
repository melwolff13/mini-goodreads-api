package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Livro;

public record DadosResumoLivro(
        Long id,
        String titulo,
        String autor
) {
    public DadosResumoLivro(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor());
    }
}

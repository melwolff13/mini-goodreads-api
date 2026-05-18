package com.minigoodreads.api.DTO;

import com.minigoodreads.api.models.Livro;

public record DadosLivro(
        Long id,
        String titulo,
        String autor,
        String editora,
        String genero,
        Integer anoPublicacao
) {
    public DadosLivro(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getEditora(), livro.getGenero(), livro.getAnoPublicacao());
    }
}

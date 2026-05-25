package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Genero;
import com.minigoodreads.api.models.Livro;

public record DadosLivro(
        Long id,
        String titulo,
        String autor,
        String editora,
        Genero genero,
        String sinopse,
        Integer anoPublicacao
) {
    public DadosLivro(Livro livro) {
        this(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getEditora(), livro.getGenero(), livro.getSinopse(), livro.getAnoPublicacao());
    }
}

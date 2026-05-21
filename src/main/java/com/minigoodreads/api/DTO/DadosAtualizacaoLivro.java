package com.minigoodreads.api.DTO;

public record DadosAtualizacaoLivro(
        String titulo,
        String autor,
        String editora,
        String genero,
        String sinopse,
        Integer anoPublicacao
) { }


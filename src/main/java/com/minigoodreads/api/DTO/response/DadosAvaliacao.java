package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Avaliacao;
import com.minigoodreads.api.models.Livro;

import java.time.LocalDateTime;

public record DadosAvaliacao(
        Long id,
        LocalDateTime data,
        Integer estrelas,
        String comentario,
        DadosUsuario usuario,
        DadosLivro livro
) {
    public DadosAvaliacao(Avaliacao avaliacao) {
        this(avaliacao.getId(), avaliacao.getData(), avaliacao.getEstrelas(), avaliacao.getComentario(), new DadosUsuario(avaliacao.getUsuario()), new DadosLivro(avaliacao.getLivro()));
    }
}

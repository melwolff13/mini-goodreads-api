package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Avaliacao;

import java.time.LocalDateTime;

public record DadosAvaliacao(
        Long id,
        LocalDateTime data_publicacao,
        Integer estrelas,
        String comentario,
        DadosUsuario usuario,
        DadosLivro livro,
        Boolean editada,
        LocalDateTime data_edicao
) {
    public DadosAvaliacao(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getData_publicacao(),
                avaliacao.getEstrelas(),
                avaliacao.getComentario(),
                new DadosUsuario(avaliacao.getUsuario()),
                new DadosLivro(avaliacao.getLivro()),
                avaliacao.isEditada(),
                avaliacao.getData_edicao()
        );
    }
}

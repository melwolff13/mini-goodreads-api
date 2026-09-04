package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Avaliacao;

import java.time.LocalDateTime;

public record DadosAvaliacao(
        Long id,
        LocalDateTime dataPublicacao,
        Integer estrelas,
        String comentario,
        DadosUsuario usuario,
        DadosResumoLivro livro,
        Boolean editada,
        LocalDateTime dataEdicao
) {
    public DadosAvaliacao(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getDataPublicacao(),
                avaliacao.getEstrelas(),
                avaliacao.getComentario(),
                new DadosUsuario(avaliacao.getUsuario()),
                new DadosResumoLivro(avaliacao.getLivro()),
                avaliacao.isEditada(),
                avaliacao.getDataEdicao()
        );
    }
}

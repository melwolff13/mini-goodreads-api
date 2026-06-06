package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Avaliacao;
import com.minigoodreads.api.models.Livro;
import com.minigoodreads.api.models.Usuario;

import java.time.LocalDateTime;

public record DadosAvaliacao(
        Long id,
        LocalDateTime data,
        Integer estrelas,
        String comentario,
        Usuario usuario,
        Livro livro
) {
    public DadosAvaliacao(Avaliacao avaliacao) {
        this(avaliacao.getId(), avaliacao.getData(), avaliacao.getEstrelas(), avaliacao.getComentario(), avaliacao.getUsuario(), avaliacao.getLivro());
    }
}

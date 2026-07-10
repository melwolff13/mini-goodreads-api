package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.ListaDeLeitura;
import com.minigoodreads.api.models.StatusLeitura;

public record DadosLeitura(
        Long id,
        DadosUsuario usuario,
        DadosResumoLivro livro,
        StatusLeitura status
) {
    public DadosLeitura(ListaDeLeitura novaLeitura) {
        this(
                novaLeitura.getId(),
                new DadosUsuario(novaLeitura.getUsuario()),
                new DadosResumoLivro(novaLeitura.getLivro()),
                novaLeitura.getStatus()
        );
    }
}

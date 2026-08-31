package com.minigoodreads.api.DTO;

public record AvaliacaoResumoDTO(
        Long totalAvaliacoes,
        Double notaMediaAvaliacoes) {

    public AvaliacaoResumoDTO {
        if (notaMediaAvaliacoes == null) {
            notaMediaAvaliacoes = 0.0;
        }
    }
}

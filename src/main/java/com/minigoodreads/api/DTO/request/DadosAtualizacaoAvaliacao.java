package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record DadosAtualizacaoAvaliacao(
        @Min(value = 1)
        @Max(value = 5)
        Integer estrelas,
        String comentario
) {}

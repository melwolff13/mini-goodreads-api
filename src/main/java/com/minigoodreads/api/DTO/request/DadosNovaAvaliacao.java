package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DadosNovaAvaliacao(
        @NotNull
        @Min(value = 1)
        @Max(value = 5)
        Integer estrelas,
        String comentario
) {}

package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.NotNull;

public record DadosNovaLeitura(
        @NotNull
        Long livro_id,
        @NotNull
        String status
) {
}

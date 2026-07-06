package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.NotBlank;

public record DadosAtualizacaoLeitura(
        @NotBlank
        String status
) {
}

package com.minigoodreads.api.DTO.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record DadosNovaAvaliacao(
        @NotNull
        Integer estrelas,
        String comentario,
        @NotNull
        Long usuarioId
) {}

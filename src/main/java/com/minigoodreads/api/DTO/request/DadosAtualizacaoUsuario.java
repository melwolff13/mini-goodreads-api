package com.minigoodreads.api.DTO.request;

import jakarta.validation.constraints.Email;

public record DadosAtualizacaoUsuario(
        @Email
        String email,
        String nick,
        String senha
){}

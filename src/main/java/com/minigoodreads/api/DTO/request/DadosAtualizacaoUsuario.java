package com.minigoodreads.api.DTO.request;

import com.minigoodreads.api.DTO.IDadosUsuario;
import jakarta.validation.constraints.Email;

public record DadosAtualizacaoUsuario(
        @Email
        String email,
        String nick,
        String senha
) implements IDadosUsuario {}

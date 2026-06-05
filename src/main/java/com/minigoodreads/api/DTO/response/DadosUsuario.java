package com.minigoodreads.api.DTO.response;

import com.minigoodreads.api.models.Usuario;

public record DadosUsuario(
        Long id,
        String email,
        String nick
) {
    public DadosUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getEmail(), usuario.getNick());
    }
}

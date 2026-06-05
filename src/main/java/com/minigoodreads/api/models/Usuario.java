package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import com.minigoodreads.api.DTO.request.DadosNovoUsuario;
import com.minigoodreads.api.DTO.response.DadosUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nick;
    private String senha;

    public Usuario(DadosNovoUsuario dados){
        this.email = dados.email();
        this.nick = dados.nick();
        this.senha = dados.senha();
    }
    public void atualizar(DadosAtualizacaoUsuario dados){
        if(dados.email() != null){
            this.email = dados.email();
        }
        if(dados.nick() != null){
            this.nick = dados.nick();
        }
        if(dados.senha() != null){
            this.senha = dados.senha();
        }
    }

}

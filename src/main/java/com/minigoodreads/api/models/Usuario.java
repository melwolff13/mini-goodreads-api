package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String nick;
    private String senha;
    @Enumerated(value = EnumType.STRING)
    private UsuarioRole role;


    public Usuario(String email, String nick, String senha, UsuarioRole role){
        this.email = email;
        this.nick = nick;
        this.senha = senha;
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nick;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

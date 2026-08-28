package com.minigoodreads.api.service;
import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import com.minigoodreads.api.DTO.request.DadosNovoUsuario;
import com.minigoodreads.api.DTO.response.DadosUsuario;
import com.minigoodreads.api.exceptions.RegraDeNegocioException;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.models.UsuarioRole;
import com.minigoodreads.api.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public DadosUsuario registrarUsuario(DadosNovoUsuario dados) {
        verificaDadosCadastro(dados);
        String senhaCriptografada = passwordEncoder.encode(dados.senha());
        var novoUsuario = new Usuario(dados.email(), dados.nick(), senhaCriptografada, UsuarioRole.USER);
        usuarioRepository.save(novoUsuario);
        return new DadosUsuario(novoUsuario);
    }

    public DadosUsuario detalharUsuario(Long id) {
        var dadosUsuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return new DadosUsuario(dadosUsuario);
    }

    @Transactional
    public DadosUsuario atualizarUsuario(Long id, DadosAtualizacaoUsuario dados) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        String senhaCriptografada = dados.senha() != null
                ? passwordEncoder.encode(dados.senha())
                : null;
        usuario.atualizar(dados.email(), dados.nick(), senhaCriptografada);
        return new DadosUsuario(usuario);
    }

    public ResponseEntity<?> deletarUsuario(Long id) {
        usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private void verificaDadosCadastro(DadosNovoUsuario dados) {
        var erros = new ArrayList<String>();

        if (usuarioRepository.existsByEmail(dados.email())) {
            erros.add("Este e-mail já está sendo usado");
        }
        if (usuarioRepository.existsByNick(dados.nick())) {
            erros.add("Este nick já está sendo usado");
        }
        if (dados.senha().length() < 6) {
            erros.add("A senha deve conter no mínimo 6 caracteres");
        }

        if (!erros.isEmpty()) {
            throw new RegraDeNegocioException(erros);
        }
    }
}

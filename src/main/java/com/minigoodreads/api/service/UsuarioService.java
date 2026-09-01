package com.minigoodreads.api.service;
import com.minigoodreads.api.DTO.IDadosUsuario;
import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import com.minigoodreads.api.DTO.request.DadosNovoUsuario;
import com.minigoodreads.api.DTO.response.DadosUsuario;
import com.minigoodreads.api.exceptions.ConflitoException;
import com.minigoodreads.api.exceptions.RegraDeNegocioException;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.models.UsuarioRole;
import com.minigoodreads.api.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    public DadosUsuario registrarUsuario(DadosNovoUsuario dados) {
        verificaDados(dados);
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

        verificaDados(dados);

        String senhaCriptografada = dados.senha() != null
                ? passwordEncoder.encode(dados.senha())
                : null;

        usuario.atualizar(dados.email(), dados.nick(), senhaCriptografada);
        return new DadosUsuario(usuario);


    }

    public ResponseEntity<?> deletarUsuario(Long usuarioLogadoId, Long usuarioParaExcluirId) {
        var usuarioLogado = usuarioRepository.findById(usuarioLogadoId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioRepository.findById(usuarioParaExcluirId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if (usuarioLogadoId.equals(usuarioParaExcluirId) || usuarioLogado.getRole() == UsuarioRole.ADMIN) {
            usuarioRepository.deleteById(usuarioParaExcluirId);
        } else {
            throw new AccessDeniedException("Você não tem permissão para deletar esta usuário");
        }

        return ResponseEntity.noContent().build();
    }


    private void verificaDados(IDadosUsuario dados) {
        var erros = new ArrayList<String>();

        if (dados.email() != null && usuarioRepository.existsByEmail(dados.email())) {
            erros.add("Este e-mail já está sendo usado");
        }
        if (dados.nick() != null && usuarioRepository.existsByNick(dados.nick())) {
            erros.add("Este nick já está sendo usado");
        }
        if (dados.senha() != null && dados.senha().length() < 6) {
            erros.add("A senha deve conter no mínimo 6 caracteres");
        }

        if (!erros.isEmpty()) {
            throw new RegraDeNegocioException(erros);
        }
    }
}

package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import com.minigoodreads.api.DTO.request.DadosNovoUsuario;
import com.minigoodreads.api.DTO.response.DadosUsuario;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.repositories.UsuarioRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarioRepository;

    public DadosUsuario registrarUsuario(DadosNovoUsuario dados){
        var novoUsuario = new Usuario(dados);
        usuarioRepository.save(novoUsuario);
        return new DadosUsuario(novoUsuario);
    }

    public DadosUsuario detalharUsuario(Long id){
        var dadosUsuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Digita certo aí"));
        return new DadosUsuario(dadosUsuario);
    }
    @Transactional
    public DadosUsuario atualizar(Long id, DadosAtualizacaoUsuario dados){
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Digita certo ai"));
        usuario.atualizar(dados);
        return new DadosUsuario(usuario);
    }
    public ResponseEntity deletar(Long id){
        usuarioRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Não achou..."));
        usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

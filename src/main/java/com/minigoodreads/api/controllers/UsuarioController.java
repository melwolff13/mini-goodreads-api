package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import com.minigoodreads.api.DTO.request.DadosNovoUsuario;
import com.minigoodreads.api.DTO.response.DadosUsuario;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<DadosUsuario> adicionar(@RequestBody @Valid DadosNovoUsuario dados){
        return ResponseEntity.ok(usuarioService.registrarUsuario(dados));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DadosUsuario> detalhar(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.detalharUsuario(id));
    }

    @PutMapping
    public ResponseEntity<DadosUsuario> atualizar(@AuthenticationPrincipal Usuario usuarioLogado, @RequestBody @Valid DadosAtualizacaoUsuario dados){
        return ResponseEntity.ok(usuarioService.atualizarUsuario(usuarioLogado.getId(), dados));
    }

    @DeleteMapping("/{usuarioParaExcluirId}")
    public ResponseEntity<?> deletar(@AuthenticationPrincipal Usuario usuarioLogado, @PathVariable Long usuarioParaExcluirId){
        return usuarioService.deletarUsuario(usuarioLogado.getId(), usuarioParaExcluirId);
    }



}

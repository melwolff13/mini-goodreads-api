package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoUsuario;
import com.minigoodreads.api.DTO.request.DadosNovoUsuario;
import com.minigoodreads.api.DTO.response.DadosUsuario;
import com.minigoodreads.api.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    @Autowired private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid DadosNovoUsuario dados){
        return ResponseEntity.ok(usuarioService.registrarUsuario(dados));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> detalhar(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.detalharUsuario(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados){
        return ResponseEntity.ok(usuarioService.atualizar(id,dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        return usuarioService.deletar(id);
    }



}

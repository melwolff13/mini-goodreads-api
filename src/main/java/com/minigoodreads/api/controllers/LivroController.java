package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.DadosAtualizacaoLivro;
import com.minigoodreads.api.DTO.DadosLivro;
import com.minigoodreads.api.DTO.DadosNovoLivro;
import com.minigoodreads.api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired private LivroService livroService;

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody @Valid DadosNovoLivro dados) {
        return ResponseEntity.ok(livroService.registrarLivro(dados));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosLivro> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.detalharLivro(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosLivro> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoLivro dados) {
        return ResponseEntity.ok(livroService.atualizarLivro(id, dados));
    }

}

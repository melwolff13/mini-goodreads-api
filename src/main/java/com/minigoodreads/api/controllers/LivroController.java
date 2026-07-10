package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoLivro;
import com.minigoodreads.api.DTO.request.LivroFiltro;
import com.minigoodreads.api.DTO.response.DadosLivro;
import com.minigoodreads.api.DTO.request.DadosNovoLivro;
import com.minigoodreads.api.DTO.response.DadosResumoLivro;
import com.minigoodreads.api.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<DadosResumoLivro>> listar(LivroFiltro filtro, @PageableDefault(size = 10, sort = "titulo") Pageable paginacao) {
        return ResponseEntity.ok(livroService.listarLivros(filtro, paginacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DadosLivro> atualizar(@PathVariable Long id, @RequestBody DadosAtualizacaoLivro dados) {
        return ResponseEntity.ok(livroService.atualizarLivro(id, dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return livroService.deletarLivro(id);
    }

}

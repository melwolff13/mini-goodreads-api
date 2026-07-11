package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoAvaliacao;
import com.minigoodreads.api.DTO.response.DadosAvaliacao;
import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import com.minigoodreads.api.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AvaliacaoController {

    @Autowired private AvaliacaoService avaliacaoService;

    @PostMapping("/livros/{livroId}/avaliacoes")
    public ResponseEntity<DadosAvaliacao> avaliar(@PathVariable Long livroId, @RequestBody @Valid DadosNovaAvaliacao dados) {
        return ResponseEntity.ok(avaliacaoService.adicionarAvaliacao(livroId, dados));
    }

    @GetMapping("/livros/{livroId}/avaliacoes")
    public ResponseEntity<Page<DadosAvaliacao>> listarPorLivro(@PathVariable Long livroId, @PageableDefault(size = 10, sort = "id") Pageable paginacao) {
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoesPorLivro(livroId, paginacao));
    }

    @GetMapping("/usuarios/{usuarioId}/avaliacoes")
    public ResponseEntity<Page<DadosAvaliacao>> listarPorUsuario(@PathVariable Long usuarioId, @PageableDefault(size = 10, sort = "id") Pageable paginacao) {
        return ResponseEntity.ok(avaliacaoService.listarAvaliacoesPorUsuario(usuarioId, paginacao));
    }

    @PutMapping("/avaliacoes/{id}")
    public ResponseEntity<DadosAvaliacao> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoAvaliacao dados) {
        return ResponseEntity.ok(avaliacaoService.atualizarInformacoes(id, dados));
    }

    @DeleteMapping("/avaliacoes/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return avaliacaoService.deletarAvaliacao(id);
    }
}

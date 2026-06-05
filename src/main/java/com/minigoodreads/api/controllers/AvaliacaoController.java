package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.response.DadosAvaliacao;
import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import com.minigoodreads.api.service.AvaliacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoController {

    @Autowired private AvaliacaoService avaliacaoService;

    @PostMapping("/livros/{livroId}")
    public ResponseEntity<DadosAvaliacao> avaliar(@PathVariable Long livroId, @RequestBody @Valid DadosNovaAvaliacao dados) {
        return ResponseEntity.ok(avaliacaoService.adicionarAvaliacao(livroId, dados));
    }
}

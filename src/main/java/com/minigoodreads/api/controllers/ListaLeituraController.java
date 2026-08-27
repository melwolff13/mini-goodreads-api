package com.minigoodreads.api.controllers;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoLeitura;
import com.minigoodreads.api.DTO.request.DadosNovaLeitura;
import com.minigoodreads.api.DTO.response.DadosLeitura;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.service.ListaLeituraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class ListaLeituraController {

     @Autowired private ListaLeituraService leituraService;

     @PostMapping("/leitura")
     public ResponseEntity<?> adicionar(@AuthenticationPrincipal Usuario usuarioLogado, @RequestBody @Valid DadosNovaLeitura dados) {
          return ResponseEntity.ok(leituraService.adicionarLivroALista(usuarioLogado, dados));
     }

     @GetMapping("/usuarios/{usuarioId}/leitura")
     public ResponseEntity<Page<DadosLeitura>> listar(@PathVariable Long usuarioId, @PageableDefault(size = 10, sort = "id") Pageable paginacao) {
          return ResponseEntity.ok(leituraService.exibirLista(usuarioId, paginacao));
     }

     @PutMapping("/leitura/{id}")
     public ResponseEntity<?> editar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoLeitura dados) {
          return ResponseEntity.ok(leituraService.editarLista(id, dados));
     }

     @DeleteMapping("/leitura/{id}")
     public ResponseEntity<?> deletar(@PathVariable Long id) {
          return leituraService.deletarLeitura(id);
     }
}

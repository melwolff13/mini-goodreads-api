package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoLivro;
import com.minigoodreads.api.DTO.response.DadosLivro;
import com.minigoodreads.api.DTO.request.DadosNovoLivro;
import com.minigoodreads.api.models.Livro;
import com.minigoodreads.api.repositories.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LivroService {

    @Autowired private LivroRepository livroRepository;

    public DadosLivro registrarLivro(DadosNovoLivro dados) {
        var novoLivro = new Livro(dados);
        livroRepository.save(novoLivro);
        return new DadosLivro(novoLivro);
    }

    public DadosLivro detalharLivro(Long id) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        return new DadosLivro(livro);
    }

    public Page<DadosLivro> listarLivros(Pageable paginacao) {
        return livroRepository.findAll(paginacao).map(DadosLivro::new);
    }

    @Transactional
    public DadosLivro atualizarLivro(Long id, DadosAtualizacaoLivro dados) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        livro.atualizarDados(dados);

        return new DadosLivro(livro);
    }

    public ResponseEntity<?> deletarLivro(Long id) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        livroRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

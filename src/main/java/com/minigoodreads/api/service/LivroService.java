package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.AvaliacaoResumoDTO;
import com.minigoodreads.api.DTO.request.DadosAtualizacaoLivro;
import com.minigoodreads.api.DTO.request.LivroFiltro;
import com.minigoodreads.api.DTO.response.DadosLivro;
import com.minigoodreads.api.DTO.request.DadosNovoLivro;
import com.minigoodreads.api.DTO.response.DadosResumoLivro;
import com.minigoodreads.api.models.Livro;
import com.minigoodreads.api.models.LivroSpec;
import com.minigoodreads.api.repositories.AvaliacaoRepository;
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
    @Autowired private AvaliacaoRepository avaliacaoRepository;

    public DadosLivro registrarLivro(DadosNovoLivro dados) {
        var novoLivro = new Livro(dados);
        livroRepository.save(novoLivro);
        return new DadosLivro(novoLivro, 0.0, 0);
    }

    public DadosLivro detalharLivro(Long id) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        var resumo = obterResumoAvaliacao(id);
        return new DadosLivro(livro, resumo.notaMediaAvaliacoes(), resumo.totalAvaliacoes());
    }

    public Page<DadosResumoLivro> listarLivros(LivroFiltro filtro, Pageable paginacao) {
        return livroRepository.findAll(LivroSpec.comFiltros(filtro), paginacao)
                .map(DadosResumoLivro::new);
    }

    @Transactional
    public DadosLivro atualizarLivro(Long id, DadosAtualizacaoLivro dados) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        livro.atualizarDados(dados);

        var resumo = obterResumoAvaliacao(id);
        return new DadosLivro(livro, resumo.notaMediaAvaliacoes(), resumo.totalAvaliacoes());
    }

    public ResponseEntity<?> deletarLivro(Long id) {
        var livro = livroRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        livroRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    private AvaliacaoResumoDTO obterResumoAvaliacao(Long id) {
        return avaliacaoRepository.obterResumoAvaliacao(id);
    }

}

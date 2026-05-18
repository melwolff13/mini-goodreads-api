package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.DadosLivro;
import com.minigoodreads.api.DTO.DadosNovoLivro;
import com.minigoodreads.api.models.Livro;
import com.minigoodreads.api.repositories.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

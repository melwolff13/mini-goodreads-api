package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.response.DadosAvaliacao;
import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import com.minigoodreads.api.models.Avaliacao;
import com.minigoodreads.api.repositories.AvaliacaoRepository;
import com.minigoodreads.api.repositories.LivroRepository;
import com.minigoodreads.api.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoService {

    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private LivroRepository livroRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public DadosAvaliacao adicionarAvaliacao(Long livroId, DadosNovaAvaliacao dados) {
        var livroAvaliado = livroRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        var usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        var novaAvaliacao = new Avaliacao(livroAvaliado, usuario, dados);
        avaliacaoRepository.save(novaAvaliacao);

        return new DadosAvaliacao(novaAvaliacao);
    }
}

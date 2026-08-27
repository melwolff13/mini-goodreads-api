package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoAvaliacao;
import com.minigoodreads.api.DTO.response.DadosAvaliacao;
import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import com.minigoodreads.api.exceptions.ConflitoException;
import com.minigoodreads.api.models.Avaliacao;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.repositories.AvaliacaoRepository;
import com.minigoodreads.api.repositories.LivroRepository;
import com.minigoodreads.api.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private LivroRepository livroRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public DadosAvaliacao adicionarAvaliacao(Usuario usuarioLogado, Long livroId, DadosNovaAvaliacao dados) {
        if (avaliacaoRepository.existsByLivroIdAndUsuarioId(livroId, usuarioLogado.getId())) {
            throw new ConflitoException("Você já avaliou este livro");
        }

        var livroAvaliado = livroRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        var novaAvaliacao = new Avaliacao(livroAvaliado, usuarioLogado, dados);
        avaliacaoRepository.save(novaAvaliacao);

        return new DadosAvaliacao(novaAvaliacao);
    }

    public Page<DadosAvaliacao> listarAvaliacoesPorLivro(Long livroId, Pageable paginacao) {
        return avaliacaoRepository.findAllByLivroId(livroId, paginacao).map(DadosAvaliacao::new);
    }

    public Page<DadosAvaliacao> listarAvaliacoesPorUsuario(Long usuarioId, Pageable paginacao) {
        return avaliacaoRepository.findAllByUsuarioId(usuarioId, paginacao).map(DadosAvaliacao::new);
    }

    @Transactional
    public DadosAvaliacao atualizarInformacoes(Long id, DadosAtualizacaoAvaliacao dados) {
        var avaliacao = avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
        avaliacao.atualizarInformacoes(dados);

        return new DadosAvaliacao(avaliacao);
    }

    public ResponseEntity<?> deletarAvaliacao(Long id) {
        avaliacaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
        avaliacaoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}

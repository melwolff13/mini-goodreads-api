package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoAvaliacao;
import com.minigoodreads.api.DTO.response.DadosAvaliacao;
import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import com.minigoodreads.api.exceptions.ConflitoException;
import com.minigoodreads.api.models.Avaliacao;
import com.minigoodreads.api.repositories.AvaliacaoRepository;
import com.minigoodreads.api.repositories.LivroRepository;
import com.minigoodreads.api.repositories.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvaliacaoService {

    @Autowired private AvaliacaoRepository avaliacaoRepository;
    @Autowired private LivroRepository livroRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public DadosAvaliacao adicionarAvaliacao(Long usuarioLogadoId, Long livroId, DadosNovaAvaliacao dados) {
        var usuarioLogado = usuarioRepository.findById(usuarioLogadoId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var livroAvaliado = livroRepository.findById(livroId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        if (avaliacaoRepository.existsByLivroIdAndUsuarioId(livroId, usuarioLogado.getId())) {
            throw new ConflitoException("Você já avaliou este livro");
        }

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
    public DadosAvaliacao atualizarInformacoes(Long usuarioLogadoId, Long avaliacaoId, DadosAtualizacaoAvaliacao dados) {
        usuarioRepository.findById(usuarioLogadoId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));

        var estrelas = dados.estrelas().equals(avaliacao.getEstrelas()) ? null : dados.estrelas();
        var comentario = dados.comentario().equals(avaliacao.getComentario()) ? null : dados.comentario();

        if (avaliacao.getUsuario().getId().equals(usuarioLogadoId)) {
            avaliacao.atualizarInformacoes(estrelas, comentario);
            return new DadosAvaliacao(avaliacao);
        }
        throw new AccessDeniedException("Você não tem autorização para editar esta avaliação");
    }

    public ResponseEntity<?> deletarAvaliacao(Long usuarioLogadoId, Long avaliacaoId) {
        usuarioRepository.findById(usuarioLogadoId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var avaliacao = avaliacaoRepository.findById(avaliacaoId)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));

        if (avaliacao.getUsuario().getId().equals(usuarioLogadoId)) {
            avaliacaoRepository.deleteById(avaliacaoId);
            return ResponseEntity.noContent().build();
        }
        throw new AccessDeniedException("Você não tem autorização para deletar esta avaliação");


    }
}

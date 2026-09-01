package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoLeitura;
import com.minigoodreads.api.DTO.request.DadosNovaLeitura;
import com.minigoodreads.api.DTO.response.DadosLeitura;
import com.minigoodreads.api.exceptions.ConflitoException;
import com.minigoodreads.api.models.ListaDeLeitura;
import com.minigoodreads.api.models.StatusLeitura;
import com.minigoodreads.api.models.Usuario;
import com.minigoodreads.api.repositories.ListaLeituraRepository;
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
public class ListaLeituraService {

    @Autowired private LivroRepository livroRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ListaLeituraRepository leituraRepository;

    public DadosLeitura adicionarLivroALista(Usuario usuarioLogado, DadosNovaLeitura dados) {
        if (leituraRepository.existsByUsuarioIdAndLivroId(usuarioLogado.getId(), dados.livro_id())) {
            throw new ConflitoException("Este livro já está na sua lista de leitura");
        }

        var livro = livroRepository.findById(dados.livro_id())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        var status = StatusLeitura.toEnum(dados.status());

        var novaLeitura = new ListaDeLeitura(usuarioLogado, livro, status);
        leituraRepository.save(novaLeitura);

        return new DadosLeitura(novaLeitura);
    }

    public Page<DadosLeitura> exibirLista(Long usuarioId, Pageable paginacao) {
        return leituraRepository.findAllByUsuarioId(usuarioId, paginacao).map(DadosLeitura::new);
    }

    @Transactional
    public DadosLeitura editarLeitura(Long usuarioLogadoId, Long leituraId, DadosAtualizacaoLeitura dados) {
        usuarioRepository.findById(usuarioLogadoId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var leitura = leituraRepository.findById(leituraId)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));

        if (leitura.getUsuario().getId().equals(usuarioLogadoId)) {
            leitura.atualizarStatus(StatusLeitura.toEnum(dados.status()));
            return new DadosLeitura(leitura);
        }

        throw new AccessDeniedException("Você não tem permissão para editar esta lista de leitura");
    }

    public ResponseEntity<?> deletarLeitura(Long usuarioLogadoId, Long leituraId) {
        usuarioRepository.findById(usuarioLogadoId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var leitura = leituraRepository.findById(leituraId)
                .orElseThrow(() -> new EntityNotFoundException("Leitura não encontrada"));
        if (leitura.getUsuario().getId().equals(usuarioLogadoId)) {
            leituraRepository.deleteById(leituraId);
            return ResponseEntity.noContent().build();
        }
        throw new AccessDeniedException("Você não tem permissão para deletar este item desta lista de leitura");
    }
}

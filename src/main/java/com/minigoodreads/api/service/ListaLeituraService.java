package com.minigoodreads.api.service;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoLeitura;
import com.minigoodreads.api.DTO.request.DadosNovaLeitura;
import com.minigoodreads.api.DTO.response.DadosLeitura;
import com.minigoodreads.api.models.ListaDeLeitura;
import com.minigoodreads.api.models.StatusLeitura;
import com.minigoodreads.api.repositories.ListaLeituraRepository;
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
public class ListaLeituraService {

    @Autowired private LivroRepository livroRepository;
    @Autowired private UsuarioRepository usuarioRepository;
    @Autowired private ListaLeituraRepository leituraRepository;

    public DadosLeitura adicionarLivroALista(Long usuarioId, DadosNovaLeitura dados) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        var livro = livroRepository.findById(dados.livro_id())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        var status = StatusLeitura.toEnum(dados.status());

        var novaLeitura = new ListaDeLeitura(usuario, livro, status);
        leituraRepository.save(novaLeitura);

        return new DadosLeitura(novaLeitura);
    }

    public Page<DadosLeitura> exibirLista(Long usuarioId, Pageable paginacao) {
        return leituraRepository.findAllByUsuarioId(usuarioId, paginacao).map(DadosLeitura::new);
    }

    @Transactional
    public DadosLeitura editarLista(Long id, DadosAtualizacaoLeitura dados) {
        var leitura = leituraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
        leitura.atualizarStatus(StatusLeitura.toEnum(dados.status()));
        return new DadosLeitura(leitura);
    }

    public ResponseEntity<?> deletarLeitura(Long id) {
        leituraRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Avaliação não encontrada"));
        leituraRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

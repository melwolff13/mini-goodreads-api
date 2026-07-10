package com.minigoodreads.api.repositories;

import com.minigoodreads.api.models.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {

    Page<Avaliacao> findAllByLivroId(Long livroId, Pageable paginacao);

    Page<Avaliacao> findAllByUsuarioId(Long usuarioId, Pageable paginacao);

    @Query("select a from Avaliacao a where a.livro.id = :livroId and a.usuario.id = :usuarioId")
    Optional<Avaliacao> verificaUnicidade(Long livroId, Long usuarioId);

    @Query("select ROUND(AVG(a.estrelas), 2) from Avaliacao a where a.livro.id = :id")
    Optional<Double> obterNotaMedia(Long id);

    @Query("select COUNT(*) from Avaliacao a where a.livro.id = :id")
    Integer obterTotalAvaliacoes(Long id);
}

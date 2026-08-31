package com.minigoodreads.api.repositories;

import com.minigoodreads.api.DTO.AvaliacaoResumoDTO;
import com.minigoodreads.api.models.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {

    Page<Avaliacao> findAllByLivroId(Long livroId, Pageable paginacao);

    Page<Avaliacao> findAllByUsuarioId(Long usuarioId, Pageable paginacao);

    @Query("select new com.minigoodreads.api.DTO.AvaliacaoResumoDTO(COUNT(*), ROUND(AVG(a.estrelas), 2)) from Avaliacao a where a.livro.id = :id")
    AvaliacaoResumoDTO obterResumoAvaliacao(Long id);

    boolean existsByLivroIdAndUsuarioId(Long id, Long id1);
}

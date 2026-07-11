package com.minigoodreads.api.repositories;

import com.minigoodreads.api.models.ListaDeLeitura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaLeituraRepository extends JpaRepository<ListaDeLeitura, Long> {
    Page<ListaDeLeitura> findAllByUsuarioId(Long usuarioId, Pageable paginacao);

    boolean existsByUsuarioIdAndLivroId(Long usuarioId, Long livroId);
}

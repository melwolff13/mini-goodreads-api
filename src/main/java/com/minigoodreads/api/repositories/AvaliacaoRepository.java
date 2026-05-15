package com.minigoodreads.api.repositories;

import com.minigoodreads.api.models.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao,Long> {
}

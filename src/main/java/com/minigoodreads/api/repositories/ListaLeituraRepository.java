package com.minigoodreads.api.repositories;

import com.minigoodreads.api.models.ListaDeLeitura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaLeituraRepository extends JpaRepository<ListaDeLeitura, Long> {
}

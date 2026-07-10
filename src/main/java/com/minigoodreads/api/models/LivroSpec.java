package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.request.LivroFiltro;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import jakarta.persistence.criteria.Predicate;


public class LivroSpec {
    public static Specification<Livro> comFiltros(LivroFiltro filtro) {
        return (root, query, cb) -> {
            var predicados = new ArrayList<Predicate>();

            if (filtro.titulo() != null) {
                predicados.add(cb.like(
                        cb.lower(root.get("titulo")),
                        "%" + filtro.titulo().toLowerCase() + "%"
                ));
            }
            if (filtro.autor() != null) {
                predicados.add(cb.like(
                        cb.lower(root.get("autor")),
                        "%" + filtro.autor().toLowerCase() + "%"
                ));
            }
            if (filtro.genero() != null) {
                predicados.add(cb.equal(
                        root.get("genero"),
                        filtro.genero()
                ));
            }
            if (filtro.anoPublicacao() != null) {
                predicados.add(cb.ge(
                        root.get("anoPublicacao"),
                        filtro.anoPublicacao()
                ));
            }

            return cb.and(predicados.toArray(new Predicate[0]));

        };
    }
}

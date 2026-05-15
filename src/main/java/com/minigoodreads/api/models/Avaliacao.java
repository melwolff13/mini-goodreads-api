package com.minigoodreads.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "avaliacoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Avaliacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private int estrelas;
    private String comentario;
}

package com.minigoodreads.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lista_leitura", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"usuario_id", "livro_id"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListaDeLeitura {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;
    @Enumerated(EnumType.STRING)
    private StatusLeitura status;
}

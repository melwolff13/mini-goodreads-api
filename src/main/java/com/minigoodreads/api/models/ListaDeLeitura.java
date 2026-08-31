package com.minigoodreads.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Livro livro;
    @Enumerated(EnumType.STRING)
    private StatusLeitura status;

    public ListaDeLeitura(Usuario usuario, Livro livro, StatusLeitura status) {
        this.usuario = usuario;
        this.livro = livro;
        this.status = status;
    }

    public void atualizarStatus(StatusLeitura novoStatus) {
        this.status = novoStatus;
    }
}

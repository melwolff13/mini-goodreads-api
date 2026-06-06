package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoAvaliacao;
import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    private LocalDateTime data;

    public Avaliacao(Livro livro, Usuario usuario, DadosNovaAvaliacao dados) {
        this.comentario = dados.comentario();
        this.estrelas = dados.estrelas();
        this.livro = livro;
        this.usuario = usuario;
        this.data = LocalDateTime.now();
    }

    public void atualizarInformacoes(DadosAtualizacaoAvaliacao dados) {
        if (dados.estrelas() != null) {
            this.estrelas = dados.estrelas();
        }
        if (dados.comentario() != null) {
            this.comentario = dados.comentario();
        }
    }
}

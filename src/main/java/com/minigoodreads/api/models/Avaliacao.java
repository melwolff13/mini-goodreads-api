package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.request.DadosNovaAvaliacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Livro livro;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Usuario usuario;
    private int estrelas;
    private String comentario;
    private LocalDateTime dataPublicacao;
    private boolean editada;
    private LocalDateTime dataEdicao;

    public Avaliacao(Livro livro, Usuario usuario, DadosNovaAvaliacao dados) {
        this.comentario = dados.comentario();
        this.estrelas = dados.estrelas();
        this.livro = livro;
        this.usuario = usuario;
        this.dataPublicacao = LocalDateTime.now();
        this.editada = false;
        this.dataEdicao = null;
    }

    public void atualizarInformacoes(Integer estrelas, String comentario) {
        if (estrelas != null || comentario != null) {
            this.dataEdicao = LocalDateTime.now();
            this.editada = true;
        }
        if (estrelas != null) {
            this.estrelas = estrelas;
        }
        if (comentario != null) {
            this.comentario = comentario;
        }
    }
}

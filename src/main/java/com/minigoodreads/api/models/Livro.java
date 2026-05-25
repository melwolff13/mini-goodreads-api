package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.request.DadosAtualizacaoLivro;
import com.minigoodreads.api.DTO.request.DadosNovoLivro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private String editora;
    @Enumerated(value = EnumType.STRING)
    private Genero genero;
    private String sinopse;
    private int anoPublicacao;

    public Livro(DadosNovoLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.editora = dados.editora();
        this.genero = Genero.toEnum(dados.genero());
        this.sinopse = dados.sinopse();
        this.anoPublicacao = dados.anoPublicacao();
    }

    public void atualizarDados(DadosAtualizacaoLivro dados) {
        if (dados.titulo() != null)  {
            this.titulo = dados.titulo();
        }
        if (dados.autor() != null)  {
            this.autor = dados.autor();
        }
        if (dados.editora() != null)  {
            this.editora = dados.editora();
        }
        if (dados.genero() != null)  {
            this.genero = Genero.toEnum(dados.genero());
        }
        if (dados.anoPublicacao() != null)  {
            this.titulo = dados.titulo();
        }
        if (dados.sinopse() != null)  {
            this.sinopse = dados.sinopse();
        }
    }
}

package com.minigoodreads.api.models;

import com.minigoodreads.api.DTO.DadosNovoLivro;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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
    private String genero;
    private int anoPublicacao;

    public Livro(DadosNovoLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor();
        this.editora = dados.editora();
        this.genero = dados.genero();
        this.anoPublicacao = dados.anoPublicacao();
    }
}

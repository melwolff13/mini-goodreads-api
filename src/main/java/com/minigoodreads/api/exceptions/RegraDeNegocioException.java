package com.minigoodreads.api.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class RegraDeNegocioException extends RuntimeException {

    private final List<String> erros;

    public RegraDeNegocioException(List<String> erros) {
        super("Erro de validação");
        this.erros = erros;
    }

}

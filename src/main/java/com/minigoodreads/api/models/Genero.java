package com.minigoodreads.api.models;

public enum Genero {
    AVENTURA,
    BIOGRAFIA,
    COMEDIA,
    CONTO,
    DESENVOLVIMENTO_PESSOAL,
    DISTOPIA,
    DRAMA,
    FANTASIA,
    FICCAO_CIENTIFICA,
    INFANTIL,
    MISTERIO,
    NAO_FICCAO,
    POESIA,
    RELIGIAO,
    ROMANCE,
    SUSPENSE,
    TERROR;

    public static Genero toEnum(String stringGenero) {
        for (Genero genero : Genero.values()) {
            if (stringGenero.equalsIgnoreCase(genero.toString())) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Gênero não encontrado: " + stringGenero);
    }
}


package com.minigoodreads.api.models;

public enum StatusLeitura {
    QUERO_LER,
    LENDO,
    LIDO;

    public static StatusLeitura toEnum(String stringStatus) {
        for (StatusLeitura status : StatusLeitura.values()) {
            if (stringStatus.equalsIgnoreCase(status.toString())) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status não encontrado: " + stringStatus);
    }
}

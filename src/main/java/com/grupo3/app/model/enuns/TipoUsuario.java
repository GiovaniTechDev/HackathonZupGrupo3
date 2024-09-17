package com.grupo3.app.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

// @formatter:off
@AllArgsConstructor
@Getter
public enum TipoUsuario {
    TALENTO("Talento"),
    RECRUTADOR("Recrutador");

    private final String valor;
}

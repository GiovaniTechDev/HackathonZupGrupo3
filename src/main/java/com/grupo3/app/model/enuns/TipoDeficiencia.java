package com.grupo3.app.model.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

// @formatter:off
@AllArgsConstructor
@Getter
public enum TipoDeficiencia {
    AUDITIVA("Auditiva"),
    FISICA("Física"),
    INTELECTUAL("Intelectual"),
    VISUAL("Visual"),
    MULTIPLA("Múltipla"),
    OUTRA("Outra");

    private final String valor;
}

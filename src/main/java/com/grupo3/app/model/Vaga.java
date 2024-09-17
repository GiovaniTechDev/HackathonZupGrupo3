package com.grupo3.app.model;

import com.grupo3.app.model.enuns.StatusVaga;

import java.util.List;
import java.util.UUID;

public class Vaga {

    private UUID id;

    private String titulo;

    private String descricao;

    private String localizacao;

    private List<String> requisitos;

    private StatusVaga status;

}

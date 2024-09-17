package com.grupo3.app.Controller.form;

import com.grupo3.app.model.enuns.TipoDeficiencia;
import com.grupo3.app.validation.email.EmailExisting;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@EmailExisting(message = "E-mail já cadastrado, por favor informe outro")
public class TalentoFormDto {

    private UUID id;

    @Size(min = 3, max = 300, message = "O campo nome deve ter entre 3 e 300 caracteres")
    private String nome;

    @Email(message = "O e-mail não é válido")
    private String email;

    private String senha;

    @NotNull(message = "O tipo de tipo de deficiência é obrigatório")
    private TipoDeficiencia tipoDeficiencia;

    private String acessibilidadeNecessaria;

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "USUARIO FORM DTO: >>NOME: " + this.nome;
    }

}

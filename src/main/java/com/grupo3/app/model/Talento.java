package com.grupo3.app.model;

import com.grupo3.app.model.enuns.TipoDeficiencia;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DiscriminatorValue("Talento")
@DynamicUpdate
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Talento extends Usuario {

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoDeficiencia tipoDeficiencia;

    private String acessibilidadeNecessaria;

    private String competencias;

    @Override
    public String toString() {
        return "USUARIO TALENTO: " + this.getId();
    }
}

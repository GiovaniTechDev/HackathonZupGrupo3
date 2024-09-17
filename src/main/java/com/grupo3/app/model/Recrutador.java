package com.grupo3.app.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DiscriminatorValue("Recrutador")
@DynamicUpdate
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public class Recrutador extends Usuario {

    private String setor;

    private String cnpj;

//	@OneToMany(mappedBy = "recrutador")
//	private List<Vaga> vagasAbertas = new ArrayList<>();

    @Override
    public String toString() {
        return "RECRUTADOR EMPRESA: " + this.getId();
    }

}

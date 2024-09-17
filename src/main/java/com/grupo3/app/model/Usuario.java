package com.grupo3.app.model;

import com.grupo3.app.model.enuns.TipoUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "classe")
@DynamicUpdate
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @NotNull
    private String nome;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String senha;

    @NotNull
    private Boolean ativo = true;

    @Column(updatable = false)
    @NotNull
    private LocalDateTime criadoEm;

    @NotNull
    private LocalDateTime atualizadoEm;

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "USUARIO MODEL: " + id;
    }

}

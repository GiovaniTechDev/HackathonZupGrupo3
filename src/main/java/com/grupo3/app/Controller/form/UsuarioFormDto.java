package com.grupo3.app.Controller.form;

import com.grupo3.app.model.enuns.TipoUsuario;
import com.grupo3.app.validation.email.EmailExisting;
import com.grupo3.app.validation.password.PasswordsMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@EmailExisting(message = "E-mail já cadastrado, por favor informe outro")
@PasswordsMatch(message = "As senhas não coincidem. Certifique-se de digitar a mesma senha duas vezes")
public class UsuarioFormDto {

    private UUID id;

    @NotNull(message = "O tipo de usuário não pode ser nulo, selecione um tipo de usuário")
    private TipoUsuario tipoUsuario;

    @Size(min = 3, max = 300, message = "O campo nome deve ter entre 3 e 300 caracteres")
    private String usuarioNome;

    @Email(message = "O e-mail não é válido")
    private String email;

    @Size(min = 8, max = 200, message = "A senha deve ter entre 8 e 200 caracteres")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!\"#$%&' ()*+, -./:;<=>?@]_*|}~).{8,200}$",
             message = "A senha deve conter ao menos um dígito, uma letra minúscula, uma letra maiúscula e um caractere especial")
    private String senha;

    private String confirmacaoDeSenha;

    private Boolean ativo = true;

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return "USUARIO FORM DTO: >>NOME: " + this.usuarioNome;
    }

}

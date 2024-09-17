package com.grupo3.app.validation.password;

import com.grupo3.app.Controller.form.UsuarioFormDto;
import com.grupo3.app.model.Usuario;
import com.grupo3.app.repository.usuario.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Optional;

@AllArgsConstructor
@Component
public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, UsuarioFormDto> {

	private final UsuarioRepository usuarioRepository;

	@Override
	public void initialize(PasswordsMatch constraintAnnotation) {
	}

	@Override
	public boolean isValid(UsuarioFormDto usuarioFormDto, ConstraintValidatorContext context) {
		// Se o usuário é novo, apenas verifica se a senha e a confirmação são iguais
		if (usuarioFormDto.isNew()) {
			return checkPasswordsMatch(usuarioFormDto, context);
		}
		else {
			// Recupera o usuário existente
			Optional<Usuario> usuarioExistenteOpt = usuarioRepository.findByEmail(usuarioFormDto.getEmail());
			if (usuarioExistenteOpt.isPresent()) {
				Usuario usuarioExistente = usuarioExistenteOpt.get();
				// Se a senha fornecida é diferente da senha existente, verifica se a nova
				// senha e a confirmação são iguais
				if (!usuarioExistente.getSenha().equals(usuarioFormDto.getSenha())) {
					return checkPasswordsMatch(usuarioFormDto, context);
				}

				if (!StringUtils.hasText(usuarioFormDto.getSenha())) {
					usuarioFormDto.setSenha(usuarioExistente.getSenha());
					return true;
				}
			}
			// Se o usuário existente não foi encontrado ou as senhas são iguais considera
			// válido
			return true;
		}
	}

	private boolean checkPasswordsMatch(UsuarioFormDto usuarioFormDto, ConstraintValidatorContext context) {
		if (!usuarioFormDto.getSenha().equals(usuarioFormDto.getConfirmacaoDeSenha())) {
			context.disableDefaultConstraintViolation();
			String message = context.getDefaultConstraintMessageTemplate();
			context.buildConstraintViolationWithTemplate(message)
				.addPropertyNode("confirmacaoDeSenha")
				.addConstraintViolation();
			return false;
		}
		return true;
	}

}

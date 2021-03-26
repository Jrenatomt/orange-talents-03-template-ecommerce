package com.renato.mercadolivre.validation;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.renato.mercadolivre.usuario.Usuario;
import com.renato.mercadolivre.usuario.UsuarioRepository;
import com.renato.mercadolivre.usuario.UsuarioRequest;

@Component
public class ProibeUsuarioComEmailDuplicado implements Validator{
	
	private UsuarioRepository usuarioRepository;
	
	public ProibeUsuarioComEmailDuplicado(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		UsuarioRequest request = (UsuarioRequest) target;
		Optional<Usuario> usuario = usuarioRepository.findByLogin(request.getLogin());
		
		if(usuario.isPresent()) {
			errors.rejectValue("login",null, "email ja cadastrado no sistema");
		}	
	}
}

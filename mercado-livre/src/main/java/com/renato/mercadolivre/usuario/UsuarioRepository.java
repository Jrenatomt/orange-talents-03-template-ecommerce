package com.renato.mercadolivre.usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

	Usuario findByEmail(String username);
	
}

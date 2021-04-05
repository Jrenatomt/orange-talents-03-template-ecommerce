package com.renato.mercadolivre.email;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.mercadolivre.pergunta.Pergunta;

@Service
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getUsuarioInteressado().getEmail()
				,"novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getEmail());
	}

}

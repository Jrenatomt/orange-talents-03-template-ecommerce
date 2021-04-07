package com.renato.mercadolivre.email;

import java.io.IOException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.mercadolivre.finalizaCompra.Pedido;
import com.renato.mercadolivre.pergunta.Pergunta;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class Emails {

	@Autowired
	private Mailer mailer;
	
	@Autowired
	private SendGrid sendGrid;
	
	public void sendEmail(SendGridEmail obj) {
		Email from = new Email(obj.getFromEmail(), obj.getFromName());
		Email to = new Email(obj.getTo());
		Content content = new Content(obj.getContentType(), obj.getBody());
		Mail mail = new Mail(from, obj.getSubject(), to, content);
		
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");;
			request.setBody(mail.build());
			Response response = sendGrid.api(request);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>","Nova pergunta...",pergunta.getUsuarioInteressado().getEmail()
				,"novapergunta@nossomercadolivre.com",pergunta.getDonoProduto().getEmail());
	}
	
	public void novaPedido(Pedido novoPedido) {
		mailer.send("De: " + "pedido@mercadolivre.com",
				"Você tem uma nova compra:",
				"E-mail Cliente: " + novoPedido.getComprador().getEmail(),
				"Vendedor " + novoPedido.getDonoProduto().getEmail(),
				"Produto " + novoPedido);
	}
	
	public void erroCompra(Pedido novoPedido) {
		mailer.send("De: " + "Vendedor " + novoPedido.getDonoProduto().getEmail(),
				"erro na compra compra:",
				"favor tentar novamente ",
				": ",
				"Produto " + novoPedido);
	}
}

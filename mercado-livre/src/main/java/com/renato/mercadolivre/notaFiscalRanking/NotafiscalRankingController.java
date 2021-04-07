package com.renato.mercadolivre.notaFiscalRanking;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotafiscalRankingController {

	@PostMapping(value = "/notas-fiscais")
	public void criaNota(@Valid @RequestBody NFRequest request) throws InterruptedException {
		System.out.println("criando nota "+request);
		Thread.sleep(150);
	}
	
	@PostMapping(value = "/ranking")
	public void ranking(@Valid @RequestBody RankingRequest request) throws InterruptedException {
		System.out.println("criando ranking"+request);
		Thread.sleep(150);
	}
}

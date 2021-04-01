package com.renato.mercadolivre.produto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploaderFake implements Uploader{

	@Override
	public Set<String> envia(List<MultipartFile> imagens) {
		
		return imagens.stream()
				.map(imagem -> "http://bucket.io/"
						+ imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}
}

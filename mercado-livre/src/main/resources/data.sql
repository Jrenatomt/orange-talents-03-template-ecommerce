INSERT INTO tb_usuario(email, senha) VALUES('aluno@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO tb_usuario(email, senha) VALUES('visitante1@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO tb_usuario(email, senha) VALUES('visitante2@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

INSERT INTO tb_perfis (autorizacao) VALUES ('ROLE_ADMIN');
INSERT INTO tb_perfis (autorizacao) VALUES ('ROLE_USUARIO');

INSERT INTO tb_usuario_perfis (usuario_id, perfis_id) VALUES (1, 1);
INSERT INTO tb_usuario_perfis (usuario_id, perfis_id) VALUES (2, 2);
INSERT INTO tb_usuario_perfis (usuario_id, perfis_id) VALUES (3, 2);

INSERT INTO tb_categoria (nome) Values ('Informatica');
INSERT INTO tb_categoria (nome, categoria_mae_id) Values ('Celular', 1);
INSERT INTO tb_categoria (nome, categoria_mae_id) Values ('Motorola', 2);

INSERT INTO tb_produto (DATA_CADASTRO,DESCRICAO,nome,preco,QUANTIDADE,CATEGORIA_ID,usuario_id) VALUES 
('2021-04-02 21:43:10.011483','celular semi novo','celular motorola','200',5,3,1);

INSERT INTO IMAGEM_PRODUTO (link,produto_id) Values ('http://bucket.io/renato.jpg', 1);
INSERT INTO IMAGEM_PRODUTO (link,produto_id) Values ('http://bucket.io/ryan.jpg', 1);

INSERT INTO PERGUNTA (instante,titulo,produto_id,USUARIO_INTERESSADO_ID) Values 
('2021-04-02','o celular está funcionando bem?',1,2);



INSERT INTO CARACTERISTICA_PRODUTO(DESCRICAO,NOME,PRODUTO_ID) values 
('ANDROID','celular',1);
INSERT INTO CARACTERISTICA_PRODUTO(DESCRICAO,NOME,PRODUTO_ID) values 
('7.1','jelly bean',1);
INSERT INTO CARACTERISTICA_PRODUTO(DESCRICAO,NOME,PRODUTO_ID) values 
('2018','ano',1);







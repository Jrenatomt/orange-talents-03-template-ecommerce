INSERT INTO tb_usuario(email, senha) VALUES('aluno@email.com', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');

INSERT INTO tb_perfis (autorizacao) VALUES ('ROLE_ADMIN');

INSERT INTO tb_usuario_perfis (usuario_id, perfis_id) VALUES (1, 1);

INSERT INTO tb_categoria (nome) Values ('legumes');
INSERT INTO tb_categoria (nome, categoria_mae_id) Values ('verduras', 1);




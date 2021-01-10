/* script para criar tabela usuario_permissao */
CREATE TABLE IF NOT EXISTS usuario_permissao (
	usuario_id INTEGER NOT NULL,
	permissao_id INTEGER NOT NULL,
	CONSTRAINT pk_usuario_permissao PRIMARY KEY (usuario_id, permissao_id),
	CONSTRAINT fk_usuario_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_usuario_permissao_permissao FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);


/* script para inserir permissões para usuario 1 (Admin) */
INSERT INTO usuario_permissao (usuario_id, permissao_id)
	VALUES
		(1, 1),
		(1, 2),
		(1, 3),
		(1, 4),
		(1, 5),
		(1, 6),
		(1, 7),
		(1, 8),
		(1, 9),
		(1, 10),
		(1, 11),
		(1, 12),
		(1, 13),
		(1, 14),
		(1, 15),
		(1, 16),
		(1, 17),
		(1, 18),
		(1, 19),
		(1, 20),
		(1, 21);
/*
script para criar tabela usuario_permissao
*/
CREATE TABLE IF NOT EXISTS usuario_permissao (
	usuario_id INTEGER NOT NULL,
	permissao_id INTEGER NOT NULL,
	CONSTRAINT pk_usuario_permissao PRIMARY KEY (usuario_id, permissao_id),
	CONSTRAINT fk_usuario_permissao_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_usuario_permissao_permissao FOREIGN KEY (permissao_id) REFERENCES permissao(id)
);
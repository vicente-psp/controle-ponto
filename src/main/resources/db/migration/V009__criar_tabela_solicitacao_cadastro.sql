/*
script para criar tabela solicitacao_cadastro
*/
CREATE TABLE IF NOT EXISTS solicitacao_cadastro (
	usuario_id INTEGER NOT NULL,
	secret_key CHARACTER(50) NOT NULL,
	CONSTRAINT pk_solicitacao_cadastro PRIMARY KEY (usuario_id),
	CONSTRAINT fk_solicitacao_cadastro_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
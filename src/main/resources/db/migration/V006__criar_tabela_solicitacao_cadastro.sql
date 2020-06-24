/*
script para criar tabela solicitacao_cadastro
*/
CREATE TABLE IF NOT EXISTS solicitacao_cadastro (
	id INTEGER NOT NULL,
	usuario_id INTEGER NOT NULL,
	secret_key CHARACTER(50) NOT NULL,
	CONSTRAINT u_solicitacao_cadastro_usuario UNIQUE (usuario_id),
	CONSTRAINT fk_solicitacao_cadastro_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT pk_solicitacao_cadastro PRIMARY KEY (id)
);
	
	
/*
scrip para criar sequence solicitacao_cadastro_seq
*/
CREATE SEQUENCE IF NOT EXISTS solicitacao_cadastro_seq START 1;
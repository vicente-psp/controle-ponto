/*
script para criar tabela permissao
*/
CREATE TABLE IF NOT EXISTS permissao (
	id INTEGER NOT NULL,
	descricao VARCHAR(80) NOT NULL,
	CONSTRAINT pk_permissao PRIMARY KEY (id)
);


/*
script para criar sequence permissao_seq
*/
CREATE SEQUENCE IF NOT EXISTS permissao_seq START 1;
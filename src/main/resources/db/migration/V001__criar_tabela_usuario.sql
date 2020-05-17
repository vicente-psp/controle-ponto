/*
script para criar tabela usuario
*/
CREATE TABLE IF NOT EXISTS usuario (
	id INTEGER NOT NULL,
	nome VARCHAR(150) NOT NULL,
	email VARCHAR(150) NOT NULL,
	senha VARCHAR(200) NOT NULL,
	tipo_usuario CHARACTER(3) NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY (id)
);


/*
script para criar sequence usuario_seq
*/
CREATE SEQUENCE IF NOT EXISTS usuario_seq START 1;
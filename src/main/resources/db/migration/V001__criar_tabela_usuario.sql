/*
script para criar tabela usuario
*/
CREATE TABLE IF NOT EXISTS usuario (
	id INTEGER NOT NULL,
	nome VARCHAR(150) NOT NULL,
	email VARCHAR(150) NOT NULL,
	senha VARCHAR(200) NOT NULL,
	tipo_usuario CHARACTER(3) NOT NULL,
	data_inclusao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	ativo BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT u_usuario_email UNIQUE(email),
	CONSTRAINT pk_usuario PRIMARY KEY (id)
);


/*
script para criar sequence usuario_seq
*/
CREATE SEQUENCE IF NOT EXISTS usuario_seq START 2;


/*
script para inserir primeiro registro na tabela usuário
*/
INSERT INTO
	usuario (id, nome, email, senha, tipo_usuario, ativo)
	VALUES(1, 'Admin', 'adm@controleponto.com.br', '$2a$10$6VxWhdkIljrJaDzY1Ck1ZO7RbUkJJflFgw0j3as3M8k8k9YqIdlYy', 'ADM', true);
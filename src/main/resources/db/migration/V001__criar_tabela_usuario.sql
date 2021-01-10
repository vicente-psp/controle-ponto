/* script para criar sequence usuario_seq */
CREATE SEQUENCE IF NOT EXISTS usuario_seq START 1;


/* script para criar tabela usuario */
CREATE TABLE IF NOT EXISTS usuario (
	id INTEGER NOT NULL DEFAULT nextval('usuario_seq'),
	email VARCHAR(150) NOT NULL,
	senha VARCHAR(200) NOT NULL,
	tipo_usuario CHARACTER(3) NOT NULL,
	data_inclusao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP WITH TIME ZONE,
	ativo BOOLEAN NOT NULL DEFAULT FALSE,
	cadastro_aprovado BOOLEAN NOT NULL DEFAULT FALSE,
	CONSTRAINT u_usuario_email UNIQUE(email),
	CONSTRAINT pk_usuario PRIMARY KEY (id)
);


/* script para inserir primeiro registro na tabela usuário */
INSERT INTO
	usuario (email, senha, tipo_usuario, ativo)
	VALUES('adm@controleponto.com.br', '$2a$10$ySylNvwY6oRvlenA2GhmiuewW8dl5svOnvRWpbUCW5hu8pvduNELy', 'ADM', true);
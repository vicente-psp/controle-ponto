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
CREATE SEQUENCE IF NOT EXISTS permissao_seq START 7;


/*
script para inserir registros (permissões para usuario) na tabela permissao
*/
INSERT INTO permissao (id, descricao)
	VALUES
		(1, 'ROLE_CADASTRAR_USUARIO'),
		(2, 'ROLE_PESQUISAR_USUARIO'),
		(3, 'ROLE_REMOVER_USUARIO'),
		(4, 'ROLE_CADASTRAR_USUARIO_PERMISSAO'),
		(5, 'ROLE_PESQUISAR_USUARIO_PERMISSAO'),
		(6, 'ROLE_REMOVER_USUARIO_PERMISSAO');
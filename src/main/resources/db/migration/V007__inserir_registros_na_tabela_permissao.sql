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
	
ALTER SEQUENCE permissao_seq RESTART WITH 7;
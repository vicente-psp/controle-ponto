/*
scripts para adicionar restrição na coluna usuario_id
*/	
ALTER TABLE solicitacao_cadastro 
	ADD CONSTRAINT u_solicitacao_cadastro_usuario UNIQUE (usuario_id);
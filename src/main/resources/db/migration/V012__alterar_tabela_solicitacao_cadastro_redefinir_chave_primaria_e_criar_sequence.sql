/*
scripts para redefinir chave primária
*/
ALTER TABLE solicitacao_cadastro 
	DROP CONSTRAINT pk_solicitacao_cadastro;
	
ALTER TABLE solicitacao_cadastro 
	ADD COLUMN id INTEGER NOT NULL,
	ADD CONSTRAINT pk_solicitacao_cadastro PRIMARY KEY (id);
	
	
/*
scrip para criar sequence solicitacao_cadastro_seq
*/
CREATE SEQUENCE IF NOT EXISTS solicitacao_cadastro_seq START 1;
/*
script para tornar coluna data única, não pode ter registros com mesma data
*/
CREATE TABLE IF NOT EXISTS empresa (
	id INTEGER NOT NULL,
	razao_social VARCHAR(150) NOT NULL,
	cnpj CHARACTER(14) NOT NULL,
	CONSTRAINT pk_empresa_id PRIMARY KEY(id)
);
	
/*
script para criar sequence empresa_seq
*/
CREATE SEQUENCE IF NOT EXISTS empresa_seq START 1;
/*
script para criar colunas
*/
ALTER TABLE usuario
	ADD COLUMN data_inclusao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	ADD COLUMN data_alteracao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
	ADD COLUMN ativo BOOLEAN DEFAULT false;
	
	
/*
script para preencher coluna data_inclusao do registro 1 (Admin)
*/
UPDATE usuario u SET data_inclusao = NOW() WHERE u.id = 1;
	
	
/*
script para redefinir como obrigatória coluna data_inclusao
*/
ALTER TABLE usuario
	ALTER COLUMN data_inclusao SET NOT NULL;
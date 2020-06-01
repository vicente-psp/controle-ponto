/*
script para tornar coluna data única, não pode ter registros com mesma data
*/
ALTER TABLE registro
	ADD CONSTRAINT u_registro_data UNIQUE(data);
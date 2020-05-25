/*
script para criar chave primária
*/
ALTER TABLE entrada_saida 
	ADD CONSTRAINT pk_entrada_saida PRIMARY KEY (id);
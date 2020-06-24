/*
script para criar tabela entrada_saida
*/
CREATE TABLE IF NOT EXISTS entrada_saida (
	id INTEGER NOT NULL,
	registro_id INTEGER NOT NULL,
	entrada TIME NOT NULL DEFAULT CURRENT_TIME,
	saida TIME,
	CONSTRAINT fk_entrada_saida_registro FOREIGN KEY (registro_id) REFERENCES registro(id),
	CONSTRAINT pk_entrada_saida PRIMARY KEY (id)
);

/*
script para criar sequence entrada_saida_seq
*/
CREATE SEQUENCE IF NOT EXISTS entrada_saida_seq START 1;
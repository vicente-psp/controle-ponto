/* script para criar sequence contratante_seq */
CREATE SEQUENCE IF NOT EXISTS contratante_seq START 1;

	
/* script para criar tabela contratante */
CREATE TABLE IF NOT EXISTS contratante (
	id INTEGER NOT NULL DEFAULT nextval('contratante_seq'),
	nome VARCHAR(150) NOT NULL,
	razao_social VARCHAR(150),
	cpf_cnpj CHARACTER(14) NOT NULL,
	usuario_id INTEGER NOT NULL,
	data_inclusao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP WITH TIME ZONE,
	CONSTRAINT pk_contratante_id PRIMARY KEY(id),
	CONSTRAINT fk_contratante_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
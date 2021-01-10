/* script para criar sequence pessoa_seq */
CREATE SEQUENCE IF NOT EXISTS pessoa_seq START 1;

	
/* script para criar tabela pessoa */
CREATE TABLE IF NOT EXISTS pessoa (
	id INTEGER NOT NULL DEFAULT nextval('pessoa_seq'),
	nome VARCHAR(100) NOT NULL,
	razao_social VARCHAR(150),
	cpf_cnpj CHARACTER(14) NOT NULL,
	tipo_pessoa VARCHAR(15) NOT NULL,
	contratante_id INTEGER,
	usuario_id INTEGER NOT NULL,
	data_inclusao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP WITH TIME ZONE,
	CONSTRAINT pk_pessoa_id PRIMARY KEY(id),
	CONSTRAINT fk_pessoa_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
	CONSTRAINT fk_pessoa_contratante FOREIGN KEY (contratante_id) REFERENCES pessoa(id)
);
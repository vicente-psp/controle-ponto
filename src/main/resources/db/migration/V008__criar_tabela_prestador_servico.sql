/* script para criar sequence prestador_servico_seq */
CREATE SEQUENCE IF NOT EXISTS prestador_servico_seq START 1;


/* script para criar tabela prestador_servico */
CREATE TABLE IF NOT EXISTS prestador_servico (
	id INTEGER NOT NULL DEFAULT nextval('prestador_servico_seq'),
	nome VARCHAR(150) NOT NULL,
	razao_social VARCHAR(150),
	cpf_cnpj CHARACTER(14) NOT NULL,
	tipo_pessoa VARCHAR(15) NOT NULL,
	usuario_id INTEGER NOT NULL,
	data_inclusao TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL,
	data_alteracao TIMESTAMP WITH TIME ZONE,
	CONSTRAINT pk_prestador_servico_id PRIMARY KEY(id),
	CONSTRAINT fk_prestador_servico_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
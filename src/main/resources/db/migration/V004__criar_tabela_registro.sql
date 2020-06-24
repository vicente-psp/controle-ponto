/*
script para criar tabela registro
*/
CREATE TABLE IF NOT EXISTS registro (
	id INTEGER NOT NULL,
	data DATE NOT NULL DEFAULT CURRENT_DATE,
	usuario_id INTEGER NOT NULL,
	CONSTRAINT u_registro_data UNIQUE(data),
	CONSTRAINT pk_registro PRIMARY KEY (id),
	CONSTRAINT fk_registro_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

/*
script para criar sequence registro_seq
*/
CREATE SEQUENCE IF NOT EXISTS registro_seq START 1;
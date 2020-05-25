/*
script para tornar coluna email única, não pode ter registros com mesmo email
*/
ALTER TABLE usuario
	ADD CONSTRAINT u_usuario_email UNIQUE(email);
	
	
/*
script para tornar usuário 1 (Admin) ativo
*/	
UPDATE usuario SET ativo = true WHERE id = 1; 
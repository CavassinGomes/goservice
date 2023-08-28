
drop database goservice_db;

create database goservice_db;

use goservice_db;

show tables;

drop table agendamentos;

drop table prestadores_servicos;

drop table servico;

select * from usuarios;


insert into usuarios (dtype, nome, email, perfil, senha, habilitado)
value (
	"Administrador",
	"Lucas Gomes",
    "lucascavassinig@gmail.com",
    "ADMIN",
    "$2a$10$r3r9V682sIhE/61jZjqkauT.08pxrBx.GE1T.yEogN5r8Ly2S8eTK",
    1
); # 123456

INSERT INTO usuarios (dtype, email, habilitado, nome, perfil, senha)
VALUES
    ('Administrador', 'lucas_admin@example.com', 1, 'Lucas', 'ADMIN', '$2a$12$XYOhPdBa1nl2X4staQwvaei4MO0SlWW8N06kfrjBX323Wtl5s9nGy'),
    ('Prestador', 'julia_prestador@example.com', 1, 'Julia', 'PRESTADOR', '$2a$12$GOW0j1reHdGpw6iWwmwNq.aCQUAf/w/odNCsY0C/wM5HGgoXsRBZ6'),
    ('Cliente', 'andre_cliente@example.com', 1, 'André', 'CLIENTE', '$2a$12$HDGPO8BMtCSIxB5ObmGFbOtaxGlu.B.GDn4IFzApjWMf.0yMybjTe'),
    ('Cliente', 'maria_cliente@example.com', 1, 'Maria', 'CLIENTE', '$2a$12$nQlxYHOCt/iEO0al8RNc3uo5kzRsrZ9urtcv5mt.nwfOuXwsobZ6q'),
    ('Prestador', 'gustavo_prestador@example.com', 1, 'Gustavo', 'PRESTADOR', '$2a$12$YlM3XnSYQInfgqmxnQ3duOe2taKGPWKngQ3xxtK2GKABU1XrvY.QO'),
    ('Cliente', 'ana_cliente@example.com', 1, 'Ana', 'CLIENTE', '$2a$12$0QlSNNMPuT9Id6Yz7ZeaVem0lWXlqezxYtbbYVrdzBVoIC8mixCju'),
    ('Administrador', 'rafael_admin@example.com', 1, 'Rafael', 'ADMIN', '$2a$12$j9UOMm8J3V.aYwOWEIKYKOReQwPk1n2zIohv2o9dsITd7q3slfKb.'),
    ('Cliente', 'carolina_cliente@example.com', 1, 'Carolina', 'CLIENTE', '$2a$12$mXVaadOixPC2Z9dWXeuKveX8phoA519qC9eDMvUUQvZVEabvID.ny'),
    ('Prestador', 'pedro_prestador@example.com', 1, 'Pedro', 'PRESTADOR', '$2a$12$U1H2gRcj7dw51LKbqTpu4eqGGlIcFFO1Xv.yDE42h2EKxATWUDKmy'),
    ('Cliente', 'larissa_cliente@example.com', 1, 'Larissa', 'CLIENTE', '$2a$12$7MCr/qfG.0cG7MyhesNls..ozSF1PJEAoIS55oT2GcO4EuIMfo1ee');


update usuarios set habilitado = 0 where id in (1, 7, 9);

update usuarios set habilitado = 1 where id = 5;

select * from servicos;
select * from prestadores_servicos;

INSERT INTO servicos (categoria, descricao, nome) 
VALUES 
    ('Limpeza', 'Serviço de limpeza residencial', 'Limpeza Residencial'),
    ('Treinamento', 'Treinamento corporativo em liderança', 'Treinamento em Liderança'),
    ('Saúde', 'Consulta médica de clínico geral', 'Consulta Clínico Geral'),
    ('Desenvolvimento', 'Desenvolvimento de aplicativo móvel', 'Desenvolvimento de App'),
    ('Design', 'Design de logotipo para empresas', 'Design de Logotipo'),
    ('Limpeza', 'Limpeza de escritórios comerciais', 'Limpeza Comercial'),
    ('Treinamento', 'Treinamento em habilidades de comunicação', 'Treinamento em Comunicação'),
    ('Saúde', 'Sessão de fisioterapia para reabilitação', 'Fisioterapia de Reabilitação'),
    ('Desenvolvimento', 'Desenvolvimento de site institucional', 'Desenvolvimento de Site Institucional'),
    ('Design', 'Design de interface de usuário para aplicativos', 'Design de Interface de Aplicativo');
    
INSERT INTO prestadores_servicos (servico_id, prestador_id)
VALUES
    (1, 3),   -- Serviço 1 associado ao prestador Julia
    (2, 6),   -- Serviço 2 associado ao prestador Gustavo
    (3, 10),  -- Serviço 3 associado ao prestador Pedro
    (4, 3),   -- Serviço 4 associado ao prestador Julia
    (5, 6),   -- Serviço 5 associado ao prestador Gustavo
    (6, 10),  -- Serviço 6 associado ao prestador Pedro
    (7, 6),   -- Serviço 7 associado ao prestador Gustavo
    (8, 3),   -- Serviço 8 associado ao prestador Julia
    (9, 10),  -- Serviço 9 associado ao prestador Pedro
    (10, 6);  -- Serviço 10 associado ao prestador Gustavo


delete from servicos;


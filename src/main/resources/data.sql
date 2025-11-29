MERGE INTO BAIRROS (id, nome) VALUES
(1, 'Centro'),
(2, 'Jardim das Flores'),
(3, 'Vila Nova'),
(4, 'Parque das Nações'),
(5, 'Cidade Alta'),
(6, 'Bela Vista'),
(7, 'São José'),
(8, 'Santa Maria'),
(9, 'Boa Vista'),
(10, 'Nova Esperança');


MERGE INTO RUAS_CONEXOES (id, bairros_origens_id, bairros_destinos_id, distancias_km) VALUES
(1, 1, 2, 2.5),
(2, 1, 3, 3.8),
(3, 1, 4, 5.0),
(4, 1, 5, 4.2),
(5, 2, 3, 1.7),
(6, 2, 4, 3.4),
(7, 2, 5, 2.9),
(8, 3, 4, 2.1),
(9, 3, 5, 3.3),
(10, 4, 5, 1.8);


MERGE INTO CAMINHOES (id, capacidades, nomes_motoristas, placas, residuos) VALUES
(1, 5.0, 'João Silva', 'ABC-1234', 'Orgânico'),
(2, 7.5, 'Maria Oliveira', 'DEF-5678', 'Reciclável'),
(3, 6.0, 'Carlos Souza', 'GHI-9012', 'Orgânico'),
(4, 8.0, 'Ana Pereira', 'JKL-3456', 'Reciclável'),
(5, 4.5, 'Lucas Costa', 'MNO-7890', 'Eletrônico');


MERGE INTO PONTOS_COLETAS (id, email_responsavel, enderecos, nomes, responsaveis, telefone_responsavel, tipos_residuo_aceitos, bairro_id) VALUES
(1, 'joao.silva@email.com', 'Rua das Flores, 123', 'Ponto Central', 'João Silva', '(11) 98765-4321', 'Orgânico', 1),
(2, 'maria.oliveira@email.com', 'Av. Paulista, 456', 'Ponto Paulista', 'Maria Oliveira', '(11) 91234-5678', 'Reciclável', 2),
(3, 'carlos.souza@email.com', 'Rua do Sol, 789', 'Ponto Sol', 'Carlos Souza', '(11) 99876-5432', 'Orgânico', 3),
(4, 'ana.pereira@email.com', 'Av. Brasil, 321', 'Ponto Brasil', 'Ana Pereira', '(11) 97654-3210', 'Reciclável', 4),
(5, 'lucas.costa@email.com', 'Rua das Laranjeiras, 654', 'Ponto Laranjeiras', 'Lucas Costa', '(11) 93456-7890', 'Eletrônico', 5);


-- Para BAIRROS
ALTER TABLE BAIRROS ALTER COLUMN ID RESTART WITH (
SELECT COALESCE(MAX(ID), 0) + 1 FROM BAIRROS
    );

-- Para RUAS_CONEXOES
ALTER TABLE RUAS_CONEXOES ALTER COLUMN ID RESTART WITH (
SELECT COALESCE(MAX(ID), 0) + 1 FROM RUAS_CONEXOES
    );

-- Para CAMINHOES
ALTER TABLE CAMINHOES ALTER COLUMN ID RESTART WITH (
SELECT COALESCE(MAX(ID), 0) + 1 FROM CAMINHOES
    );

-- Para PONTOS_COLETAS
ALTER TABLE PONTOS_COLETAS ALTER COLUMN ID RESTART WITH (
SELECT COALESCE(MAX(ID), 0) + 1 FROM PONTOS_COLETAS
    );

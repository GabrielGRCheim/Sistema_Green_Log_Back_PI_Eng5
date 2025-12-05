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


MERGE INTO RUAS_CONEXOES (id, bairros_origens_id, NOMES, bairros_destinos_id, distancias_km) VALUES
(1, 1,'Centro - Jardim das Flores',2, 2.5),
(2, 1,'Centro - Vila Nova',3, 3.8),
(3, 1,'Centro - Parque das Nações',4, 5.0),
(4, 1,'Centro - Cidade Alta',5, 4.2),
(5, 2,'Jardim das Flores - Vila Nova',3, 1.7),
(6, 2,'Jardim das Flores - Parque das Nações',4, 3.4),
(7, 2,'Jardim das Flores - Cidade Alta',5, 2.9),
(8, 3,'Vila Nova - Parque das Nações',4, 2.1),
(9, 3,'Vila Nova - Cidade Alta',5, 3.3),
(10, 4,'Parque das Nações - Cidade Alta',5, 1.8);

MERGE INTO MOTORISTAS (id, nomes, cpf, ativo) VALUES
(1, 'João Silva', '123.456.789-09', TRUE),
(2, 'Maria Oliveira', '987.654.321-00', TRUE),
(3, 'Carlos Souza', '111.222.333-44', TRUE),
(4, 'Ana Pereira', '555.666.777-88', TRUE),
(5, 'Lucas Costa', '999.888.777-66', TRUE),
(6, 'Fernanda Lima', '222.333.444-55', TRUE),
(7, 'Ricardo Alves', '444.555.666-77', TRUE),
(8, 'Beatriz Rocha', '333.222.111-00', TRUE),
(9, 'Paulo Santos', '888.777.666-55', TRUE),
(10, 'Carolina Martins', '666.555.444-33', TRUE);

MERGE INTO CAMINHOES (id, capacidades, Motoristas, placas, ATIVO) VALUES
(1, 5.0, 1, 'ABC1A23',TRUE),
(2, 7.5, 2, 'DEF2B34',TRUE),
(3, 6.0, 3, 'GHI3C45',TRUE),
(4, 8.0, 4, 'JKL4D56',TRUE),
(5, 4.5, 5, 'MNO5E67',TRUE);

INSERT INTO CAMINHAO_TIPOS_RESIDUOS (CAMINHAO_ID, TIPOS_RESIDUOS)
SELECT CAMINHAO_ID, TIPOS_RESIDUOS
FROM (
         SELECT 1 AS CAMINHAO_ID, 'METAL' AS TIPOS_RESIDUOS
         UNION ALL SELECT 1, 'PLASTICO'
         UNION ALL SELECT 1, 'VIDRO'
         UNION ALL SELECT 2, 'METAL'
         UNION ALL SELECT 3, 'METAL'
         UNION ALL SELECT 4, 'METAL'
         UNION ALL SELECT 5, 'METAL'
     ) AS seed
WHERE NOT EXISTS (SELECT 1 FROM CAMINHAO_TIPOS_RESIDUOS);


MERGE INTO PONTOS_COLETAS (id, email_responsavel, enderecos, nomes, responsaveis, telefone_responsavel, bairro_id, ATIVO) VALUES
(1, 'joao.silva@email.com', 'Rua das Flores, 123', 'Ponto Central', 'João Silva', '(11) 98765-4321', 1,TRUE),
(2, 'maria.oliveira@email.com', 'Av. Paulista, 456', 'Ponto Paulista', 'Maria Oliveira', '(11) 91234-5678', 2,TRUE),
(3, 'carlos.souza@email.com', 'Rua do Sol, 789', 'Ponto Sol', 'Carlos Souza', '(11) 99876-5432', 3,TRUE),
(4, 'ana.pereira@email.com', 'Av. Brasil, 321', 'Ponto Brasil', 'Ana Pereira', '(11) 97654-3210', 4,TRUE),
(5, 'lucas.costa@email.com', 'Rua das Laranjeiras, 654', 'Ponto Laranjeiras', 'Lucas Costa', '(11) 93456-7890', 5,TRUE);

INSERT INTO PONTO_RESIDUOS (PONTO_ID, TIPO_RESIDUO)
SELECT PONTO_ID, TIPOS_RESIDUOS
FROM (
         SELECT 1 AS PONTO_ID, 'METAL' AS TIPOS_RESIDUOS
         UNION ALL SELECT 1, 'PLASTICO'
         UNION ALL SELECT 1, 'VIDRO'
         UNION ALL SELECT 2, 'METAL'
         UNION ALL SELECT 3, 'METAL'
         UNION ALL SELECT 4, 'METAL'
         UNION ALL SELECT 5, 'METAL'
     ) AS seed
WHERE NOT EXISTS (SELECT 1 FROM PONTO_RESIDUOS);

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

-- Para MOTORISTAS
ALTER TABLE MOTORISTAS ALTER COLUMN ID RESTART WITH (
SELECT COALESCE(MAX(ID), 0) + 1 FROM MOTORISTAS
    );

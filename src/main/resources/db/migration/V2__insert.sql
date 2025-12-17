-- ============================
-- PLANOS INICIAIS
-- ============================
INSERT INTO plano (id, codigo, limite_diario, streams_simultaneos)
VALUES
    (random_uuid(), 'BASICO', 5, 1),
    (random_uuid(), 'PADRAO', 10, 2),
    (random_uuid(), 'PREMIUM', 20, 4);

-- ============================
-- CONTEUDOS DE EXEMPLO
-- ============================
INSERT INTO conteudo (
    id, titulo, tipo, ano, duracao_minutos,
    relevancia, genero, criado_em, atualizado_em
) VALUES
(
    random_uuid(),
    'O Poderoso Chefão',
    'FILME',
    1972,
    175,
    9.2,
    'Drama',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    random_uuid(),
    'Breaking Bad',
    'SERIE',
    2008,
    45,
    9.5,
    'Drama',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

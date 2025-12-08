-- ===============================================================
-- USUÁRIO DE TESTE
-- ===============================================================
INSERT INTO usuarios (
    id, nome_completo, data_nascimento, email, senha_hash, cpf_cnpj,
    perfil, criado_em, atualizado_em
) VALUES (
    uuid_generate_v4(),
    'Usuário de Teste',
    '1990-01-01',
    'teste@example.com',
    '$2a$10$EzSeuHashAqui123456789012345678901234567890123456789012',
    '12345678900',
    'USER',
    now(),
    now()
);

-- ===============================================================
-- PLANOS
-- ===============================================================
INSERT INTO planos (id, nome, descricao, valor_mensal, resolucao, dispositivos, criado_em, atualizado_em)
VALUES
    (uuid_generate_v4(), 'Básico', 'Plano básico com 1 tela', 19.90, '720p', 1, NOW(), NOW()),
    (uuid_generate_v4(), 'Padrão', '2 telas simultâneas', 29.90, '1080p', 2, NOW(), NOW()),
    (uuid_generate_v4(), 'Premium', '4K + 4 telas', 49.90, '4K', 4, NOW(), NOW());

-- ===============================================================
-- CONTEÚDOS
-- ===============================================================
INSERT INTO conteudo (id, titulo, tipo, ano, duracao_minutos, relevancia, sinopse, trailer_url, genero, criado_em, atualizado_em)
VALUES
    (uuid_generate_v4(), 'Interestelar', 'FILME', 2014, 169, 9.8,
        'Um grupo de astronautas viaja através de um buraco de minhoca.',
        'https://youtube.com/trailer/interestelar', 'FICÇÃO', NOW(), NOW()),

    (uuid_generate_v4(), 'Breaking Bad', 'SERIE', 2008, 50, 9.9,
        'Professor vira fabricante de drogas.',
        'https://youtube.com/trailer/breakingbad', 'DRAMA', NOW(), NOW()),

    (uuid_generate_v4(), 'Avatar', 'FILME', 2009, 180, 9.4,
        'Humano se conecta com nativos de Pandora.',
        'https://youtube.com/trailer/avatar', 'FANTASIA', NOW(), NOW());

-- ===============================================================
-- MÉTODO DE PAGAMENTO (PIX)
-- ===============================================================
INSERT INTO metodos_pagamento (
    id, usuario_id, tipo, apelido, chave_pix, ativo, criado_em, atualizado_em
)
VALUES (
    uuid_generate_v4(),
    (SELECT id FROM usuarios LIMIT 1),
    'PIX',
    'Chave Principal',
    'email@example.com',
    TRUE,
    NOW(),
    NOW()
);

-- ===============================================================
-- MÉTODO DE PAGAMENTO (CARTÃO DE CRÉDITO)
-- ===============================================================
INSERT INTO metodos_pagamento (
    id, usuario_id, tipo, apelido, bandeira, numero_mascarado, expiracao, ativo, criado_em, atualizado_em
)
VALUES (
    uuid_generate_v4(),
    (SELECT id FROM usuarios LIMIT 1),
    'CARTAO',
    'Cartão Visa',
    'VISA',
    '**** **** **** 1234',
    '12/29',
    TRUE,
    NOW(),
    NOW()
);

-- ===============================================================
-- FAVORITOS AUTOMÁTICOS
-- ===============================================================
INSERT INTO favoritos (usuario_id, conteudo_id)
SELECT u.id, c.id
FROM usuarios u
CROSS JOIN conteudo c
LIMIT 3;

-- ===============================================================
-- EXTENSÕES NECESSÁRIAS
-- ===============================================================
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "pgcrypto";

-- ===============================================================
-- TABELA: enderecos
-- ===============================================================
CREATE TABLE enderecos (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(150),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    cep VARCHAR(9) NOT NULL
);

-- ===============================================================
-- TABELA: usuarios
-- ===============================================================
CREATE TABLE usuarios (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome_completo VARCHAR(150) NOT NULL,
    data_nascimento DATE NOT NULL,
    email VARCHAR(254) NOT NULL UNIQUE,
    senha_hash VARCHAR(60) NOT NULL,
    cpf_cnpj VARCHAR(14) UNIQUE,
    perfil VARCHAR(20) NOT NULL,
    criado_em TIMESTAMP(3) NOT NULL,
    atualizado_em TIMESTAMP(3) NOT NULL,

    endereco_id UUID,
    CONSTRAINT fk_usuario_endereco
        FOREIGN KEY (endereco_id)
        REFERENCES enderecos(id)
);

-- ===============================================================
-- TABELA: conteudo
-- ===============================================================
CREATE TABLE conteudo (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    titulo VARCHAR(200) NOT NULL,
    tipo VARCHAR(10) NOT NULL,
    ano SMALLINT NOT NULL CHECK (ano >= 1888 AND ano <= 2100),
    duracao_minutos SMALLINT NOT NULL CHECK (duracao_minutos BETWEEN 1 AND 999),
    relevancia DECIMAL(4,2) NOT NULL,
    sinopse TEXT,
    trailer_url VARCHAR(500),
    genero VARCHAR(50),
    criado_em TIMESTAMP(3) NOT NULL,
    atualizado_em TIMESTAMP(3) NOT NULL
);

-- ===============================================================
-- TABELA: planos
-- ===============================================================
CREATE TABLE planos (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    valor_mensal DECIMAL(10,2) NOT NULL CHECK (valor_mensal > 0),
    resolucao VARCHAR(10) NOT NULL,
    dispositivos INTEGER NOT NULL CHECK (dispositivos >= 1),
    criado_em TIMESTAMP(3) NOT NULL,
    atualizado_em TIMESTAMP(3) NOT NULL
);

-- ===============================================================
-- TABELA: metodos_pagamento
-- ===============================================================

CREATE TABLE metodos_pagamento (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    usuario_id UUID NOT NULL,

    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('PIX', 'CARTAO')),
    apelido VARCHAR(100),

    -- CAMPOS DE CARTÃO
    bandeira VARCHAR(30),
    numero_mascarado VARCHAR(20),
    expiracao VARCHAR(5),

    -- CAMPOS DE PIX
    chave_pix VARCHAR(120),

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    criado_em TIMESTAMP(3) NOT NULL,
    atualizado_em TIMESTAMP(3) NOT NULL,

    CONSTRAINT fk_metodo_pag_usuario
        FOREIGN KEY(usuario_id) REFERENCES usuarios(id)
);

-- ===============================================================
-- TABELA: assinaturas
-- ===============================================================
CREATE TABLE assinaturas (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),

    usuario_id UUID NOT NULL,
    plano_id UUID NOT NULL,

    status VARCHAR(20) NOT NULL CHECK (status IN ('ATIVA', 'CANCELADA')),
    iniciada_em TIMESTAMP(3) NOT NULL,
    cancelada_em TIMESTAMP(3),

    metodo_pagamento_id UUID,
    CONSTRAINT fk_assinatura_metodo FOREIGN KEY (metodo_pagamento_id)
        REFERENCES metodos_pagamento(id),

    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (plano_id) REFERENCES planos(id)
);

-- ===============================================================
-- TABELA: favoritos
-- ===============================================================
CREATE TABLE favoritos (
    usuario_id UUID NOT NULL,
    conteudo_id UUID NOT NULL,
    criado_em TIMESTAMP(3) NOT NULL DEFAULT now(),

    PRIMARY KEY (usuario_id, conteudo_id),

    CONSTRAINT fk_favorito_usuario FOREIGN KEY (usuario_id)
        REFERENCES usuarios(id) ON DELETE CASCADE,

    CONSTRAINT fk_favorito_conteudo FOREIGN KEY (conteudo_id)
        REFERENCES conteudo(id) ON DELETE CASCADE
);

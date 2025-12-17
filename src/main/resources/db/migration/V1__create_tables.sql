-- ============================
-- USUARIO
-- ============================
CREATE TABLE usuario (
    id UUID PRIMARY KEY,
    nome_completo VARCHAR(150) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
    perfil VARCHAR(20) NOT NULL,
    data_nascimento DATE
);

-- ============================
-- ENDERECO
-- ============================
CREATE TABLE endereco (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    logradouro VARCHAR(150) NOT NULL,
    numero VARCHAR(20) NOT NULL,
    complemento VARCHAR(100),
    bairro VARCHAR(80) NOT NULL,
    cidade VARCHAR(80) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    cep VARCHAR(8) NOT NULL,

    CONSTRAINT fk_endereco_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- ============================
-- PLANO
-- ============================
CREATE TABLE plano (
    id UUID PRIMARY KEY,
    codigo VARCHAR(20) NOT NULL UNIQUE,
    limite_diario INTEGER NOT NULL,
    streams_simultaneos INTEGER NOT NULL
);

-- ============================
-- CONTEUDO
-- ============================
CREATE TABLE conteudo (
    id UUID PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    ano INTEGER NOT NULL,
    duracao_minutos INTEGER NOT NULL,
    relevancia NUMERIC(4,2) NOT NULL,
    sinopse TEXT,
    genero VARCHAR(50),
    trailer_url VARCHAR(500),
    criado_em TIMESTAMP NOT NULL,
    atualizado_em TIMESTAMP NOT NULL
);

-- ============================
-- METODO PAGAMENTO
-- ============================
CREATE TABLE metodo_pagamento (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    bandeira VARCHAR(20) NOT NULL,
    ultimos4 VARCHAR(4) NOT NULL,
    mes_exp INTEGER NOT NULL,
    ano_exp INTEGER NOT NULL,
    nome_portador VARCHAR(150) NOT NULL,
    token_gateway VARCHAR(120) NOT NULL,
    criado_em TIMESTAMP NOT NULL,

    CONSTRAINT fk_metodo_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

-- ============================
-- ASSINATURA
-- ============================
CREATE TABLE assinatura (
    id UUID PRIMARY KEY,
    usuario_id UUID NOT NULL,
    plano_id UUID NOT NULL,
    metodo_pagamento_id UUID,
    status VARCHAR(20) NOT NULL,
    iniciada_em TIMESTAMP NOT NULL,
    cancelada_em TIMESTAMP,

    CONSTRAINT fk_assinatura_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id),

    CONSTRAINT fk_assinatura_plano
        FOREIGN KEY (plano_id) REFERENCES plano(id),

    CONSTRAINT fk_assinatura_metodo
        FOREIGN KEY (metodo_pagamento_id) REFERENCES metodo_pagamento(id)
);

-- ============================
-- FAVORITO (PK COMPOSTA)
-- ============================
CREATE TABLE favorito (
    usuario_id UUID NOT NULL,
    conteudo_id UUID NOT NULL,
    criado_em TIMESTAMP NOT NULL,

    CONSTRAINT pk_favorito PRIMARY KEY (usuario_id, conteudo_id),

    CONSTRAINT fk_favorito_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id),

    CONSTRAINT fk_favorito_conteudo
        FOREIGN KEY (conteudo_id) REFERENCES conteudo(id)
);

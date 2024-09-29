CREATE TABLE usuario (
   id BIGSERIAL PRIMARY KEY,
   nome VARCHAR(100) NOT NULL
);

CREATE TABLE sugestao (
    id BIGSERIAL PRIMARY KEY,
    descricao VARCHAR(255),
    categoria VARCHAR(100),
    data_envio TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE curtidas (
    sugestao_id BIGINT,
    usuario_id BIGINT,
    PRIMARY KEY (sugestao_id, usuario_id),
    FOREIGN KEY (sugestao_id) REFERENCES sugestao(id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
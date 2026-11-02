-- Creacion de Tablas
CREATE TABLE users (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(100) NOT NULL,
    email      VARCHAR(150) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(20)  NOT NULL,
    created_at TIMESTAMP    NOT NULL
);

CREATE TABLE visitors (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre   VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni      VARCHAR(20)  NOT NULL UNIQUE,
    edad     INT          NOT NULL
);

CREATE TABLE environments (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(150) NOT NULL,
    description TEXT
);

CREATE TABLE rates (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre      VARCHAR(150)   NOT NULL,
    precio      DECIMAL(10, 2) NOT NULL,
    descripcion TEXT
);

CREATE TABLE tickets (
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    visitor_id_fk   BIGINT  NOT NULL,
    ticket_active   BOOLEAN NOT NULL DEFAULT TRUE,
    rate_id_fk  BIGINT  NOT NULL,
    environment_id_fk   BIGINT  NOT NULL,

-- Restricciones de Foreign Keys
CONSTRAINT fk_tickets_visitor     FOREIGN KEY (visitor_id_fk)     REFERENCES visitors (id)     ON DELETE RESTRICT,
CONSTRAINT fk_tickets_rate        FOREIGN KEY (rate_id_fk)        REFERENCES rates (id)        ON DELETE RESTRICT,
CONSTRAINT fk_tickets_environment FOREIGN KEY (environment_id_fk) REFERENCES environments (id) ON DELETE RESTRICT
);

-- Creacion Indices
CREATE INDEX idx_tickets_visitor     ON tickets (visitor_id_fk);
CREATE INDEX idx_tickets_rate        ON tickets (rate_id_fk);
CREATE INDEX idx_tickets_environment ON tickets (environment_id_fk);



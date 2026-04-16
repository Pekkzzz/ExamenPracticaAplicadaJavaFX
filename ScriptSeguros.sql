CREATE DATABASE db_seguros;
USE db_seguros;

CREATE TABLE asegurado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE seguro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    numero VARCHAR(50) NOT NULL UNIQUE,
    fecha_expedicion VARCHAR(20) NOT NULL,
    estado BOOLEAN NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    asegurado_id INT NOT NULL,
    FOREIGN KEY (asegurado_id) REFERENCES asegurado(id)
);

CREATE TABLE seguro_vida (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seguro_id INT NOT NULL UNIQUE,
    beneficiario VARCHAR(100) NOT NULL,
    FOREIGN KEY (seguro_id) REFERENCES seguro(id)
);

CREATE TABLE seguro_vehiculo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    seguro_id INT NOT NULL UNIQUE,
    marca VARCHAR(100) NOT NULL,
    FOREIGN KEY (seguro_id) REFERENCES seguro(id)
);

INSERT INTO asegurado (nombre) VALUES ('Juan Pérez'), ('María López');

USE db_seguros;
SELECT * FROM asegurado;
SELECT * FROM seguro;
CREATE TABLE usuarios (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR (70) NOT NULL,
    apellidos VARCHAR(70) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE ,
    telefono VARCHAR(12),
    password VARCHAR(30) NOT NULL,

    PRIMARY KEY(id)
);
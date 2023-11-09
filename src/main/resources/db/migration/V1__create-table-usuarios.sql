CREATE TABLE usuarios
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    nombres         VARCHAR(255) NOT NULL,
    apellidos       VARCHAR(255) NOT NULL,
    correo          VARCHAR(255) NOT NULL,
    intereses       VARCHAR(255),
    genero          VARCHAR(255),
    fechaNacimiento DATE,
    pais            VARCHAR(255),
    estado          VARCHAR(255),
    ciudad          VARCHAR(255),
    telefono        VARCHAR(255),
    foto            VARCHAR(255),
    password        VARCHAR(255) NOT NULL
);
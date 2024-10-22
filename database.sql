--Tabla sobre consumidores de actividades
CREATE TABLE
    CONSUMIDOR (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        password VARCHAR(255) NOT NULL,
        email VARCHAR(100) NOT NULL
    );

--Tabla sobre ofertantes de actividades
CREATE TABLE
    OFERTANTE (
        id INT AUTO_INCREMENT PRIMARY KEY,
        username VARCHAR(50) NOT NULL,
        password VARCHAR(255) NOT NULL,
        email VARCHAR(100) NOT NULL
    );

--Tabla sobre actividades ofertadas
CREATE TABLE
    GUIA (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(100) NOT NULL,
        duracion INT NOT NULL, -- Duración en minutos u horas, según se necesite
        precio DECIMAL(10, 2) NOT NULL, -- Precio con dos decimales
        ofertante_id INT NOT NULL, -- Relación con ofertante
        tipo_id INT NOT NULL, -- Clave foránea que referencia a tipo
        FOREIGN KEY (ofertante_id) REFERENCES OFERTANTE (id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (tipo_id) REFERENCES TIPO (id) ON DELETE SET NULL ON UPDATE CASCADE
    );

--Tabla que relaciona consumidores y actividades con relación n:m
CREATE TABLE
    CONSUMO_ACTIVIDAD (
        consumidor_id INT NOT NULL,
        guia_id INT NOT NULL,
        PRIMARY KEY (consumidor_id, guia_id), -- Llave primaria compuesta
        FOREIGN KEY (consumidor_id) REFERENCES CONSUMIDOR (id) ON DELETE CASCADE ON UPDATE CASCADE,
        FOREIGN KEY (guia_id) REFERENCES GUIA (id) ON DELETE CASCADE ON UPDATE CASCADE
    );

--Tabla de tipos de actividades
CREATE TABLE
    TIPO (
        id INT AUTO_INCREMENT PRIMARY KEY,
        nombre VARCHAR(50) NOT NULL -- Nombre del tipo de actividad
    );
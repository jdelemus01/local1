INSERT INTO USUARIO (username, password, email, rol) VALUES
('maria2024', 'contrasena1', 'maria@example.com', 'ADMIN'),
('carlos123', 'contrasena2', 'carlos.sanchez@correo.com', 'OFE'),
('ana90', 'contrasena3', 'ana.gomez@correo.com', 'CON'),
('luisa1985', 'contrasena4', 'luisa@example.com', 'CON'),
('pedro2023', 'contrasena5', 'pedro@example.com', 'OFE'),
('sofia1990', 'contrasena6', 'sofia@example.com', 'ADMIN'),
('jorge1995', 'contrasena7', 'jorge@example.com', 'OFE');


INSERT INTO OFERTANTE (nombre, apellido, email, username) VALUES
('Carlos', 'Sanchez', 'carlos.sanchez@correo.com', 'carlos123'),
('Lucia', 'Fernandez', 'lucia@example.com', 'pedro2023'),
('Roberto', 'Martinez', 'roberto@example.com', 'jorge1995'),
('Elena', 'Gonzalez', 'elena@example.com', 'maria2024'),
('Ricardo', 'Lopez', 'ricardo@example.com', 'sofia1990'),
('Valeria', 'Diaz', 'valeria@example.com', 'luisa1985'),
('Julio', 'Ramirez', 'julio@example.com', 'jorge1995');



INSERT INTO CONSUMIDOR (nombre, apellido, fecha_nacimiento, email, username) VALUES
('Ana', 'Gómez', '1990-03-15', 'ana.gomez@correo.com', 'ana90'),
('Juan', 'Pérez', '1995-06-22', 'juan.perez@correo.com', 'maria2024'),
('Sofia', 'Torres', '1989-09-10', 'sofia.torres@correo.com', 'sofia1990'),
('Diego', 'Rios', '1992-02-05', 'diego.rios@correo.com', 'carlos123'),
('Luisa', 'Vasquez', '1993-11-30', 'luisa.vasquez@correo.com', 'pedro2023'),
('Mariana', 'Cruz', '1996-01-18', 'mariana.cruz@correo.com', 'jorge1995'),
('Gabriel', 'Mendoza', '1988-04-21', 'gabriel.mendoza@correo.com', 'ana90');


INSERT INTO ACTIVIDAD (nombre, duracion, precio, aireLibre, fecha, mas18, ofertante_id, tipo_id) VALUES
('Caminata por el Parque', 2.5, 10.0, TRUE, '2024-11-05', FALSE, 1, 1),
('Clase de Yoga', 1.5, 15.0, FALSE, '2024-11-10', FALSE, 2, 2),
('Taller de Pintura', 3.0, 20.0, FALSE, '2024-11-15', TRUE, 3, 3),
('Excursión a la Montaña', 5.0, 50.0, TRUE, '2024-11-20', TRUE, 1, 1),
('Ciclo de Cine', 2.0, 8.0, FALSE, '2024-11-25', FALSE, 2, 2),
('Clase de Cocina', 4.0, 30.0, FALSE, '2024-12-01', TRUE, 3, 3),
('Torneo de Fútbol', 3.0, 12.0, TRUE, '2024-12-05', TRUE, 1, 1);


INSERT INTO TIPO (nombre) VALUES
('Aventura'),
('Cultural'),
('Deportivo'),
('Gastronómico'),
('Educativo'),
('Salud'),
('Entretenimiento');

INSERT INTO CONSUMO_ACTIVIDAD (idConsumidor, idActividad) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 2),
(6, 3),
(7, 4);


INSERT INTO ROL (nombre) VALUES
('ADMIN'),
('OFE'),
('CON');



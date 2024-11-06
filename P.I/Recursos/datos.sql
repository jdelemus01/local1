INSERT INTO `usuario` (`id`, `email`, `password`, `username`, `rol`) VALUES
(1, 'maria@example.com', 'contrasena1', 'maria2024', 'ADMIN'),
(2, 'carlos.sanchez@correo.com', 'contrasena2', 'carlos123', 'OFE'),
(3, 'ana.gomez@correo.com', 'contrasena3', 'ana90', 'CON'),
(4, 'luisa@example.com', 'contrasena4', 'luisa1985', 'CON'),
(5, 'pedro@example.com', 'contrasena5', 'pedro2023', 'OFE'),
(6, 'sofia@example.com', 'contrasena6', 'sofia1990', 'ADMIN'),
(7, 'jorge@example.com', 'contrasena7', 'jorge1995', 'OFE');

INSERT INTO `tipo` (`id`, `nombre`) VALUES
(1, 'Aventura'),
(2, 'Educativo'),
(3, 'Cultural');

INSERT INTO `ofertante` (`fecha_nacimiento`, `username`, `apellido`, `email`, `nombre`, `rol`) VALUES
('1985-04-23', 2, 'Sanchez', 'carlos.sanchez@correo.com', 'Carlos', 'OFE'),
('1990-07-15', 5, 'Perez', 'pedro@example.com', 'Pedro', 'OFE'),
('1992-03-02', 7, 'Martinez', 'jorge@example.com', 'Jorge', 'OFE');

INSERT INTO `consumidor` (`fecha_nacimiento`, `username`, `apellido`, `email`, `nombre`, `rol`) VALUES
('1990-05-10', 3, 'Gomez', 'ana.gomez@correo.com', 'Ana', 'CON'),
('1985-08-23', 4, 'Lopez', 'luisa@example.com', 'Luisa', 'CON');

INSERT INTO `actividad` (`aire_libre`, `duracion`, `fecha`, `mas18`, `precio`, `id`, `ofertante_id`, `tipo_id`, `nombre`) VALUES
(b'1', 3.5, '2024-11-15', b'1', 50, 1, 1, 1, 'Rafting en el río'),
(b'0', 2, '2024-12-05', b'0', 30, 2, 2, 2, 'Taller de cerámica'),
(b'1', 1.5, '2024-11-20', b'0', 15, 3, 3, 3, 'Visita guiada al museo');

INSERT INTO `consumo_actividad` (`id_actividad`, `id_consumidor`) VALUES
(1, 1),
(2, 2),
(3, 1);
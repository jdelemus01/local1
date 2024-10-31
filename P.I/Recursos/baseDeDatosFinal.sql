USE pInt;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pint`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividad`
--

CREATE TABLE `actividad` (
  `aire_libre` bit(1) NOT NULL,
  `duracion` double NOT NULL,
  `fecha` date NOT NULL,
  `mas18` bit(1) NOT NULL,
  `precio` double NOT NULL,
  `id` bigint(20) NOT NULL,
  `ofertante_id` bigint(20) NOT NULL,
  `tipo_id` bigint(20) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `actividad`
--

INSERT INTO `actividad` (`aire_libre`, `duracion`, `fecha`, `mas18`, `precio`, `id`, `ofertante_id`, `tipo_id`, `nombre`) VALUES
(b'1', 3.5, '2024-11-15', b'1', 50, 1, 2, 1, 'Rafting en el río'),
(b'0', 2, '2024-12-05', b'0', 30, 2, 5, 2, 'Taller de cerámica'),
(b'1', 1.5, '2024-11-20', b'0', 15, 3, 7, 3, 'Visita guiada al museo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumidor`
--

CREATE TABLE `consumidor` (
  `fecha_nacimiento` date NOT NULL,
  `username` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consumidor`
--

INSERT INTO `consumidor` (`fecha_nacimiento`, `username`, `apellido`, `email`, `nombre`, `rol`) VALUES
('1990-05-10', 3, 'Gomez', 'ana.gomez@correo.com', 'Ana', 'CON'),
('1985-08-23', 4, 'Lopez', 'luisa@example.com', 'Luisa', 'CON');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumo_actividad`
--

CREATE TABLE `consumo_actividad` (
  `id_actividad` bigint(20) NOT NULL,
  `id_consumidor` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `consumo_actividad`
--

INSERT INTO `consumo_actividad` (`id_actividad`, `id_consumidor`) VALUES
(1, 3),
(2, 4),
(3, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertante`
--

CREATE TABLE `ofertante` (
  `fecha_nacimiento` date NOT NULL,
  `username` bigint(20) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `rol` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ofertante`
--

INSERT INTO `ofertante` (`fecha_nacimiento`, `username`, `apellido`, `email`, `nombre`, `rol`) VALUES
('1985-04-23', 2, 'Sanchez', 'carlos.sanchez@correo.com', 'Carlos', 'OFE'),
('1990-07-15', 5, 'Perez', 'pedro@example.com', 'Pedro', 'OFE'),
('1992-03-02', 7, 'Martinez', 'jorge@example.com', 'Jorge', 'OFE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo`
--

CREATE TABLE `tipo` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tipo`
--

INSERT INTO `tipo` (`id`, `nombre`) VALUES
(1, 'Aventura'),
(2, 'Educativo'),
(3, 'Cultural');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `rol` enum('ADMIN','CON','OFE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `email`, `password`, `username`, `rol`) VALUES
(1, 'maria@example.com', 'contrasena1', 'maria2024', 'ADMIN'),
(2, 'carlos.sanchez@correo.com', 'contrasena2', 'carlos123', 'OFE'),
(3, 'ana.gomez@correo.com', 'contrasena3', 'ana90', 'CON'),
(4, 'luisa@example.com', 'contrasena4', 'luisa1985', 'CON'),
(5, 'pedro@example.com', 'contrasena5', 'pedro2023', 'OFE'),
(6, 'sofia@example.com', 'contrasena6', 'sofia1990', 'ADMIN'),
(7, 'jorge@example.com', 'contrasena7', 'jorge1995', 'OFE');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkOf` (`ofertante_id`),
  ADD KEY `fkTipo` (`tipo_id`);

--
-- Indices de la tabla `consumidor`
--
ALTER TABLE `consumidor`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `consumo_actividad`
--
ALTER TABLE `consumo_actividad`
  ADD PRIMARY KEY (`id_actividad`,`id_consumidor`),
  ADD KEY `PKCompuesta` (`id_consumidor`);

--
-- Indices de la tabla `ofertante`
--
ALTER TABLE `ofertante`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `tipo`
--
ALTER TABLE `tipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usernameUnico` (`username`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividad`
--
ALTER TABLE `actividad`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `tipo`
--
ALTER TABLE `tipo`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `actividad`
--
ALTER TABLE `actividad`
  ADD CONSTRAINT `actividadTipoFK` FOREIGN KEY (`tipo_id`) REFERENCES `tipo` (`id`),
  ADD CONSTRAINT `ActividadOferFK` FOREIGN KEY (`ofertante_id`) REFERENCES `ofertante` (`username`);

--
-- Filtros para la tabla `consumidor`
--
ALTER TABLE `consumidor`
  ADD CONSTRAINT `consumidorUsuarioFK` FOREIGN KEY (`username`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `consumo_actividad`
--
ALTER TABLE `consumo_actividad`
  ADD CONSTRAINT `consumidorActFK` FOREIGN KEY (`id_consumidor`) REFERENCES `consumidor` (`username`),
  ADD CONSTRAINT `actividadConsFK` FOREIGN KEY (`id_actividad`) REFERENCES `actividad` (`id`);

--
-- Filtros para la tabla `ofertante`
--
ALTER TABLE `ofertante`
  ADD CONSTRAINT `ofertanteUsuarioFK` FOREIGN KEY (`username`) REFERENCES `usuario` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

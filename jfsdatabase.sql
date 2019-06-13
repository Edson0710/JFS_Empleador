-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2019 a las 16:58:28
-- Versión del servidor: 10.1.32-MariaDB
-- Versión de PHP: 5.6.36

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `jfsdatabase`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `id_comentarios` int(11) NOT NULL,
  `fk_id_empleador` int(11) NOT NULL,
  `calificacion` tinyint(4) NOT NULL,
  `comentario` varchar(85) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `discapacidad`
--

CREATE TABLE `discapacidad` (
  `id_discapacidad` int(11) NOT NULL,
  `discapacidad` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `discapacidad`
--

INSERT INTO `discapacidad` (`id_discapacidad`, `discapacidad`) VALUES
(1, 'Auditiva'),
(2, 'Mental intelect'),
(3, 'Mental psicosoc'),
(4, 'Motriz'),
(5, 'Verbal'),
(6, 'Visual'),
(7, 'Ninguno');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `fk_id_empleador` int(11) NOT NULL,
  `fk_id_discapacidad` int(11) NOT NULL,
  `fk_id_nivel_estudios` int(11) NOT NULL,
  `fk_id_nacionalidad` int(11) NOT NULL,
  `fk_id_idiomas_2` int(11) NOT NULL,
  `fk_id_idiomas_3` int(11) NOT NULL,
  `fk_id_estado_civil` int(11) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `contraseña` varchar(100) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellido` varchar(40) NOT NULL,
  `edad` tinyint(4) NOT NULL,
  `estatura` tinyint(4) NOT NULL,
  `domicilio` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `curp` varchar(18) NOT NULL,
  `puesto_deseado` varchar(50) NOT NULL,
  `ingreso_deseado` int(11) NOT NULL,
  `profesion` varchar(50) NOT NULL,
  `estado_contratacion` tinyint(1) NOT NULL,
  `estado_comentario` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleador`
--

CREATE TABLE `empleador` (
  `correo` varchar(50) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `giro` varchar(40) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `p_transporte` tinyint(1) NOT NULL,
  `p_bonos` tinyint(1) NOT NULL,
  `p_comisiones` tinyint(1) NOT NULL,
  `p_otro_1` varchar(50) NOT NULL,
  `p_otro_2` varchar(50) NOT NULL,
  `p_otro_3` varchar(50) NOT NULL,
  `id_empleador` int(11) NOT NULL,
  `calificacion_promedio` float NOT NULL DEFAULT '5'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleador`
--

INSERT INTO `empleador` (`correo`, `contrasena`, `nombre`, `giro`, `direccion`, `telefono`, `p_transporte`, `p_bonos`, `p_comisiones`, `p_otro_1`, `p_otro_2`, `p_otro_3`, `id_empleador`, `calificacion_promedio`) VALUES
('empresa1@gmail.com', 'ivan', 'Ferrari', 'Automotriz', 'ceti', '3311224455', 1, 1, 1, '1', '1', '1', 1, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado civil`
--

CREATE TABLE `estado civil` (
  `id_estado_civil` int(11) NOT NULL,
  `estado_civil` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estado civil`
--

INSERT INTO `estado civil` (`id_estado_civil`, `estado_civil`) VALUES
(1, 'Soltero'),
(2, 'Divorciado'),
(3, 'Viudo'),
(4, 'Casado'),
(5, 'Union libre');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `idiomas`
--

CREATE TABLE `idiomas` (
  `id_idiomas` int(11) NOT NULL,
  `idioma` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `idiomas`
--

INSERT INTO `idiomas` (`id_idiomas`, `idioma`) VALUES
(1, 'Ingles'),
(2, 'Chino'),
(3, 'Frances'),
(4, 'Aleman'),
(5, 'Italiano'),
(6, 'Ruso'),
(7, 'Japones'),
(8, 'Portugues'),
(9, 'Otro'),
(10, 'Ninguno');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nacionalidad`
--

CREATE TABLE `nacionalidad` (
  `id_nacionalidad` int(11) NOT NULL,
  `nacionalidad` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nacionalidad`
--

INSERT INTO `nacionalidad` (`id_nacionalidad`, `nacionalidad`) VALUES
(1, 'Aleman'),
(2, 'Argentino'),
(3, 'Brasileno'),
(4, 'Canadiense'),
(5, 'Chileno'),
(6, 'Chino'),
(7, 'Espanol'),
(8, 'Estadounidense'),
(9, 'Frances'),
(10, 'Indio'),
(11, 'Italiano'),
(12, 'Japones'),
(13, 'Mexicano'),
(14, 'Ruso'),
(15, 'Otro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `nivel estudios`
--

CREATE TABLE `nivel estudios` (
  `id_nivel_estudios` int(11) NOT NULL,
  `nivel estudios` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `nivel estudios`
--

INSERT INTO `nivel estudios` (`id_nivel_estudios`, `nivel estudios`) VALUES
(1, 'Preescolar'),
(2, 'Primaria'),
(3, 'Secundaria'),
(4, 'Bachillerato general'),
(5, 'Bachillerato tecnolo'),
(6, 'Profesional tecnico'),
(7, 'Licenciatura'),
(8, 'Maestria'),
(9, 'Doctorado'),
(10, 'Otro');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ofertas`
--

CREATE TABLE `ofertas` (
  `id_ofertas` int(11) NOT NULL,
  `fk_id_empleador` int(11) NOT NULL,
  `fk_id_estado_civil` int(11) NOT NULL,
  `fk_id_discapacidad` int(11) NOT NULL,
  `fk_id_nacionalidad` int(11) NOT NULL,
  `fk_id_nivel_estudios` int(11) NOT NULL,
  `fk_id_idiomas_2` int(11) NOT NULL,
  `fk_id_idiomas_3` int(11) NOT NULL,
  `calificacion_promedio` float NOT NULL,
  `puesto` varchar(50) NOT NULL,
  `sueldo` int(11) NOT NULL,
  `edad` tinyint(4) NOT NULL,
  `estatura` tinyint(4) NOT NULL,
  `profesion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `postulantes`
--

CREATE TABLE `postulantes` (
  `id_postulantes` int(11) NOT NULL,
  `fk_id_ofertas` int(11) NOT NULL,
  `fk_id_empleado` int(11) NOT NULL,
  `estado de contratacion` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`id_comentarios`),
  ADD KEY `fk_id_empleador` (`fk_id_empleador`);

--
-- Indices de la tabla `discapacidad`
--
ALTER TABLE `discapacidad`
  ADD PRIMARY KEY (`id_discapacidad`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD KEY `fk_id_empleador` (`fk_id_empleador`),
  ADD KEY `fk_id_discapacidad` (`fk_id_discapacidad`),
  ADD KEY `fk_id_nivel_estudios` (`fk_id_nivel_estudios`),
  ADD KEY `fk_id_nacionalidad` (`fk_id_nacionalidad`),
  ADD KEY `fk_id_idiomas_2` (`fk_id_idiomas_2`),
  ADD KEY `fk_id_idiomas_3` (`fk_id_idiomas_3`),
  ADD KEY `fk_id_estado_civil` (`fk_id_estado_civil`);

--
-- Indices de la tabla `empleador`
--
ALTER TABLE `empleador`
  ADD PRIMARY KEY (`id_empleador`);

--
-- Indices de la tabla `estado civil`
--
ALTER TABLE `estado civil`
  ADD PRIMARY KEY (`id_estado_civil`);

--
-- Indices de la tabla `idiomas`
--
ALTER TABLE `idiomas`
  ADD PRIMARY KEY (`id_idiomas`);

--
-- Indices de la tabla `nacionalidad`
--
ALTER TABLE `nacionalidad`
  ADD PRIMARY KEY (`id_nacionalidad`);

--
-- Indices de la tabla `nivel estudios`
--
ALTER TABLE `nivel estudios`
  ADD PRIMARY KEY (`id_nivel_estudios`);

--
-- Indices de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD PRIMARY KEY (`id_ofertas`),
  ADD KEY `fk_id_empleador` (`fk_id_empleador`),
  ADD KEY `fk_id_estado_civil` (`fk_id_estado_civil`),
  ADD KEY `fk_id_discapacidad` (`fk_id_discapacidad`),
  ADD KEY `fk_id_nacionalidad` (`fk_id_nacionalidad`),
  ADD KEY `fk_id_nivel_estudios` (`fk_id_nivel_estudios`),
  ADD KEY `fk_id_idiomas_2` (`fk_id_idiomas_2`),
  ADD KEY `fk_id_idiomas_3` (`fk_id_idiomas_3`);

--
-- Indices de la tabla `postulantes`
--
ALTER TABLE `postulantes`
  ADD PRIMARY KEY (`id_postulantes`),
  ADD KEY `fk_id_ofertas` (`fk_id_ofertas`),
  ADD KEY `fk_id_empleado` (`fk_id_empleado`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `id_comentarios` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `discapacidad`
--
ALTER TABLE `discapacidad`
  MODIFY `id_discapacidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id_empleado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empleador`
--
ALTER TABLE `empleador`
  MODIFY `id_empleador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `estado civil`
--
ALTER TABLE `estado civil`
  MODIFY `id_estado_civil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `idiomas`
--
ALTER TABLE `idiomas`
  MODIFY `id_idiomas` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `nacionalidad`
--
ALTER TABLE `nacionalidad`
  MODIFY `id_nacionalidad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `nivel estudios`
--
ALTER TABLE `nivel estudios`
  MODIFY `id_nivel_estudios` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ofertas`
--
ALTER TABLE `ofertas`
  MODIFY `id_ofertas` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `postulantes`
--
ALTER TABLE `postulantes`
  MODIFY `id_postulantes` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`fk_id_empleador`) REFERENCES `empleador` (`id_empleador`);

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`fk_id_empleador`) REFERENCES `empleador` (`id_empleador`),
  ADD CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`fk_id_nacionalidad`) REFERENCES `nacionalidad` (`id_nacionalidad`),
  ADD CONSTRAINT `empleado_ibfk_3` FOREIGN KEY (`fk_id_estado_civil`) REFERENCES `estado civil` (`id_estado_civil`),
  ADD CONSTRAINT `empleado_ibfk_4` FOREIGN KEY (`fk_id_discapacidad`) REFERENCES `discapacidad` (`id_discapacidad`),
  ADD CONSTRAINT `empleado_ibfk_5` FOREIGN KEY (`fk_id_nivel_estudios`) REFERENCES `nivel estudios` (`id_nivel_estudios`),
  ADD CONSTRAINT `empleado_ibfk_6` FOREIGN KEY (`fk_id_idiomas_2`) REFERENCES `idiomas` (`id_idiomas`),
  ADD CONSTRAINT `empleado_ibfk_7` FOREIGN KEY (`fk_id_idiomas_3`) REFERENCES `idiomas` (`id_idiomas`);

--
-- Filtros para la tabla `ofertas`
--
ALTER TABLE `ofertas`
  ADD CONSTRAINT `ofertas_ibfk_1` FOREIGN KEY (`fk_id_empleador`) REFERENCES `empleador` (`id_empleador`),
  ADD CONSTRAINT `ofertas_ibfk_2` FOREIGN KEY (`fk_id_estado_civil`) REFERENCES `estado civil` (`id_estado_civil`),
  ADD CONSTRAINT `ofertas_ibfk_3` FOREIGN KEY (`fk_id_discapacidad`) REFERENCES `discapacidad` (`id_discapacidad`),
  ADD CONSTRAINT `ofertas_ibfk_4` FOREIGN KEY (`fk_id_nacionalidad`) REFERENCES `nacionalidad` (`id_nacionalidad`),
  ADD CONSTRAINT `ofertas_ibfk_5` FOREIGN KEY (`fk_id_nivel_estudios`) REFERENCES `nivel estudios` (`id_nivel_estudios`),
  ADD CONSTRAINT `ofertas_ibfk_6` FOREIGN KEY (`fk_id_idiomas_2`) REFERENCES `idiomas` (`id_idiomas`),
  ADD CONSTRAINT `ofertas_ibfk_7` FOREIGN KEY (`fk_id_idiomas_3`) REFERENCES `idiomas` (`id_idiomas`);

--
-- Filtros para la tabla `postulantes`
--
ALTER TABLE `postulantes`
  ADD CONSTRAINT `postulantes_ibfk_1` FOREIGN KEY (`fk_id_empleado`) REFERENCES `empleado` (`id_empleado`),
  ADD CONSTRAINT `postulantes_ibfk_2` FOREIGN KEY (`fk_id_ofertas`) REFERENCES `ofertas` (`id_ofertas`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-10-2021 a las 19:13:49
-- Versión del servidor: 10.4.18-MariaDB
-- Versión de PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";



--
-- Base de datos: `pw`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `criticas`
--

CREATE TABLE `criticas` (
  `titulo` varchar(100) NOT NULL,
  `puntuacion` varchar(100) NOT NULL,
  `espectaculo` varchar(100) NOT NULL,
  `review` varchar(100) NOT NULL,
  `valoraciones` varchar(100) DEFAULT NULL,
  `autor` varchar(100) NOT NULL,
  `votantes` varchar(100) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculomultiple`
--

CREATE TABLE `espectaculomultiple` (
  `titulo_mult` varchar(100) NOT NULL,
  `descripcion_mult` varchar(100) NOT NULL,
  `categoria_mult` varchar(100) NOT NULL,
  `aforolocalidades_mult` int(11) NOT NULL,
  `localidadesvendidas_mult` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculopuntual`
--

CREATE TABLE `espectaculopuntual` (
  `titulo_puntual` varchar(100) NOT NULL,
  `descripcion_puntual` varchar(100) NOT NULL,
  `categoria_puntual` varchar(100) NOT NULL,
  `aforolocalidades_puntual` int(100) NOT NULL,
  `localidadesvendidas_puntual` int(100) NOT NULL,
  `fecha_puntual` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `espectaculotemporada`
--

CREATE TABLE `espectaculotemporada` (
  `titulo_temp` varchar(100) NOT NULL,
  `descripcion_temp` varchar(100) NOT NULL,
  `categoria_temp` varchar(100) NOT NULL,
  `aforolocalidades_temp` int(100) NOT NULL,
  `localidadesvendidas_temp` int(100) NOT NULL,
  `dia_temp` varchar(100) NOT NULL,
  `inicio_temp` date NOT NULL,
  `fin_temp` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multiplefechas`
--

CREATE TABLE `multiplefechas` (
  `titulo_mult` varchar(100) NOT NULL,
  `fecha_mult` date NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `nick` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `criticas`
--
ALTER TABLE `criticas`
  ADD PRIMARY KEY (`titulo`),
  ADD KEY `autor` (`autor`,`votantes`),
  ADD KEY `FK2_GPU` (`votantes`),
  ADD KEY `espectaculo` (`espectaculo`);

--
-- Indices de la tabla `espectaculomultiple`
--
ALTER TABLE `espectaculomultiple`
  ADD PRIMARY KEY (`titulo_mult`);

--
-- Indices de la tabla `espectaculopuntual`
--
ALTER TABLE `espectaculopuntual`
  ADD PRIMARY KEY (`titulo_puntual`);

--
-- Indices de la tabla `espectaculotemporada`
--
ALTER TABLE `espectaculotemporada`
  ADD PRIMARY KEY (`titulo_temp`);

--
-- Indices de la tabla `multiplefechas`
--
ALTER TABLE `multiplefechas`
  ADD KEY `titulo_mult` (`titulo_mult`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`correo`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `criticas`
--
ALTER TABLE `criticas`
  ADD CONSTRAINT `Relacion_autor` FOREIGN KEY (`autor`) REFERENCES `usuario` (`correo`),
  ADD CONSTRAINT `Relacion_votantes` FOREIGN KEY (`votantes`) REFERENCES `usuario` (`correo`),
  ADD CONSTRAINT `Relacion_multiple` FOREIGN KEY (`espectaculo`) REFERENCES `espectaculomultiple` (`titulo_mult`),
  ADD CONSTRAINT `Relacion_puntual` FOREIGN KEY (`espectaculo`) REFERENCES `espectaculopuntual` (`titulo_puntual`),
  ADD CONSTRAINT `Relacion_temporada` FOREIGN KEY (`espectaculo`) REFERENCES `espectaculotemporada` (`titulo_temp`);

--
-- Filtros para la tabla `espectaculomultiple`
--
ALTER TABLE `espectaculomultiple`
  ADD CONSTRAINT `Relacion_fechasM` FOREIGN KEY (`titulo_mult`) REFERENCES `multiplefechas` (`titulo_mult`);
COMMIT;

-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3307
-- Tiempo de generación: 17-08-2018 a las 07:23:54
-- Versión del servidor: 10.1.33-MariaDB
-- Versión de PHP: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `pdesign`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `num_reg` int(5) UNSIGNED NOT NULL,
  `Comprobante` varchar(20) COLLATE latin1_spanish_ci NOT NULL,
  `cod_e` int(5) UNSIGNED ZEROFILL NOT NULL,
  `cantidad` tinyint(3) UNSIGNED NOT NULL,
  `fecha` date NOT NULL,
  `Forma_pago` enum('EFECTIVO','CREDITO','DEBITO','OTRO') COLLATE latin1_spanish_ci NOT NULL,
  `costo` decimal(10,2) NOT NULL,
  `Forma_entrega` enum('FISICA','CORREO','PENDIENTE') COLLATE latin1_spanish_ci NOT NULL,
  `NO_PROVE` tinyint(3) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`num_reg`, `Comprobante`, `cod_e`, `cantidad`, `fecha`, `Forma_pago`, `costo`, `Forma_entrega`, `NO_PROVE`) VALUES
(1, '15952', 00003, 3, '2018-06-13', 'CREDITO', '39.00', 'PENDIENTE', 1),
(2, '15953', 00003, 3, '2018-06-11', 'DEBITO', '29.50', 'FISICA', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `rfc` char(13) NOT NULL,
  `nombre_e` varchar(30) NOT NULL,
  `ape_pat` varchar(25) NOT NULL,
  `ape_mat` varchar(25) NOT NULL,
  `genero` enum('Masculino','Femenino') DEFAULT 'Masculino',
  `fecha_nacimiento` date NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `locker` tinyint(3) NOT NULL,
  `area` set('Diseño','Finanzas','Experimental','Manufactura','Vigilante') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`rfc`, `nombre_e`, `ape_pat`, `ape_mat`, `genero`, `fecha_nacimiento`, `telefono`, `locker`, `area`) VALUES
('BOPD981111NZ8', 'Diego', 'Bolanos', 'Pardo', 'Masculino', '1997-12-12', '7757510151', 1, 'Experimental'),
('BOPD981111NZ9', 'Ana', 'Nieto', 'Perez', 'Femenino', '1999-09-21', '123456789', 3, 'Diseño'),
('MADR991113NZ7', 'Mario Alberto', 'Nieto', 'López', 'Masculino', '1999-01-10', '213938901', 2, 'Diseño');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `existencias`
--

CREATE TABLE `existencias` (
  `codigo` int(5) UNSIGNED ZEROFILL NOT NULL,
  `clase` enum('Herramienta','Material') COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(25) COLLATE latin1_spanish_ci NOT NULL,
  `especificaciones` text COLLATE latin1_spanish_ci,
  `cantidad` int(6) NOT NULL,
  `unidad_m` enum('','Metros','Galones','Kilos','Litros','Piezas','Mililitros') COLLATE latin1_spanish_ci DEFAULT NULL,
  `Tipo_m` enum('','Solido','Liquido') COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `existencias`
--

INSERT INTO `existencias` (`codigo`, `clase`, `nombre`, `especificaciones`, `cantidad`, `unidad_m`, `Tipo_m`) VALUES
(00001, 'Material', 'Trapos', 'Microfibra', 22, 'Piezas', ''),
(00002, 'Material', 'Soldadura', '', 33, 'Piezas', 'Solido'),
(00003, 'Herramienta', 'Desarmador', 'Cruz', 3, '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id_proveedor` tinyint(3) UNSIGNED NOT NULL,
  `Nombre_P` varchar(50) COLLATE latin1_spanish_ci NOT NULL,
  `tel_p` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `CORREO` varchar(50) COLLATE latin1_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id_proveedor`, `Nombre_P`, `tel_p`, `CORREO`) VALUES
(1, 'La casa del plomero', '', ''),
(2, 'Autozone Mexico', '', '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyecto`
--

CREATE TABLE `proyecto` (
  `id_p` int(5) UNSIGNED NOT NULL,
  `nombre_proyecto` varchar(45) NOT NULL,
  `caracteristicas` text,
  `tipo` enum('demo','diseño','prototipo','industrial','otro') NOT NULL,
  `Foto` longblob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proyecto`
--

INSERT INTO `proyecto` (`id_p`, `nombre_proyecto`, `caracteristicas`, `tipo`, `Foto`) VALUES
(1, 'Andrea', 'Prototipo de auto eléctrico', 'diseño', NULL),
(2, 'Coca-cola', 'Diseño para vehiculo de transporte', 'diseño', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usar`
--

CREATE TABLE `usar` (
  `conteo` int(11) NOT NULL,
  `no_proyecto` int(5) UNSIGNED NOT NULL,
  `requerido` int(5) UNSIGNED ZEROFILL DEFAULT NULL,
  `cantidad` int(6) UNSIGNED DEFAULT NULL,
  `id_user` tinyint(3) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `usar`
--

INSERT INTO `usar` (`conteo`, `no_proyecto`, `requerido`, `cantidad`, `id_user`) VALUES
(1, 1, 00001, 1, NULL),
(6, 2, 00003, 2, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` tinyint(3) UNSIGNED NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(35) NOT NULL,
  `tipo` enum('Admin','User') DEFAULT 'User',
  `rfc_e` char(13) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `username`, `password`, `tipo`, `rfc_e`) VALUES
(1, 'Diego12', '4cdf7d5309be38b3988763754de28310', 'Admin', 'BOPD981111NZ8'),
(3, 'Mario12', '4cdf7d5309be38b3988763754de28310', 'Admin', 'MADR991113NZ7');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`rfc`);

--
-- Indices de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  ADD PRIMARY KEY (`id_p`);

--
-- Indices de la tabla `usar`
--
ALTER TABLE `usar`
  ADD PRIMARY KEY (`conteo`),
  ADD KEY `no_proyecto` (`no_proyecto`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `requerido` (`requerido`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `rfc_e` (`rfc_e`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `proyecto`
--
ALTER TABLE `proyecto`
  MODIFY `id_p` int(5) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `usar`
--
ALTER TABLE `usar`
  MODIFY `conteo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` tinyint(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`rfc_e`) REFERENCES `empleado` (`rfc`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:        10.11.11-MariaDB - MariaDB Server
-- SO del servidor:              Linux
-- HeidiSQL Versión:            12.11.1.167
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Volcando datos para la tabla prestamoLibro.libro: ~15 rows (aproximadamente)
INSERT INTO `libro` (`id`, `autor`, `año`, `edicion`, `editorial`, `estado`, `isbn`, `titulo`, `prestamo_id`) VALUES
	(1, 'Gabriel García Márquez', '1967', '1ra', 'Editorial Sudamericana', b'0', '978-0307474728', 'Cien años de soledad', 1),
	(2, 'Isabel Allende', '1982', '1ra', 'Editorial Plaza & Janés', b'0', '978-0553383805', 'La casa de los espíritus', 2),
	(3, 'Mario Vargas Llosa', '1969', '2da', 'Seix Barral', b'0', '978-8433973964', 'Conversación en La Catedral', 1),
	(4, 'Jorge Luis Borges', '1944', '1ra', 'Emecé Editores', b'0', '978-8491050111', 'Ficciones', 3),
	(5, 'Julio Cortázar', '1963', '3ra', 'Editorial Sudamericana', b'0', '978-8437604949', 'Rayuela', 4),
	(6, 'Laura Restrepo', '2004', '1ra', 'Alfaguara', b'1', '978-8420472205', 'Delirio', NULL),
	(7, 'Carlos Fuentes', '1985', '1ra', 'Fondo de Cultura Económica', b'1', '978-8437503280', 'Cristóbal Nonato', NULL),
	(8, 'Juan Rulfo', '1955', '1ra', 'Fondo de Cultura Económica', b'0', '978-8420469281', 'Pedro Páramo', 4),
	(9, 'Pablo Neruda', '1971', '2da', 'Editorial Losada', b'0', '978-9561124539', 'Canto General', 5),
	(10, 'Miguel de Cervantes', '1605', '1ra', 'Francisco de Robles', b'0', '978-8491050104', 'Don Quijote de la Mancha', 6),
	(11, 'Fernando Vallejo', '1994', '1ra', 'Alfaguara', b'0', '978-8420447814', 'La virgen de los sicarios', 4),
	(12, 'Ernesto Sábato', '1961', '2da', 'Editorial Losada', b'0', '978-8437603652', 'Sobre héroes y tumbas', 2),
	(13, 'Homero', '800 a.C.', 'Edición crítica', 'Gredos', b'1', '978-8424915463', 'La Odisea', NULL),
	(14, 'Dante Alighieri', '1320', 'Edición comentada', 'Alianza Editorial', b'1', '978-8420634467', 'La Divina Comedia', NULL),
	(15, 'William Shakespeare', '1600', 'Edición Oxford', 'Oxford University Press', b'1', '978-0199535828', 'Hamlet', NULL);

-- Volcando datos para la tabla prestamoLibro.prestamo: ~6 rows (aproximadamente)
INSERT INTO `prestamo` (`id`, `fecha_devolucion`, `fecha_prestamo`, `observacion`, `usuario_id`) VALUES
	(1, '2025-09-17', '2025-09-15', 'NA', 1),
	(2, '2025-09-16', '2025-09-15', 'NA', 1),
	(3, '2025-09-20', '2025-09-11', 'NA', 5),
	(4, '2025-09-14', '2025-09-18', 'NA', 3),
	(5, '2025-10-02', '2025-09-09', 'NA', 5),
	(6, '2025-09-18', '2025-09-17', 'NA', 2);

-- Volcando datos para la tabla prestamoLibro.usuario: ~10 rows (aproximadamente)
INSERT INTO `usuario` (`id`, `correo`, `identificacion`, `nombre_completo`, `telefono`) VALUES
	(1, 'juan.perez@example.com', '1001', 'Juan Pérez', '3001112233'),
	(2, 'maria.gomez@example.com', '1002', 'María Gómez', '3002223344'),
	(3, 'carlos.lopez@example.com', '1003', 'Carlos López', '3003334455'),
	(4, 'ana.rodriguez@example.com', '1004', 'Ana Rodríguez', '3004445566'),
	(5, 'jose.martinez@example.com', '1005', 'José Martínez', '3005556677'),
	(6, 'laura.sanchez@example.com', '1006', 'Laura Sánchez', '3006667788'),
	(7, 'pedro.ramirez@example.com', '1007', 'Pedro Ramírez', '3007778899'),
	(8, 'sofia.hernandez@example.com', '1008', 'Sofía Hernández', '3008889900'),
	(9, 'andres.castillo@example.com', '1009', 'Andrés Castillo', '3011112233'),
	(10, 'valentina.mora@example.com', '1010', 'Valentina Mora', '3012223344');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

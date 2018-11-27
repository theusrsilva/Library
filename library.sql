-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3308
-- Generation Time: 27-Nov-2018 às 19:44
-- Versão do servidor: 5.7.23
-- versão do PHP: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--
CREATE DATABASE IF NOT EXISTS `library` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `library`;

-- --------------------------------------------------------

--
-- Estrutura da tabela `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
CREATE TABLE IF NOT EXISTS `emprestimo` (
  `id_emprestimo` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `data_emprestimo` date NOT NULL,
  `data_devolucao` date NOT NULL,
  `data_prevista` date NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_emprestimo`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `emprestimo_livro`
--

DROP TABLE IF EXISTS `emprestimo_livro`;
CREATE TABLE IF NOT EXISTS `emprestimo_livro` (
  `id_emprestimo` int(11) DEFAULT NULL,
  `id_livro` int(11) DEFAULT NULL,
  KEY `id_emprestimo` (`id_emprestimo`),
  KEY `id_livro` (`id_livro`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `estoque`
--

DROP TABLE IF EXISTS `estoque`;
CREATE TABLE IF NOT EXISTS `estoque` (
  `id_estoque` int(11) NOT NULL DEFAULT '1',
  `id_livro` int(11) NOT NULL,
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_estoque`,`id_livro`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `estoque`
--

INSERT INTO `estoque` (`id_estoque`, `id_livro`, `quantidade`) VALUES
(1, 36, 0),
(1, 35, 0),
(1, 34, 0),
(1, 33, 0),
(1, 32, 0),
(1, 37, 0),
(1, 38, 0);

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

DROP TABLE IF EXISTS `livro`;
CREATE TABLE IF NOT EXISTS `livro` (
  `id_livro` int(11) NOT NULL AUTO_INCREMENT,
  `id_estoque` int(11) DEFAULT NULL,
  `autor` varchar(30) NOT NULL,
  `isbn` varchar(17) NOT NULL,
  `titulo` varchar(30) NOT NULL,
  `ano` year(4) NOT NULL,
  PRIMARY KEY (`id_livro`),
  UNIQUE KEY `isbn` (`isbn`),
  UNIQUE KEY `isbn_2` (`isbn`),
  UNIQUE KEY `isbn_3` (`isbn`),
  KEY `id_estoque` (`id_estoque`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livro`
--

INSERT INTO `livro` (`id_livro`, `id_estoque`, `autor`, `isbn`, `titulo`, `ano`) VALUES
(38, 1, 'gcgcygcg', '747-64-764-7647-5', 'njbjbjbjk', 2000),
(37, 1, 'kjcscgsuv', '324-79-327-4324-3', 'fhiduhfudif', 2000),
(36, 1, 'deewfewf', '687-68-767-8688-7', 'diwiudhwfewf', 2000),
(35, 1, 'George Orwell', '978-98-631-8208-5', 'Revolução dos bichos', 2007),
(34, 1, 'José Saramago', '978-96-307-9195-3', 'Ensaio sobre a cegueira', 1995),
(33, 1, 'George Orwell', '978-80-268-7425-6', '1984', 2008),
(32, 1, 'Dan Brown', '978-90-245-7679-1', 'A Origem', 2017);

-- --------------------------------------------------------

--
-- Estrutura da tabela `multa`
--

DROP TABLE IF EXISTS `multa`;
CREATE TABLE IF NOT EXISTS `multa` (
  `id_multa` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `id_emprestimo` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `valor` decimal(6,2) DEFAULT NULL,
  PRIMARY KEY (`id_multa`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_emprestimo` (`id_emprestimo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `papel`
--

DROP TABLE IF EXISTS `papel`;
CREATE TABLE IF NOT EXISTS `papel` (
  `id_papel` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  `nome` varchar(30) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_papel`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `papel_usuario`
--

DROP TABLE IF EXISTS `papel_usuario`;
CREATE TABLE IF NOT EXISTS `papel_usuario` (
  `id_usuario` int(11) DEFAULT NULL,
  `id_papel` int(11) DEFAULT NULL,
  `nome` varchar(30) DEFAULT NULL,
  KEY `id_usuario` (`id_usuario`),
  KEY `id_papel` (`id_papel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `telefone` varchar(14) DEFAULT NULL,
  `email` varchar(40) NOT NULL,
  `senha_hash` varchar(60) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `cpf` (`cpf`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `cpf`, `nome`, `telefone`, `email`, `senha_hash`) VALUES
(1, '123.643.267-37', 'Matheus Rocha', '(21)98477-0307', 'theusrsilva@gmail.com', 'f7c878036d1dde70eedb6fc3e8a994ab'),
(5, '149.849.317-33', 'Guilherme Cavalcante', '(21)99319-5740', 'guikcsteam2@gmail.com', 'cb8dc34873148e97abe9b3c776600c36');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

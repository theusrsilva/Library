-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 24-Nov-2018 às 21:43
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
  `id_estoque` int(11) NOT NULL AUTO_INCREMENT,
  `id_livro` int(11) DEFAULT NULL,
  `quantidade` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_estoque`),
  KEY `id_livro` (`id_livro`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estrutura da tabela `livro`
--

DROP TABLE IF EXISTS `livro`;
CREATE TABLE IF NOT EXISTS `livro` (
  `id_livro` int(11) NOT NULL AUTO_INCREMENT,
  `id_estoque` int(11) DEFAULT NULL,
  `autor` varchar(30) NOT NULL,
  `isbn` char(13) NOT NULL,
  `titulo` varchar(30) NOT NULL,
  `ano` year(4) NOT NULL,
  PRIMARY KEY (`id_livro`),
  KEY `id_estoque` (`id_estoque`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `livro`
--

INSERT INTO `livro` (`id_livro`, `id_estoque`, `autor`, `isbn`, `titulo`, `ano`) VALUES
(1, 0, 'machado', '1234567898912', 'alguem de alguem', 1996),
(2, 0, 'machado', '1234567898912', 'alguem de alguem', 1996);

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
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `cpf`, `nome`, `telefone`, `email`, `senha_hash`) VALUES
(1, '123.643.267-37', 'Matheus Rocha', '(21)98477-0307', 'theusrsilva@gmail.com', 'f7c878036d1dde70eedb6fc3e8a994ab'),
(2, '123.456.789-98', 'Anne Oliveira', '(12)34567-8978', 'dhaiudh@diasah.com', '25f9e794323b453885f5181f1b624d0b'),
(3, '123.456.789-89', 'Lohan Bruno', '(78)89787-8787', 'hdsaijdh@dghsuai.com', '25f9e794323b453885f5181f1b624d0b'),
(4, '180.303.417-33', 'anne', '(21)97519-4355', 'anneoliveirar@gmail.com', 'c5c34687fd91d72b1899d04e00153119');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

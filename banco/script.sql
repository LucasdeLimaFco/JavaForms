/**
 * BACK-END / BANCO = Matheus Godoy.
 */
CREATE DATABASE IF NOT EXISTS cadastro_etec;
USE cadastro_etec;

CREATE TABLE IF NOT EXISTS aluno (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(100) NOT NULL,
    nome_mae VARCHAR(100) NOT NULL,
    nome_pai VARCHAR(100),
    cpf VARCHAR(14) NOT NULL,
    data_nasc VARCHAR(10) NOT NULL, 
    endereco VARCHAR(200) NOT NULL,
    cep VARCHAR(9) NOT NULL 
);

CREATE DATABASE cadastro_escola;

USE cadastro_escola;

CREATE TABLE aluno (

    id INT PRIMARY KEY AUTO_INCREMENT,

    nome VARCHAR(100),
    cpf VARCHAR(20),
    genero VARCHAR(30),
    data_nascimento VARCHAR(20),
    nacionalidade VARCHAR(50),
    telefone VARCHAR(30),
    email VARCHAR(100),
    endereco VARCHAR(200),
    cidade VARCHAR(100),
    cep VARCHAR(20)
);

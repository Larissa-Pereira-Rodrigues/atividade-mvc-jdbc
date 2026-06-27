# Cenário 1 - Clínica Veterinária

## Descrição

Sistema desenvolvido em Java utilizando arquitetura MVC, JDBC e PostgreSQL para gerenciamento de uma clínica veterinária.

## Funcionalidades

- Cadastro de Tutores
- Cadastro de Animais
- Cadastro de Consultas
- Listagem de Tutores
- Listagem de Animais
- Listagem de Consultas
- Consulta de animais por tutor
- Consulta de consultas por animal

## Tecnologias

- Java
- Maven
- PostgreSQL
- JDBC
- MVC

## Estrutura do Projeto

```
controller/
model/
repository/
service/
util/
Main.java
```

## Banco de Dados

```sql
CREATE TABLE tutor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    telefone VARCHAR(20)
);

CREATE TABLE animal (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    especie VARCHAR(50),
    tutor_id INTEGER REFERENCES tutor(id)
);

CREATE TABLE consulta (
    id SERIAL PRIMARY KEY,
    animal_id INTEGER REFERENCES animal(id),
    descricao VARCHAR(255),
    valor NUMERIC(10,2)
);
```

## Regras de Negócio

- Não cadastrar animal sem tutor.
- Não cadastrar consulta sem animal.
- Campos obrigatórios validados na camada Service.

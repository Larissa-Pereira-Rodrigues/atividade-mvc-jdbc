# Cenário 3 - Escola de Cursos Livres

## Descrição

Sistema acadêmico desenvolvido em Java utilizando arquitetura MVC, JDBC e PostgreSQL.

## Funcionalidades

- Cadastro de Alunos
- Cadastro de Cursos
- Realização de Matrículas
- Listagem de Alunos
- Listagem de Cursos
- Listagem de Matrículas
- Matrículas por Aluno
- Matrículas por Curso

## Tecnologias

- Java
- Maven
- PostgreSQL
- JDBC
- MVC

## Estrutura

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
CREATE TABLE aluno (...);

CREATE TABLE curso (...);

CREATE TABLE matricula (...);
```

## Regras de Negócio

- Não matricular aluno inexistente.
- Não matricular em curso inexistente.
- Não permitir matrícula duplicada.
- Não permitir matrícula quando não houver vagas.
- Ao realizar matrícula, reduzir automaticamente as vagas disponíveis.

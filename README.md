# Cenário 2 - Oficina Mecânica

## Descrição

Sistema para gerenciamento de clientes, veículos e ordens de serviço.

## Funcionalidades

- Cadastro de Clientes
- Cadastro de Veículos
- Abertura de Ordem de Serviço
- Listagem de Clientes
- Listagem de Veículos
- Listagem de Ordens
- Consulta de veículos por cliente
- Consulta de ordens por veículo

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
CREATE TABLE cliente (...);

CREATE TABLE veiculo (...);

CREATE TABLE ordem_servico (...);
```

## Regras de Negócio

- Não cadastrar veículo sem cliente.
- Não abrir ordem sem veículo.
- Status permitido:
  - ABERTA
  - CONCLUIDA

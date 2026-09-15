# 🐾 VetMark

> Sistema web de gestão para clínicas veterinárias, desenvolvido com Java e Spring Boot.

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)

---

## 📌 Sobre o projeto

O **VetMark** é um sistema de gestão desenvolvido para centralizar e organizar as principais operações de uma clínica veterinária.

A aplicação foi construída com foco em:

- organização dos dados da clínica;
- cadastro e gerenciamento de clientes e pets;
- controle de veterinários;
- gerenciamento de produtos e serviços;
- agendamento de atendimentos;
- histórico clínico dos animais;
- gerenciamento de pedidos;
- controle financeiro;
- geração de relatórios;
- autenticação e proteção das rotas;
- disponibilização de uma API REST utilizando DTOs.

O projeto utiliza uma arquitetura baseada em **Spring Boot + MVC + Thymeleaf + JPA**, mantendo a interface web e a API REST no mesmo backend.

---

## 🎯 Objetivos

O VetMark tem como principais objetivos:

1. Centralizar as informações da clínica veterinária.
2. Facilitar o gerenciamento de clientes e seus animais.
3. Organizar a agenda de atendimentos.
4. Registrar informações clínicas dos pets.
5. Controlar produtos, serviços e pedidos.
6. Apoiar o controle financeiro da clínica.
7. Disponibilizar dados por meio de uma API REST.
8. Aplicar boas práticas de organização e separação de responsabilidades.
9. Implementar autenticação e controle de acesso.
10. Servir como projeto prático para aplicação de conceitos de desenvolvimento backend e web.

---

## 🧩 Funcionalidades

### 👤 Clientes

- Cadastro de clientes.
- Edição de clientes.
- Listagem de clientes.
- Exclusão de clientes.
- Informações de contato.
- CPF.
- Data de cadastro.
- API REST para operações de clientes.

### 🐾 Pets

- Cadastro de animais.
- Associação do pet ao cliente.
- Espécie.
- Raça.
- Data de nascimento.
- Listagem e edição.
- API REST para operações de pets.

### 🩺 Veterinários

- Cadastro de veterinários.
- Nome e dados de contato.
- CRMV.
- Especialidade.
- Controle de ativo/inativo.
- API REST para operações de veterinários.

### 📦 Produtos

- Cadastro de produtos.
- Descrição.
- Preço.
- Controle de estoque.
- Status ativo/inativo.
- API REST para operações de produtos.

### 💉 Serviços

- Cadastro de serviços veterinários.
- Descrição.
- Valor.
- Controle de ativo/inativo.
- API REST para operações de serviços.

### 📅 Agendamentos

- Agendamento de consultas e serviços.
- Associação entre cliente, pet, veterinário e serviço.
- Data e horário.
- Status do agendamento.
- Observações.
- Visualização diária.
- Visualização semanal.
- Visualização mensal.
- API REST para operações de agendamento.

### 📋 Histórico Clínico

- Registro das consultas.
- Queixa principal.
- Diagnóstico.
- Tratamento.
- Medicação.
- Observações.
- Associação com pet.
- Associação com veterinário.
- Associação opcional com agendamento.
- Consulta do histórico específico de um pet.
- API REST para operações de histórico clínico.

### 🧾 Pedidos

- Cadastro de pedidos.
- Associação do pedido ao cliente.
- Inclusão de produtos.
- Quantidade de itens.
- Valor unitário.
- Valor total dos itens.
- Cálculo do valor total do pedido.
- Status do pedido.
- API REST para operações de pedidos.

### 💰 Financeiro

- Registro de movimentações financeiras.
- Controle de entradas e despesas.
- Categorias.
- Data da movimentação.
- Associação opcional com pedidos.

### 📈 Relatórios

- Área destinada à visualização de informações gerenciais da clínica.
- Estrutura preparada para consolidação de dados operacionais e financeiros.

### 🔐 Segurança

O projeto utiliza **Spring Security** para proteger as áreas internas da aplicação.

O acesso público inclui a página de login e recursos estáticos necessários para o funcionamento da interface.

As demais funcionalidades são protegidas por autenticação.

---

# 🏗️ Arquitetura

O projeto segue uma organização baseada em camadas:

```text
src/
└── main/
    ├── java/
    │   └── sp/
    │       └── senai/
    │           └── org/
    │               └── vetmark/
    │                   ├── api/
    │                   ├── config/
    │                   ├── controller/
    │                   ├── dto/
    │                   │   ├── request/
    │                   │   └── response/
    │                   ├── exception/
    │                   ├── model/
    │                   │   ├── entity/
    │                   │   └── enums/
    │                   ├── repository/
    │                   └── service/
    │
    └── resources/
        ├── static/
        │   ├── css/
        │   ├── js/
        │   └── images/
        ├── templates/
        │   ├── agendamento/
        │   ├── cliente/
        │   ├── financeiro/
        │   ├── historico-clinico/
        │   ├── pedido/
        │   ├── pet/
        │   ├── produto/
        │   ├── relatorio/
        │   ├── servico/
        │   ├── veterinario/
        │   └── ...
        └── application.properties
```

### Camadas

**Controller**

Responsável por receber as requisições e direcioná-las para os serviços apropriados.

**Controller API**

Responsável pelos endpoints REST da aplicação.

**Service**

Centraliza as regras de negócio e a comunicação entre controllers e repositories.

**Repository**

Responsável pelo acesso aos dados utilizando Spring Data JPA.

**Entity**

Representa as entidades persistidas no banco de dados.

**DTO**

Define os objetos utilizados para entrada e saída de dados da API.

---

# 🔌 API REST

Além da aplicação web com Thymeleaf, o VetMark disponibiliza endpoints REST.

A API utiliza a separação:

```text
Request → entrada de dados
Response → saída de dados
```

Essa abordagem evita expor diretamente as entidades JPA nas respostas da API e permite controlar quais informações serão recebidas e retornadas.

## Endpoints disponíveis

### Clientes

```text
GET    /api/clientes
GET    /api/clientes/{id}
POST   /api/clientes
PUT    /api/clientes/{id}
DELETE /api/clientes/{id}
```

### Pets

```text
GET    /api/pets
GET    /api/pets/{id}
POST   /api/pets
PUT    /api/pets/{id}
DELETE /api/pets/{id}
```

### Veterinários

```text
GET    /api/veterinarios
GET    /api/veterinarios/{id}
POST   /api/veterinarios
PUT    /api/veterinarios/{id}
DELETE /api/veterinarios/{id}
```

### Produtos

```text
GET    /api/produtos
GET    /api/produtos/{id}
POST   /api/produtos
PUT    /api/produtos/{id}
DELETE /api/produtos/{id}
```

### Serviços

```text
GET    /api/servicos
GET    /api/servicos/{id}
POST   /api/servicos
PUT    /api/servicos/{id}
DELETE /api/servicos/{id}
```

### Agendamentos

```text
GET    /api/agendamentos
GET    /api/agendamentos/{id}
POST   /api/agendamentos
PUT    /api/agendamentos/{id}
DELETE /api/agendamentos/{id}
```

### Históricos Clínicos

```text
GET    /api/historicos-clinicos
GET    /api/historicos-clinicos/{id}
POST   /api/historicos-clinicos
PUT    /api/historicos-clinicos/{id}
DELETE /api/historicos-clinicos/{id}
```

### Pedidos

```text
GET    /api/pedidos
GET    /api/pedidos/{id}
POST   /api/pedidos
PUT    /api/pedidos/{id}
DELETE /api/pedidos/{id}
```

---

# 📨 Exemplo de requisição

Exemplo de criação de um serviço:

```json
{
  "nome": "Consulta veterinária",
  "descricao": "Consulta clínica geral",
  "valor": 120.00,
  "ativo": true
}
```

Exemplo de criação de um pet:

```json
{
  "nome": "Rex",
  "especie": "CACHORRO",
  "raca": "Labrador",
  "dataNascimento": "2021-05-10",
  "clienteId": 1
}
```

Exemplo de criação de um histórico clínico:

```json
{
  "dataConsulta": "2026-09-15T14:30:00",
  "queixaPrincipal": "Falta de apetite",
  "diagnostico": "Avaliação clínica inicial",
  "tratamento": "Acompanhamento",
  "medicacao": "Conforme prescrição",
  "observacoes": "Retorno recomendado",
  "petId": 1,
  "veterinarioId": 2,
  "agendamentoId": 5
}
```

---

# 🗃️ Modelo de dados

Entre os principais relacionamentos do sistema:

```text
Pessoa
 ├── Cliente
 │    └── Pets
 │
 └── Veterinário

Cliente
 ├── Pet
 ├── Agendamento
 └── Pedido

Pet
 ├── Agendamento
 └── Histórico Clínico

Veterinário
 ├── Agendamento
 └── Histórico Clínico

Serviço
 └── Agendamento

Pedido
 └── ItemPedido
      └── Produto

Movimentação Financeira
 └── Pedido (opcional)
```

A aplicação utiliza JPA/Hibernate para o mapeamento objeto-relacional.

---

# 🛠️ Tecnologias utilizadas

## Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- Bean Validation
- Lombok

## Frontend

- HTML5
- CSS3
- JavaScript
- Thymeleaf

## Banco de dados

- MySQL

## API

- REST
- JSON
- DTOs Request/Response

## Ferramentas

- IntelliJ IDEA
- Maven
- Git
- GitHub

---

# ⚙️ Como executar o projeto

## Pré-requisitos

Antes de executar o projeto, certifique-se de possuir:

- Java instalado;
- Maven instalado ou Maven Wrapper disponível;
- MySQL instalado e em execução;
- Git, caso o projeto seja clonado do GitHub.

## 1. Clone o repositório

```bash
git clone URL_DO_SEU_REPOSITORIO
```

Entre na pasta:

```bash
cd vetmark
```

## 2. Configure o banco de dados

Crie um banco MySQL:

```sql
CREATE DATABASE vetmark;
```

Depois configure as informações de conexão no:

```text
src/main/resources/application.properties
```

Exemplo:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vetmark
spring.datasource.username=root
spring.datasource.password=SUA_SENHA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

> Não publique senhas reais ou credenciais no GitHub.

## 3. Execute o projeto

Com Maven:

```bash
mvn spring-boot:run
```

Ou utilizando o Maven Wrapper:

```bash
./mvnw spring-boot:run
```

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Após a inicialização, acesse a aplicação pelo endereço configurado pelo Spring Boot, normalmente:

```text
http://localhost:8080
```

---

# 🔐 Autenticação

As áreas internas do sistema são protegidas pelo Spring Security.

A aplicação possui uma página de login e utiliza autenticação baseada em usuário.

As rotas públicas incluem:

```text
/
 /login
 /css/**
 /js/**
 /images/**
```

As demais rotas exigem autenticação.

---

# 🎨 Interface

A interface do VetMark foi desenvolvida com uma identidade visual própria para o segmento veterinário.

A identidade utiliza principalmente tons de:

- verde;
- azul;
- branco;
- cinza claro.

O layout conta com:

- sidebar de navegação;
- header;
- footer;
- tabelas responsivas;
- formulários;
- cards;
- indicadores de status;
- mensagens de estado vazio;
- navegação responsiva;
- estados de foco para acessibilidade.

---

# 📅 Agenda

A agenda possui três formas principais de visualização:

```text
Diária
Semanal
Mensal
```

Os agendamentos possuem relacionamento com:

```text
Cliente
Pet
Veterinário
Serviço
```

Isso permite centralizar as informações necessárias para o atendimento.

---

# 📋 Histórico Clínico

O histórico clínico permite registrar informações relacionadas ao atendimento do animal.

Cada registro pode conter:

```text
Data da consulta
Queixa principal
Diagnóstico
Tratamento
Medicação
Observações
Pet
Veterinário
Agendamento
```

Também existe uma consulta específica do histórico de determinado pet:

```text
/historico-clinico/pet/{petId}
```

---

# 🧾 Pedidos e itens

Os pedidos possuem uma estrutura composta por itens:

```text
Pedido
   │
   ├── Cliente
   │
   └── Itens
        ├── Produto
        ├── Quantidade
        ├── Valor unitário
        └── Valor total
```

O valor total do pedido é calculado a partir dos seus itens.

Na API, o cliente informa os produtos e suas quantidades, enquanto o backend busca os preços cadastrados dos produtos e calcula os valores.

---

# 📐 Boas práticas aplicadas

O projeto busca aplicar conceitos importantes de desenvolvimento de software, incluindo:

- separação de responsabilidades;
- arquitetura em camadas;
- uso de DTOs;
- encapsulamento das regras de negócio;
- utilização de repositories;
- tratamento de entidades relacionadas;
- validação de dados;
- autenticação;
- uso de HTTP status codes;
- API REST;
- organização de pacotes;
- responsividade;
- preocupação com acessibilidade;
- reutilização de componentes visuais.

---

# 🚀 Evolução do projeto

O VetMark está sendo desenvolvido de forma incremental.

A evolução do projeto inclui:

- [x] Estrutura inicial do sistema
- [x] CRUD de clientes
- [x] CRUD de pets
- [x] CRUD de veterinários
- [x] CRUD de produtos
- [x] CRUD de serviços
- [x] Agenda
- [x] Histórico clínico
- [x] Pedidos
- [x] Controle financeiro
- [x] Relatórios
- [x] Spring Security
- [x] DTOs Request/Response
- [x] API REST para principais módulos
- [x] Interface responsiva
- [ ] Aprimoramento de validações
- [ ] Padronização global de tratamento de erros
- [ ] Documentação da API com OpenAPI/Swagger
- [ ] Testes automatizados
- [ ] Melhorias de filtros e paginação
- [ ] Melhorias nos relatórios

---

# 🧪 Testes da API

A API pode ser testada utilizando ferramentas como:

- Postman;
- Insomnia;
- Thunder Client;
- IntelliJ HTTP Client.

Exemplo:

```text
GET http://localhost:8080/api/clientes
```

ou:

```text
GET http://localhost:8080/api/pets
```

Para operações `POST` e `PUT`, envie os dados no formato JSON.

---

# 📂 Organização dos DTOs

Os DTOs foram organizados em duas categorias:

```text
dto/
├── request/
└── response/
```

### Request

Representa os dados recebidos pela API.

Exemplo:

```text
ClienteRequest
PetRequest
ProdutoRequest
ServicoRequest
VeterinarioRequest
PedidoRequest
HistoricoClinicoRequest
```

### Response

Representa os dados devolvidos pela API.

Exemplo:

```text
ClienteResponse
PetResponse
ProdutoResponse
ServicoResponse
VeterinarioResponse
PedidoResponse
HistoricoClinicoResponse
```

Essa separação facilita a evolução da API e reduz o acoplamento entre os endpoints e as entidades JPA.

---

# 📌 Status do projeto

**Em desenvolvimento 🚧**

O sistema possui uma base funcional de gestão veterinária, com interface web, persistência em banco de dados, autenticação e API REST.

Novas funcionalidades, melhorias de arquitetura, validações e testes continuam sendo implementados.

---

# 👨‍💻 Desenvolvimento

Projeto desenvolvido como aplicação prática para estudo e desenvolvimento de sistemas web utilizando Java e Spring Boot.

**VetMark — Gestão Veterinária** 🐾

---

## 📄 Licença

Este projeto pode ser utilizado para fins educacionais e de estudo.

Caso seja disponibilizado com uma licença específica no repositório, consulte o arquivo `LICENSE` para conhecer os termos de uso.

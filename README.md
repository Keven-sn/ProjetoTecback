# 🎬 TechBack — API de Plataforma de Streaming
API REST completa para gerenciamento de usuários, assinaturas, conteúdos, favoritos, métodos de pagamento e planos de assinatura.

Este projeto foi desenvolvido utilizando **Spring Boot 3**, **Spring Data JPA**, **Hibernate**, **PostgreSQL**, **Flyway**, **Bean Validation**, **Lombok** e arquitetura REST moderna.

---

# 📑 Sumário
- [📌 Sobre o Projeto](#-sobre-o-projeto)
- [⚙ Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [📁 Estrutura do Projeto](#-estrutura-do-projeto)
- [🗄️ Modelagem do Banco de Dados](#️-modelagem-do-banco-de-dados)
- [🧩 Entidades](#-entidades)
- [📥 DTOs](#-dtos)
- [🌐 Endpoints da API](#-endpoints-da-api)
- [🚀 Como Executar o Projeto](#-como-executar-o-projeto)
- [🐘 Configuração PostgreSQL](#-configuração-postgresql)
- [🔧 Migrations com Flyway](#-migrations-com-flyway)
- [✔ Critérios de Aceite Atendidos](#-critérios-de-aceite-atendidos)
- [📌 Extras Implementados](#-extras-implementados)
- [👤 Autores](#-autores)

---

# 📌 Sobre o Projeto
O **TechBack** é uma API REST inspirada em plataformas de streaming (Netflix / Prime Video).

- Funcionalidades principais:
- Cadastro e gerenciamento de usuários
- Endereços vinculados a usuários
- Catálogo de conteúdos (filmes e séries)
- Planos de assinatura
- Assinaturas de usuários
- Métodos de pagamento tokenizados
- Lista de conteúdos favoritos
- O projeto foi desenvolvido com foco acadêmico, priorizando clareza,
organização e aderência ao modelo relacional fornecido pelo professor.

---

# ⚙ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
- **Spring Web**
- **Spring Data JPA (Hibernate)**
- **H2 Database (ambiente de desenvolvimento)**
- **Spring Validation (Jakarta Validation)**
- **Lombok**
- **Spring Security Crypto (apenas BCrypt para hash de senha)**
- **SpringDoc OpenAPI (Swagger)**

---

# 📁 Estrutura do Projeto

src/main/java/br/uniesp/si/techback/  
│  
├── controller/ → Controllers REST  
├── service/ → Regras de negócio  
├── repository/ → Interfaces JPA  
├── model/ → Entidades JPA  
├── dto/ → DTOs de entrada e saída
├── config/ → Configurações auxiliares 
└── TechbackApplication.java

---

# 🗄️ Modelagem do Banco de Dados

Todas as tabelas são criadas automaticamente via Flyway (V1).

Principais tabelas:

- **usuarios**
- **enderecos**
- **conteudo**
- **planos**
- **assinaturas**
- **favoritos**
- **metodos_pagamento**

---

# 🧩 Entidades

✔ Usuario  
✔ Endereco  
✔ Conteudo  
✔ Plano  
✔ Assinatura  
✔ MetodoPagamento  
✔ Favorito

Todas utilizando:

- `UUID`
- `LocalDate / LocalDateTime`
- `Relacionamentos JPA (@ManyToOne, @EmbeddedId)`
- `@EqualsAndHashCode(of = "id")`

---

# 📥 DTOs

O projeto segue o padrão:

- `CreateDTO` (entrada)
- `ResponseDTO` (saída)

Exemplos:

- `UsuarioCreateDTO`
- `UsuarioResponseDTO`

---

# 🌐 Endpoints da API

A documentação completa está no Swagger:
```bash
  http://localhost:8080/swagger-ui.html
```

### Exemplos de endpoints:

### **Usuários**

- POST `/api/v1/usuarios`  
- GET `/api/v1/usuarios/{id}`


### **Conteúdos**

- GET `/api/v1/conteudos`  
- POST `/api/v1/conteudos`  
- PUT `/api/v1/conteudos/{id}`  
- DELETE `/api/v1/conteudos/{id}`


### **Planos**

- GET `/api/v1/planos`  
- GET `/api/v1/planos/{id}`  
- POST `/api/v1/planos`


### **Assinaturas**

- POST `/api/v1/assinaturas`  
- PUT `/api/v1/assinaturas/{id}/cancelar`  
- GET `/api/v1/assinaturas/usuario/{usuarioId}`  
- GET `/api/v1/assinaturas/status/{status}`

### **Favoritos**

- POST `/api/v1/favoritos`  
- DELETE `/api/v1/favoritos`  
- GET `/api/v1/favoritos/usuario/{usuarioId}`


### **Métodos de Pagamento**

- POST `/api/v1/metodos-pagamento`  
- GET `/api/v1/metodos-pagamento/usuario/{usuarioId}`  
- DELETE `/api/v1/metodos-pagamento/{id}`


---

# 🚀 Como Executar o Projeto

### 1. Clonar o repositório
```bash
  git clone https://github.com/Keven-sn/ProjetoTecback.git
  ```

### 2. Configurar PostgreSQL
```yaml
 Crie o banco: CREATE DATABASE techback;
```

### 3. Configurar application.yml
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/techback
    username: postgres
    password: sua_senha
 ```
    
### 4. Executar
```yaml
Via Maven: mvn spring-boot:run
```
### 🐘 Configuração PostgreSQL
```yaml
Habilitar extensão: CREATE EXTENSION IF NOT EXISTS pgcrypto;
```
### 🔧 Migrations com Flyway

V1 — Criação das tabelas
```yaml
Arquivo: src/main/resources/db/migration/V1__create_tables.sql
```
V2 — Inserts iniciais
```yaml
Arquivo: src/main/resources/db/migration/V2__insert.sql
```

#### O banco H2 será inicializado automaticamente.

---

# ✔ Critérios de Aceite Atendidos  
✔ Modelo relacional fiel ao enunciado  
✔ Arquitetura REST  
✔ Uso correto de DTOs  
✔ Regras de negócio no Service  
✔ JPA com relacionamentos corretos  
✔ Enum para estados fixos  
✔ Hash de senha com BCrypt  
✔ Swagger configurado  
✔ Código limpo e organizado

---

# 📌 Extras Implementados

🔵 Uso de DTOs para entrada e saída de dados  
🔵 Utilização de UUID como chave primária  
🔵 Uso de enums para estados fixos do domínio  
🔵 Relacionamentos JPA corretamente mapeados  
🔵 Chave composta em Favorito com `@EmbeddedId`  
🔵 Hash de senha com BCrypt  
🔵 Documentação automática da API com Swagger  
🔵 Filtros de busca no catálogo de conteúdos  
🔵 Integração externa com a API ViaCEP para consulta de endereços

---

# 👤 Autores

##### Keven Douglas  
##### Entony Lucas

---
##### Projeto desenvolvido para disciplina de Tecnologias para Back-end  
##### Universidade: UNIESP
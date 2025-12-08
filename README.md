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
- [👤 Autor](#-autor)

---

# 📌 Sobre o Projeto
O **TechBack** é uma API REST inspirada em plataformas de streaming (como Netflix/Prime).  
Permite:

- Cadastro e gerenciamento de usuários
- Controle de assinaturas
- Métodos de pagamento
- Catálogo de conteúdos
- Lista de favoritos
- Planos de assinatura
- Integração externa (ViaCEP)
- Autovalidação com Bean Validation + custom validators

O código segue padrões profissionais e está totalmente modularizado.

---

# ⚙ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.4**
- **Spring Web**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **Flyway**
- **Lombok**
- **Bean Validation (Jakarta Validation)**
- **RestTemplate para integração externa**
- **H2 (modo desenvolvimento opcional)**

---

# 📁 Estrutura do Projeto

src/main/java/br/uniesp/si/techback/  
│  
├── controller/ → Controllers REST  
├── service/ → Regras de negócio  
├── repository/ → Interfaces JPA  
├── model/ → Entidades JPA  
├── dto/ → DTOs de entrada e saída  
├── exception/ → Exceções e Handler Global  
├── config/ → Configurações (RestTemplate, etc.)  
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
- `@Entity`
- `@Builder`
- `@EqualsAndHashCode(of = "id")`

---

# 📥 DTOs

O projeto segue o padrão:

- `CreateDTO` (entrada)
- `UpdateDTO` (entrada)
- `ResponseDTO` (saída)

Exemplos:

- `UsuarioCreateDTO`
- `UsuarioUpdateDTO`
- `UsuarioResponseDTO`

---

# 🌐 Endpoints da API

A documentação completa está no Swagger:

http://localhost:8080/swagger-ui.html


### Principais recursos fornecidos:

### **Usuários**

POST /usuarios  
GET /usuarios/{id}  
PUT /usuarios/{id}  
GET /usuarios


### **Conteúdos**

GET /conteudos  
POST /conteudos  
PUT /conteudos/{id}  
DELETE /conteudos/{id}


### **Planos**

GET /planos  
GET /planos/{id}  
POST /planos


### **Assinaturas**

POST /assinaturas  
DELETE /assinaturas/{id}  
GET /assinaturas/usuario/{id}  
GET /assinaturas/status/{status}


### **Favoritos**

POST /favoritos  
DELETE /favoritos  
GET /favoritos/{usuarioId}


### **Métodos de Pagamento**

POST /metodos  
GET /metodos/usuario/{id}  
DELETE /metodos/{id}


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

#### Flyway roda AUTOMATICAMENTE ao iniciar o projeto.

---

# ✔ Critérios de Aceite Atendidos  
✔ Modelo relacional fiel às tabelas  
✔ Validações Bean Validation  
✔ Custom Validators:  
CPF/CNPJ  
EnumSubset  
SenhaForte  
✔ APIs REST completas  
✔ Consultas JPQL implementadas  
✔ Lombok aplicado em todas entidades  
✔ Exceções globais padronizadas  
✔ Logs estruturados  
✔ Integração externa ViaCEP  
✔ Flyway configurado  

---

# 📌 Extras Implementados

🔵 Integração automática com ViaCEP  
🔵 Renovação automática (mock) de assinaturas  
🔵 Mascaramento automático de cartão  
🔵 Estratégia extensível de pagamento  
🔵 DTOs separados para entrada/saída  
🔵 Regras de negócio isoladas no serviço  
🔵 Custom exceptions limpas e padronizadas

---

# 👤 Autores

##### Keven Douglas, Entony Lucas, Ridael Paulo  
##### Projeto desenvolvido para disciplina de Tecnologias para Back-end  
##### Universidade: UNIESP
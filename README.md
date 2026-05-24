
# Desafio Técnico — Event Scheduler

Este projeto foi desenvolvido como parte do desafio técnico proposto para avaliação de desenvolvimento full-stack e análise de incidentes.

A aplicação consiste em um sistema simples de gerenciamento de agendamentos, permitindo o cadastro de pessoas, locais e eventos agendados.

Stack utilizada:
- Backend: Java + Spring Boot
- Frontend: Angular
- Banco de dados: MySQL
- Containerização: Docker + Docker Compose

---

# Arquitetura

Optei por uma arquitetura em camadas inspirada em Clean Architecture, mantendo a aplicação simples, desacoplada e de fácil manutenção.

Estrutura principal do backend:

```text
presentation/   -> controllers e handlers HTTP
application/    -> regras de negócio e casos de uso
domain/         -> entidades e contratos
infrastructure/ -> persistência, JPA e configurações
````

---

# Modelagem

Relacionamentos principais:

* Uma pessoa pode possuir vários agendamentos
* Um agendamento pertence a um local

```text
Person (1) -------- (N) EventSchedule
Location (1) ------ (N) EventSchedule
```

Entidades principais:

## Person

| Campo | Tipo   |
| ----- | ------ |
| id    | Long   |
| name  | String |

## Location

| Campo   | Tipo   |
| ------- | ------ |
| id      | Long   |
| address | String |

## EventSchedule

| Campo       | Tipo          |
| ----------- | ------------- |
| id          | Long          |
| name        | String        |
| description | String        |
| count       | Long          |
| scheduledAt | LocalDateTime |
| createdAt   | LocalDateTime |
| updatedAt   | LocalDateTime |
| person_id   | FK            |
| location_id | FK            |

---

# Endpoints

## Events

```http
POST   /events
GET    /events
GET    /events/{id}
PUT    /events/{id}
DELETE /events/{id}
```

## Persons

```http
POST   /persons
GET    /persons
GET    /persons/{id}
PUT    /persons/{id}
DELETE /persons/{id}
```

## Locations

```http
POST   /locations
GET    /locations
GET    /locations/{id}
```

---

# Exemplos de requisição

## Criar pessoa

```http
POST /persons
```

```json
{
  "name": "Mariana Silva"
}
```

Resposta:

```json
{
  "id": 1,
  "name": "Mariana Silva"
}
```

---

## Criar local

```http
POST /locations
```

```json
{
  "address": "Av. Paulista, 1000"
}
```

Resposta:

```json
{
  "id": 1,
  "address": "Av. Paulista, 1000"
}
```

---

## Criar agendamento

```http
POST /events
```

```json
{
  "name": "Reunião de Arquitetura",
  "description": "Discussão sobre melhorias no módulo",
  "count": 10,
  "scheduledAt": "2026-06-01T14:00:00",
  "person_id": 1,
  "location_id": 1
}
```

Resposta:

```json
{
  "id": 1,
  "name": "Reunião de Arquitetura",
  "description": "Discussão sobre melhorias no módulo",
  "count": 10,
  "person_id": 1,
  "location": {
    "id": 1,
    "address": "Av. Paulista, 1000"
  },
  "scheduledAt": "2026-06-01T14:00:00",
  "createdAt": "2026-05-23T11:00:00",
  "updatedAt": "2026-05-23T11:00:00"
}
```

**Observação importante**: Use `person_id` e `location_id` (com underscore, não camelCase como `personId`).

---

# Frontend

Acesse o frontend em `http://localhost:3000` (Angular dev server).

Funcionalidades:
- **Eventos**: criar, listar, editar, deletar. Form com campos: nome, descrição, quantidade, data/hora, ID da pessoa, ID da localização.
- **Pessoas**: criar, listar, editar, deletar. Form com campo: nome.
- **Localizações**: criar, listar, editar, deletar. Form com campo: endereço.

Todos os componentes têm botões "Novo", "Editar" e "Excluir", com indicação dinâmica entre "Criar" vs "Editando".

---

# Como executar o projeto

## Executando localmente

### Backend

Windows:

```powershell
cd projedata
mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
cd projedata
./mvnw spring-boot:run
```

API disponível em:

```text
http://localhost:8080
```

---

### Frontend

```bash
cd events-scheduler
npm install
npm start
```

Frontend disponível em:

```text
http://localhost:4200
```

---

# Executando com Docker

```bash
docker-compose up --build
```

Serviços:

* Backend
* Frontend
* MySQL

---

# Validações implementadas

Algumas regras implementadas:

* nome obrigatório
* data de agendamento obrigatória
* quantidade não pode ser negativa
* validação de entidades inexistentes
* tratamento de exceções HTTP

Também adicionei logs básicos para facilitar diagnóstico de erros e rastreamento de operações.

---

# Testes

Foram adicionados testes cobrindo os principais fluxos da aplicação:

* criação de agendamento
* busca de registros
* validações principais
* cenários de erro

---

# Decisões técnicas e trade-offs

Preferi manter a aplicação simples e objetiva, priorizando:

* organização do código
* separação de responsabilidades
* legibilidade
* facilidade de manutenção

Decidi não implementar autenticação/autorização para manter o foco no fluxo principal do desafio e evitar complexidade desnecessária no tempo disponível.

Também optei por utilizar:

* Spring Data JPA pela produtividade
* Angular standalone components para simplificar o frontend
* Docker Compose para facilitar execução local

Melhorias futuras que eu adicionaria em um cenário produtivo:

* autenticação JWT
* observabilidade
* migrations com Flyway
* testes E2E
* pipeline CI/CD
* cache e paginação
* monitoramento centralizado

---

# Estrutura do projeto

Backend:

```text
projedata/src/main/java/com/desafio_fullstack
```

Frontend:

```text
events-scheduler/src/app
```

---

# Observações finais

O objetivo principal deste projeto foi demonstrar:

* desenvolvimento full-stack
* modelagem de dados
* organização arquitetural
* tratamento de erros
* boas práticas de engenharia
* clareza de implementação

```


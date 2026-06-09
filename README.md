# 🛒 E-commerce Distribuído - Marco 1

## 📌 Objetivo
Implementar a comunicação síncrona entre o **Checkout API** e o **Serviço de Estoque**, utilizando microsserviços em Java com Spring Boot.

---

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Maven
- REST API
- Git e GitHub

---

## 🏗️ Arquitetura

O sistema é composto por dois microsserviços:

### 🔹 Estoque Service
Responsável por gerenciar e verificar a disponibilidade de produtos.

- Porta: **8081**
- Endpoint:

---

### 🔹 Checkout API
Responsável por receber pedidos e consultar o estoque antes de confirmar a compra.

- Porta: **8082**
- Endpoint:

---

## 🔄 Fluxo de Funcionamento

1. O cliente envia um pedido para o **Checkout API**.
2. O Checkout faz uma requisição HTTP síncrona ao **Serviço de Estoque**.
3. O Estoque verifica a disponibilidade do produto.
4. O Checkout decide:
   - ✔️ Se disponível → cria o pedido
   - ❌ Se indisponível → retorna erro

---

## 📦 Exemplo de Requisição

### POST - Criar Pedido

### Body (JSON)

```json
{
  "produto": "produto_A",
  "quantidade": 2
}
```

---

## Docker

Para subir toda a aplicacao com Docker, instale o Docker Desktop e execute na raiz do projeto:

```bash
docker compose up --build
```

Servicos disponiveis:

```text
Checkout UI/API: http://localhost:8082
Estoque API:     http://localhost:8090
RabbitMQ UI:     http://localhost:15672
RabbitMQ AMQP:   localhost:5672
```

Login do RabbitMQ:

```text
ecommerce
ecommerce
```

Teste rapido:

```bash
curl "http://localhost:8090/estoque/verificar?produto=produto_A&quantidade=2"

curl -X POST "http://localhost:8082/pedidos" \
  -H "Content-Type: application/json" \
  -d '{"produto":"produto_A","quantidade":2}'
```

Para parar os containers:

```bash
docker compose down
```

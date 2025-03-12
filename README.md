# API de Catálogo de Produtos

Um microsserviço RESTful para gerenciamento de catálogos de produtos com recursos avançados de busca e operações CRUD completas.

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- PostgreSQL
- Spring Data JPA
- SpringDoc OpenAPI (Swagger)
- Maven

## Pré-requisitos

- Java 17 ou superior
- Maven
- PostgreSQL

## Começando

### Configuração do Banco de Dados

1. Crie um banco de dados PostgreSQL:
```sql
CREATE DATABASE product_catalog;
```

2. Atualize a configuração do banco de dados em `src/main/resources/application.yml` se necessário:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/product_catalog
    username: seu_usuario
    password: sua_senha
```

### Executando a Aplicação

1. Clone o repositório
2. Navegue até o diretório do projeto
3. Compile o projeto:
```bash
mvn clean install
```
4. Execute a aplicação:
```bash
mvn spring-boot:run
```

A aplicação será iniciada em `http://localhost:8080`

## Documentação da API

Acesse a documentação Swagger UI em: `http://localhost:8080/swagger-ui.html`

### Endpoints Disponíveis

#### Produtos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/api/v1/products` | Criar um novo produto |
| PUT | `/api/v1/products/{id}` | Atualizar um produto existente |
| DELETE | `/api/v1/products/{id}` | Excluir um produto |
| GET | `/api/v1/products/{id}` | Obter um produto por ID |
| GET | `/api/v1/products/search` | Pesquisar produtos com filtros |

### Exemplos de API

#### Criar Produto
```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "Smartphone X",
  "description": "Smartphone modelo mais recente",
  "price": 999.99,
  "category": "Eletrônicos",
  "sku": "PHONE-X-001",
  "stockQuantity": 50
}
```

#### Pesquisar Produtos
```http
GET /api/v1/products/search?name=smartphone&category=Eletronicos&minPrice=500&maxPrice=1000&page=0&size=10&sort=price,desc
```

Parâmetros de Consulta:
- `name`: Filtro por nome do produto (opcional)
- `category`: Filtro por categoria do produto (opcional)
- `minPrice`: Filtro de preço mínimo (opcional)
- `maxPrice`: Filtro de preço máximo (opcional)
- `page`: Número da página (padrão: 0)
- `size`: Tamanho da página (padrão: 20)
- `sort`: Campo e direção de ordenação (opcional)

## Modelo de Dados

### Produto

| Campo | Tipo | Descrição |
|-------|------|-----------|
| id | UUID | Identificador único |
| name | String | Nome do produto |
| description | Text | Descrição do produto |
| price | BigDecimal | Preço do produto |
| category | String | Categoria do produto |
| sku | String | Código de identificação do produto |
| stockQuantity | Integer | Quantidade disponível |
| createdAt | DateTime | Data de criação |
| updatedAt | DateTime | Data da última atualização |

## Tratamento de Erros

A API utiliza códigos de status HTTP padrão:

- 200: Sucesso
- 201: Criado
- 400: Requisição Inválida
- 404: Não Encontrado
- 500: Erro Interno do Servidor

Formato da Resposta de Erro:
```json
{
  "timestamp": "2025-01-01T12:00:00Z",
  "status": 404,
  "error": "Não Encontrado",
  "message": "Produto não encontrado com id: 123",
  "path": "/api/v1/products/123"
}
```

## Desenvolvimento

### Compilando para Produção

```bash
mvn clean package
```

O arquivo JAR compilado estará no diretório `target`.

### Executando Testes

```bash
mvn test
```

## Segurança

A API utiliza Spring Security com autenticação JWT. Todos os endpoints requerem autenticação, exceto o Swagger UI e a documentação da API. // Deixei opcional

## Monitoramento

Endpoint de verificação de saúde: `http://localhost:8080/actuator/health`

## Contribuindo

1. Faça um fork do repositório
2. Crie uma branch para sua feature
3. Faça commit das suas alterações
4. Faça push para a branch
5. Crie um Pull Request

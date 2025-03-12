# Product Catalog API

A RESTful microservice for managing product catalogs with advanced search capabilities and complete CRUD operations.

## Technologies

- Java 17
- Spring Boot 3.2.0
- PostgreSQL
- Spring Data JPA
- SpringDoc OpenAPI (Swagger)
- Maven

## Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL

## Getting Started

### Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE product_catalog;
```

2. Update database configuration in `src/main/resources/application.yml` if needed:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/product_catalog
    username: your_username
    password: your_password
```

### Running the Application

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
```bash
mvn clean install
```
4. Run the application:
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## API Documentation

Access the Swagger UI documentation at: `http://localhost:8080/swagger-ui.html`

### Available Endpoints

#### Products

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/v1/products` | Create a new product |
| PUT | `/api/v1/products/{id}` | Update an existing product |
| DELETE | `/api/v1/products/{id}` | Delete a product |
| GET | `/api/v1/products/{id}` | Get a product by ID |
| GET | `/api/v1/products/search` | Search products with filters |

### API Examples

#### Create Product
```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "Smartphone X",
  "description": "Latest model smartphone",
  "price": 999.99,
  "category": "Electronics",
  "sku": "PHONE-X-001",
  "stockQuantity": 50
}
```

#### Search Products
```http
GET /api/v1/products/search?name=smartphone&category=Electronics&minPrice=500&maxPrice=1000&page=0&size=10&sort=price,desc
```

Query Parameters:
- `name`: Product name filter (optional)
- `category`: Product category filter (optional)
- `minPrice`: Minimum price filter (optional)
- `maxPrice`: Maximum price filter (optional)
- `page`: Page number (default: 0)
- `size`: Page size (default: 20)
- `sort`: Sort field and direction (optional)

## Data Model

### Product

| Field | Type | Description |
|-------|------|-------------|
| id | UUID | Unique identifier |
| name | String | Product name |
| description | Text | Product description |
| price | BigDecimal | Product price |
| category | String | Product category |
| sku | String | Stock keeping unit |
| stockQuantity | Integer | Available quantity |
| createdAt | DateTime | Creation timestamp |
| updatedAt | DateTime | Last update timestamp |

## Error Handling

The API uses standard HTTP status codes:

- 200: Success
- 201: Created
- 400: Bad Request
- 404: Not Found
- 500: Internal Server Error

Error Response Format:
```json
{
  "timestamp": "2025-01-01T12:00:00Z",
  "status": 404,
  "error": "Not Found",
  "message": "Product not found with id: 123",
  "path": "/api/v1/products/123"
}
```

## Development

### Building for Production

```bash
mvn clean package
```

The built JAR will be in the `target` directory.

### Running Tests

```bash
mvn test
```

## Security

The API uses Spring Security with JWT authentication. All endpoints require authentication except for the Swagger UI and API documentation.

## Monitoring

Health check endpoint: `http://localhost:8080/actuator/health`

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
# Finance API

A RESTful API for personal finance management built with Java and Spring Boot. Users can register, authenticate with JWT, and manage their own income and expense transactions securely.

## Features

- User registration and login with JWT authentication
- Password encryption with BCrypt
- Each user only sees their own transactions
- Filter transactions by type (INCOME/EXPENSE) or category
- Bean Validation on all request bodies
- Global error handling

## Technologies

- Java 21
- Spring Boot 4.1
- Spring Security 7
- JWT (jjwt 0.12.6)
- Spring Data JPA / Hibernate
- PostgreSQL 16
- Docker

## Endpoints

### Auth
| Method | Route | Description |
|--------|-------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive a JWT token |

### Transactions (requires Bearer token)
| Method | Route | Description |
|--------|-------|-------------|
| GET | `/transactions` | List all transactions for the logged user |
| POST | `/transactions` | Create a new transaction |
| GET | `/transactions/{id}` | Get a transaction by ID |
| DELETE | `/transactions/{id}` | Delete a transaction |
| GET | `/transactions/type?type=INCOME` | Filter by type (INCOME or EXPENSE) |
| GET | `/transactions/category?category=Food` | Filter by category |

## Running locally

**Prerequisites:** Docker and Java 21

1. Clone the repository
2. Start the database:
```bash
docker compose up -d
```
3. Run the application in IntelliJ or with Maven:
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## Authentication

Register to get a token, then include it in every protected request:
```
Authorization: Bearer <your_token>
```

## Request example

**Register:**
```json
POST /auth/register
{
  "name": "Daniel",
  "email": "daniel@email.com",
  "password": "123456"
}
```

**Create transaction:**
```json
POST /transactions
{
  "title": "Salary",
  "amount": 3000,
  "type": "INCOME",
  "category": "Work",
  "date": "2026-08-13"
}
```

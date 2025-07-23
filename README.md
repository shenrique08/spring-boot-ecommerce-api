# 🛒 Spring Boot API UML Case

A fully featured, production-ready RESTful API simulating a simplified e-commerce system. Built with **Spring Boot 3**, **PostgreSQL**, and **JWT-based security**, this project demonstrates a clean, layered architecture ideal for scalable enterprise applications. It includes user authentication, product and category management, order processing, and shopping cart functionality.

---

## 📚 Table of Contents

- [✨ Features](#-features)  
- [🛠 Technologies](#-technologies)  
- [✅ Prerequisites](#-prerequisites)  
- [🚀 Getting Started](#-getting-started)  
- [🧪 Running Tests](#-running-tests)  
- [📡 API Endpoints](#-api-endpoints)  
- [🏗️ Project Structure](#-project-structure)  
- [📄 License](#-license)

---

## ✨ Features

- ✅ **RESTful API**: Manage products, categories, customers, carts, and orders.  
- 🧱 **Layered Architecture**: Clear separation between domain, service, repository, and controller layers.  
- 🗄 **Database Integration**: Uses PostgreSQL with Spring Data JPA.  
- 🧬 **Database Migrations**: Automatic schema updates using Flyway.  
- 🔐 **Security**: End-to-end JWT authentication with Spring Security.  
- 🐳 **Containerized**: Easily deploy with Docker and Docker Compose.  
- 🧪 **Test Coverage**: Includes unit and integration tests for critical components.  

---

## 🛠 Technologies

| Technology       | Description                              |
|------------------|------------------------------------------|
| **Java 21**       | Core programming language                |
| **Spring Boot 3.5.3** | Backend framework                     |
| **Spring Web**    | RESTful API development                  |
| **Spring Data JPA** | ORM for PostgreSQL                    |
| **PostgreSQL**    | Relational database                      |
| **Spring Security** | Authentication & Authorization         |
| **JWT**           | Secure, stateless token-based login      |
| **Flyway**        | Database schema migration tool           |
| **Lombok**        | Eliminates boilerplate code              |
| **Maven**         | Build automation                         |
| **Docker**        | Containerization                         |

---

## ✅ Prerequisites

Make sure you have the following installed:

- Java JDK 21+
- Apache Maven
- Docker & Docker Compose
- PostgreSQL (only if not using Docker)

---

## 🚀 Getting Started

### Option 1: Run with Docker (Recommended)

```bash
docker-compose up
```

- The API will be available at `http://localhost:8080`.

### Option 2: Run Locally with Maven

1. **Start PostgreSQL manually**  
   Configure your database settings in `src/main/resources/application.properties`.

2. **Run the application**

```bash
./mvnw spring-boot:run
```

---

## 🧪 Running Tests

Execute the full test suite:

```bash
./mvnw test
```

---

## 📡 API Endpoints

### 🔖 Categories

- `GET /categories` – List all categories  
- `POST /categories` – Create category  
- `PUT /categories/{id}` – Update category  
- `DELETE /categories/{id}` – Delete category  

### 🛍️ Products

- `GET /products` – List all products  
- `POST /products` – Create product  
- `PUT /products/{id}` – Update product  
- `DELETE /products/{id}` – Delete product  

### 👤 Customers

- `GET /customers` – List all customers  
- `POST /customers` – Register customer  
- `PUT /customers/{id}` – Update customer  
- `DELETE /customers/{id}` – Delete customer  

### 📦 Orders

- `GET /orders` – List all orders  
- `POST /orders` – Create order  
- `GET /orders/{id}` – Get order by ID  

### 🛒 Shopping Cart

- `GET /carts` – Get cart  
- `POST /carts` – Add item to cart  
- `DELETE /carts/{productId}` – Remove item from cart  

---

## 🏗️ Project Structure

```
springboot-api-uml-case/
├── compose.yaml
├── Dockerfile
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/org/api/springbootapiumlcase/
│   │   │   ├── config/            # Security config, test config
│   │   │   ├── domain/            # Entities and enums
│   │   │   ├── dto/               # Data transfer objects
│   │   │   ├── repositories/      # Spring Data interfaces
│   │   │   ├── resources/         # REST controllers and exception handlers
│   │   │   ├── security/          # JWT filters and utilities
│   │   │   └── services/          # Business logic
│   └── resources/
│       ├── application.properties
│       ├── application-prod.properties
│       ├── application-test.properties
│       └── db/migration/          # Flyway migration scripts
│           ├── V1__create-initial-schema.sql
│           └── V2__add_password_to_customer.sql
└── test/
    └── java/org/api/springbootapiumlcase/
        ├── SpringbootApiUmlCaseApplicationTests.java
        ├── resources/CategoryResourceTest.java
        └── services/CategoryServiceTest.java
```

---

## 📄 License

This project is open-source and available under the [MIT License](LICENSE).

---


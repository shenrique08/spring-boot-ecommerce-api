# 🛒 Spring Boot RESTful E-commerce Service

A robust and scalable e-commerce REST API built with **Spring Boot**.  
This application manages users, products, categories, and orders.  
It follows clean architecture principles, uses **Spring Data JPA** for persistence, and **Spring Security** for role-based access control.

---

## ✨ Features

- **User Management**: CRUD operations for users.
- **Product Catalog**: Manage products and their categories.
- **Order Processing**: Create and manage customer orders.
- **Payment System**: Basic payment entity linked to orders.
- **Database Seeding**: Auto data seeding for dev and test profiles.
- **Security**: Role-based access control using Spring Security.

---

## 🛠️ Technologies Used

- **Java 21**
- **Spring Boot 3.5.3**
  - Spring Web (REST API)
  - Spring Data JPA (Persistence)
  - Spring Security (Auth)
- **Maven** (Dependency Management)
- **Lombok** (Less boilerplate)
- **Databases**:
  - PostgreSQL (development)
  - H2 In-Memory (testing)
- **Docker Compose** (for PostgreSQL container)

---

## 🚀 How to Run

### ✅ Prerequisites

- JDK 21+
- Maven 3.x
- Docker & Docker Compose (for development)

### 📦 Clone the Repository

```bash
git clone <repository-url>
cd spring-boot-ecommerce-api
```

### 🐳 Start PostgreSQL (Dev Profile)

```bash
docker-compose up -d
```

This will start a PostgreSQL container.

### ▶️ Run the Application (Dev)

```bash
mvn spring-boot:run
```

Access the API at: [http://localhost:8080](http://localhost:8080)

---

### 🧪 Run with Test Profile (In-Memory DB)

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

Access the H2 console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

Credentials are set in `application-test.properties`.

---

## 🔐 Authentication

Uses **HTTP Basic Authentication**.

### Default Users (from `SecurityConfig.java`):

| Role  | Username | Password  |
|-------|----------|-----------|
| ADMIN | admin    | admin     |
| USER  | user     | password  |

---

## 📡 API Endpoints

### 👤 User Resources (`/users`)

| Method | Endpoint         | Description           | Access        |
|--------|------------------|-----------------------|---------------|
| GET    | `/users`         | Get all users         | ADMIN         |
| GET    | `/users/{id}`    | Get user by ID        | ADMIN         |
| POST   | `/users`         | Create new user       | Authenticated |
| PUT    | `/users/{id}`    | Update existing user  | Authenticated |
| DELETE | `/users/{id}`    | Delete user           | Authenticated |

_Source: `UserResource.java`_

---

### 📦 Product Resources (`/products`)

| Method | Endpoint          | Description        | Access        |
|--------|-------------------|--------------------|---------------|
| GET    | `/products`       | Get all products   | Authenticated |
| GET    | `/products/{id}`  | Get product by ID  | Authenticated |

_Source: `ProductResource.java`_

---

### 🗂️ Category Resources (`/categories`)

| Method | Endpoint            | Description           | Access        |
|--------|---------------------|-----------------------|---------------|
| GET    | `/categories`       | Get all categories    | Authenticated |
| GET    | `/categories/{id}`  | Get category by ID    | Authenticated |

_Source: `CategoryResource.java`_

---

### 📑 Order Resources (`/orders`)

| Method | Endpoint         | Description          | Access        |
|--------|------------------|----------------------|---------------|
| GET    | `/orders`        | Get all orders       | Authenticated |
| GET    | `/orders/{id}`   | Get order by ID      | Authenticated |

_Source: `OrderResource.java`_

---

## 🗃️ Database Configuration

### Development Profile (`dev`)

- **Database**: PostgreSQL  
- **URL**: `jdbc:postgresql://localhost:5432/mydatabase`  
- **Username**: `myuser`  
- **Password**: `secret`  
- **DDL Auto**: `create-drop`  
- **Seed Data**: Loaded via `TestConfig.java` (users, products, categories, orders)

---

### Testing Profile (`test`)

- **Database**: H2 (In-memory)
- **URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: *(empty)*
- **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

---


## 📄 License

This project is licensed under the **MIT License**.

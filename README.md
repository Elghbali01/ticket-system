# Advanced Ticket Management System (Learning Project)

## ⚠️ Project Status

This project is **not finished**.

It was created as a **learning laboratory to deeply understand Spring Boot**, backend architecture, and REST API development.

The goal of this repository is educational.
Anyone is free to **clone, study, modify, or continue the project**.

---

# 📚 Purpose of the Project

This project was built while studying **Spring Boot step-by-step**, applying theoretical concepts directly in a practical backend project.

Main learning objectives:

* Understand how **Spring Boot works internally**
* Learn **REST API design**
* Implement a **clean backend architecture**
* Practice **JPA / Hibernate**
* Implement **JWT authentication**
* Apply **DTO pattern and Mappers**
* Understand **Spring Security**
* Learn how to write **controller tests**

This project follows a structured learning roadmap.

---

# 🧠 Project Concept

The system simulates a **Ticket Management System** used in companies to manage:

* Bugs
* Incidents
* Support requests

Example workflow:

```
User creates ticket
      ↓
Agent processes ticket
      ↓
Ticket status changes
      ↓
History is recorded
```

---

# 🏗 Architecture

The project follows a **layered architecture**:

```
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

And uses **DTOs to avoid exposing entities**.

---

# 📦 Project Structure

```
src/main/java/com/issam/ticket_system
```

### controller

Handles HTTP requests.

```
AuthController
UserController
TicketController
```

Responsible for:

* API endpoints
* Request/Response handling

Example:

```
POST /auth/login
POST /users
POST /tickets
```

---

### service

Contains the **business logic**.

```
AuthService
UserService
TicketService
```

Responsibilities:

* process data
* validate operations
* call repositories
* return DTOs

---

### repository

Handles **database access** using Spring Data JPA.

```
UserRepository
TicketRepository
TicketHistoryRepository
```

Example methods:

```
save()
findById()
findAll()
deleteById()
```

---

### entity

Represents **database tables**.

```
User
Ticket
TicketHistory
```

Example:

```
User
 └── Tickets (OneToMany)

Ticket
 └── User (ManyToOne)
```

---

### dto

DTOs are used to **separate API models from database entities**.

```
UserCreateDTO
UserResponseDTO
LoginRequestDTO
LoginResponseDTO
TicketCreateDTO
TicketResponseDTO
```

Why DTOs?

* avoid exposing database entities
* control API data
* improve security

---

### mapper

Converts between **DTO and Entity**.

```
UserMapper
TicketMapper
```

Example:

```
UserCreateDTO → User Entity
User Entity → UserResponseDTO
```

---

### security

Contains authentication and security configuration.

```
SecurityConfig
JwtService
JwtFilter
PasswordConfig
```

Features implemented:

* JWT authentication
* Stateless sessions
* Password hashing (BCrypt)
* Security filter chain

---

### enums

```
TicketStatus
```

Ticket workflow:

```
OPEN
IN_PROGRESS
RESOLVED
CLOSED
```

---

# 🔐 Authentication

The API uses **JWT authentication**.

Login flow:

```
POST /auth/login
      ↓
validate user credentials
      ↓
generate JWT token
      ↓
client uses token in Authorization header
```

Example header:

```
Authorization: Bearer <token>
```

---

# 🗄 Database

The project uses:

```
MySQL
Spring Data JPA
Hibernate
```

Main tables:

```
User
Ticket
TicketHistory
```

---

# 🧪 Tests

Basic controller tests were implemented using:

```
JUnit
SpringBootTest
MockMvc
```

These tests are part of the learning process.

---

# ▶️ Running the Project

Requirements:

```
Java 17+
Maven
MySQL
```

Run the application:

```
mvn spring-boot:run
```

or using IntelliJ.

---

# 🚀 Possible Future Improvements

This project can be extended with:

* Role system (USER / AGENT / ADMIN)
* Ticket assignment
* Comments system
* Pagination and filtering
* Global exception handling
* API documentation (Swagger)
* Docker support
* Frontend application (React / Next.js)

---

# 👨‍💻 Author

**Issam Elghbali**

Backend learning project built while studying Spring Boot.

---

# 📜 License

This project is open for learning purposes.
Feel free to fork or continue development.

# 🎬 PlatziPlay API

A RESTful API for movie management built with Spring Boot, implementing Clean Architecture and Hexagonal Architecture principles.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Documentation](#api-documentation)
- [Usage Examples](#usage-examples)
- [Project Structure](#project-structure)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## �� Overview

PlatziPlay is a comprehensive movie management API that demonstrates modern Spring Boot development practices. It provides CRUD operations for movies with features like validation, exception handling, and comprehensive API documentation.

## ✨ Features

- **RESTful API** with full CRUD operations
- **Clean Architecture** implementation
- **Hexagonal Architecture** (Ports & Adapters)
- **Data Validation** with Bean Validation
- **Exception Handling** with global error management
- **API Documentation** with OpenAPI/Swagger
- **Database Integration** with PostgreSQL
- **Object Mapping** with MapStruct
- **Code Generation** with Lombok
- **Profile-based Configuration**

## 🏗️ Architecture

This project follows **Clean Architecture** and **Hexagonal Architecture** principles:

```
┌─────────────────────────────────────┐
│           Web Layer                 │ ← Controllers, DTOs
├─────────────────────────────────────┤
│           Domain Layer              │ ← Business Logic, Services
├─────────────────────────────────────┤
│           Infrastructure Layer      │ ← Persistence, External APIs
└─────────────────────────────────────┘
```

### Key Principles:
- **Dependency Inversion**: Domain defines contracts, infrastructure implements them
- **Separation of Concerns**: Each layer has a specific responsibility
- **SOLID Principles**: Clean, maintainable, and extensible code
- **Testability**: Easy to unit test with dependency injection

## 🛠️ Tech Stack

### Core Framework
- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **Spring Web MVC**
- **Spring Validation**

### Database
- **PostgreSQL**
- **Hibernate**

### Development Tools
- **Lombok** - Code generation
- **MapStruct** - Object mapping
- **Gradle** - Build tool
- **OpenAPI/Swagger** - API documentation

### Testing
- **JUnit 5**
- **Spring Boot Test**

## 📋 Prerequisites

Before running this project, make sure you have:

- **Java 21** or higher
- **PostgreSQL 12** or higher
- **Gradle 7.0** or higher (or use Gradle Wrapper)

## 🚀 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd platzi-play
   ```

2. **Set up PostgreSQL Database**
   ```sql
   CREATE DATABASE platzi_play_db;
   CREATE USER username WITH PASSWORD 'username.platzi';
   GRANT ALL PRIVILEGES ON DATABASE platzi_play_db TO username;
   ```

3. **Build the project**
   ```bash
   ./gradlew build
   ```

4. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

The application will start on `http://localhost:8090`

## ⚙️ Configuration

### Application Properties

The application uses profile-based configuration:

#### `application.properties` (Base configuration)
```properties
spring.profiles.active=dev
spring.application.name=platzi-play
server.servlet.context-path=/platzi-play/api
spring.datasource.driver-class-name=org.postgresql.Driver
```

#### `application-dev.properties` (Development profile)
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/platzi_play_db
spring.datasource.username=username
spring.datasource.password=username.platzi
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.sql.init.mode=always
server.port=8090
```

### Environment Variables

You can override database configuration using environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/platzi_play_db
export SPRING_DATASOURCE_USERNAME=your_username
export SPRING_DATASOURCE_PASSWORD=your_password
```

## 📚 API Documentation

### Swagger UI
Once the application is running, access the interactive API documentation at:
```
http://localhost:8090/platzi-play/api/swagger-ui.html
```

### Base URL
```
http://localhost:8090/platzi-play/api
```

## �� Usage Examples

### Get All Movies
```bash
curl -X GET "http://localhost:8090/platzi-play/api/movies"
```

### Get Movie by ID
```bash
curl -X GET "http://localhost:8090/platzi-play/api/movies/1"
```

### Create a New Movie
```bash
curl -X POST "http://localhost:8090/platzi-play/api/movies" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix",
    "duration": 136,
    "genre": "SCI_FI",
    "releaseDate": "1999-03-31",
    "rating": 4.7,
    "state": true
  }'
```

### Update a Movie
```bash
curl -X PUT "http://localhost:8090/platzi-play/api/movies/1" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix Reloaded",
    "releaseDate": "2003-05-15",
    "rating": 4.5
  }'
```

### Delete a Movie
```bash
curl -X DELETE "http://localhost:8090/platzi-play/api/movies/1"
```

## 📁 Project Structure

```
src/main/java/com/platzi/play/
├── PlatziPlayApplication.java          # Main application class
├── domain/                             # Domain Layer
│   ├── dto/                           # Data Transfer Objects
│   │   ├── MovieDto.java              # Movie DTO
│   │   └── UpdateMovieDto.java        # Update Movie DTO
│   ├── enums/                         # Domain Enumerations
│   │   └── Genre.java                 # Movie Genres
│   ├── exception/                     # Domain Exceptions
│   │   └── MovieAlreadyExistsException.java
│   ├── repository/                    # Repository Interfaces
│   │   └── MovieRepository.java       # Movie Repository Contract
│   └── service/                       # Domain Services
│       └── MovieService.java          # Movie Business Logic
├── persistence/                        # Infrastructure Layer
│   ├── crud/                          # JPA Repositories
│   │   └── CrudMovieEntity.java       # JPA Repository
│   ├── entity/                        # JPA Entities
│   │   └── MovieEntity.java           # Movie Entity
│   ├── mapper/                        # Object Mappers
│   │   ├── MovieMapper.java           # Movie Mapper
│   │   ├── GenreMapper.java           # Genre Mapper
│   │   └── StateMapper.java           # State Mapper
│   └── MovieEntityRepository.java     # Repository Implementation
└── web/                               # Web Layer
    ├── controller/                    # REST Controllers
    │   └── MovieController.java       # Movie REST Controller
    └── exception/                     # Global Exception Handling
        └── RestExceptionHandler.java  # Exception Handler
```

## 🧪 Testing

### Run Tests
```bash
./gradlew test
```

### Test Coverage
```bash
./gradlew jacocoTestReport
```

### Integration Tests
```bash
./gradlew integrationTest
```

## �� API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/movies` | Get all movies |
| GET | `/movies/{id}` | Get movie by ID |
| POST | `/movies` | Create a new movie |
| PUT | `/movies/{id}` | Update a movie |
| DELETE | `/movies/{id}` | Delete a movie |

## �� Data Model

### Movie Entity
```json
{
  "id": 1,
  "title": "The Matrix",
  "duration": 136,
  "genre": "SCI_FI",
  "releaseDate": "1999-03-31",
  "rating": 4.7,
  "state": true
}
```

### Movie Genres
- `ACTION`
- `COMEDY`
- `FANTASY`
- `DRAMA`
- `ANIMATED`
- `HORROR`
- `SCI_FI`

## 🛠️ Development

### Code Style
This project follows Java coding standards and Spring Boot best practices:

- **Constructor Injection** for dependency injection
- **Interface Segregation** for repository contracts
- **Validation** with Bean Validation annotations
- **Exception Handling** with global exception handlers
- **Documentation** with OpenAPI annotations

### Adding New Features
1. Define domain models in `domain/`
2. Create repository interfaces in `domain/repository/`
3. Implement repositories in `persistence/`
4. Add business logic in `domain/service/`
5. Create REST endpoints in `web/controller/`
6. Add tests for new functionality

## �� Deployment

### Docker (Optional)
```dockerfile
FROM openjdk:21-jdk-slim
COPY build/libs/platzi-play-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### Production Configuration
Create `application-prod.properties`:
```properties
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## ��‍�� Author

**Julian Loaiza**
- GitHub: [@julianloaiza](https://github.com/julianloaiza)

## 🙏 Acknowledgments

- Spring Boot team for the amazing framework
- Platzi for the learning platform
- The Java community for best practices and patterns

---

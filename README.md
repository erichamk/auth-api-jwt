# Auth API JWT

A RESTful backend for generating JWT authentication tokens. Built with Java and Spring Boot.

## Tech Stack

- Java 17
- Spring Boot 4
- JJWT lib
- PostgreSQL

## Features

- RESTful API - register and login
- Layered architecture (Controller - Service - Repository)
- DTO pattern
- PostgreSQL database integration

## Getting Started

### Prerequisites

- Java JDK 17+
- Maven 3.5+
- PostgreSQL 9.1+

### Installation

```
git clone https://github.com/erichamk/auth-api-jwt.git
cd auth-api-jwt

mvn spring-boot:run
```

## API Endpoints

- `POST /api/register`: Create a new User.

```
Request:
{
    "username": "",
    "password": "",
}

Response:
{
    "token": ""
}
```

- `POST /api/login`: Validate credentials.

```
Request:
{
    "username": "",
    "password": "",
}

Response:
{
    "token": ""
}
```

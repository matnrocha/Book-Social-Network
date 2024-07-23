## Overview

Book Network is a backend application developed with Spring Boot 3 and Spring Security 6. It allows users to manage their book collections and interact with a community of book enthusiasts. It provides functionalities such as user registration, email validation, book management (creation, updating, sharing, and archiving), book borrowing with availability checks, and book return functionality. The application ensures security using JWT tokens.

## Features

- **User Registration:** Users can register for a new account.
- **Email Validation:** Accounts are activated using secure email validation codes.
- **User Authentication:** Existing users can log in securely.
- **Book Management:** Users can create, update, share, and archive their books.
- **Book Borrowing:** Necessary checks to determine if a book can be borrowed.
- **Book Returning:** Users can return borrowed books.
- **Book Return Approval:** Functionality to approve book returns.

## Technologies Used
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![openapi initiative](https://img.shields.io/badge/openapiinitiative-%23000000.svg?style=for-the-badge&logo=openapiinitiative&logoColor=white)

### Backend

- Spring Boot 3
- Spring Security 6
- JWT Token Authentication
- Spring Data JPA
- JSR-303 and Spring Validation
- OpenAPI and Swagger UI Documentation
- Docker

## Learnings from this project

- Designing a class diagram from business requirements
- Using JWT tokens with Spring Security to secure the application
- Registering users and validating accounts via email
- Utilizing inheritance with Spring Data JPA
- Implementing the service layer and handling application exceptions
- Object validation using JSR-303 and Spring Validation
- Handling custom exceptions
- Implementing pagination and REST API best practices
- Using Spring Profiles for environment-specific configurations
- Documenting APIs using OpenAPI and Swagger UI
- Dockerizing the infrastructure

## Setup Instructions

To set up the backend of the Book Social Network project, follow these steps:

1. Clone the repository:

```bash
   git clone https://github.com/matnrocha/Book-Social-Network.git
```

2. Run the docker-compose file

```bash
  docker-compose up -d
```

3. Navigate to the book-social-network directory:

```bash
  cd book-social-network
```

4. Install dependencies (assuming Maven is installed):

```bash
  mvn clean install
```

4. Run the application

```bash
  java -jar target/book-network-0.0.1.jar
```

5. Access the API documentation using Swagger UI:

  Open a web browser and go to `http://localhost:8088/api/v1/swagger-ui/index.html

6. Access MailDev to receive emails (token receiving):

  Open a web browser and go to `http://localhost:1080/#/

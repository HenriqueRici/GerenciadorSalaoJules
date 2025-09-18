# Salon Management System

This is a Spring Boot application for managing a salon. It provides a REST API for managing employees, services, and service records.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

*   Java 11 or higher
*   Maven
*   MySQL

### Installation

1.  **Clone the repo**
    ```sh
    git clone https://github.com/your_username/your_repository.git
    ```
2.  **Configure the database**

    Open `src/main/resources/application.properties` and update the following properties with your MySQL database credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/salon_db?createDatabaseIfNotExist=true
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```
3.  **Build the project**
    ```sh
    mvn clean install
    ```
4.  **Run the application**
    ```sh
    java -jar target/salon-0.0.1-SNAPSHOT.jar
    ```

## API Endpoints

The application exposes the following REST endpoints:

### Colaboradores

*   `GET /colaboradores`: Get all employees.
*   `GET /colaboradores/{id}`: Get an employee by ID.
*   `POST /colaboradores`: Create a new employee.
*   `PUT /colaboradores/{id}`: Update an employee.
*   `DELETE /colaboradores/{id}`: Delete an employee.

### Serviços

*   `GET /servicos`: Get all services.
*   `GET /servicos/{id}`: Get a service by ID.
*   `POST /servicos`: Create a new service.
*   `PUT /servicos/{id}`: Update a service.
*   `DELETE /servicos/{id}`: Delete a service.

### Lançamentos

*   `GET /lancamentos`: Get all service records.
*   `GET /lancamentos/{id}`: Get a service record by ID.
*   `POST /lancamentos`: Create a new service record.
*   `PUT /lancamentos/{id}`: Update a service record.
*   `DELETE /lancamentos/{id}`: Delete a service record.

# Redis Integration with Spring Boot

This project demonstrates a Spring Boot application integrated with Redis for efficient data storage and retrieval. Redis is used as a database in two configurations:

- **Key-Value Storage**: Data stored in Redis Database 3.
- **Hash Storage**: Data stored in Redis Database 4.

## Technologies Used

- **Spring Boot**
- **Redis**
- **Docker**

---

## Setup Instructions

### 1. Prerequisites

- Docker installed on your system.
- Redis CLI for manual database interaction.

### 2. Running the Application

#### Step 1: Start Redis in Docker

Run the following command to start Redis with a custom configuration:

    ```bash
    docker run --name my-redis --network bridge -p 6378:6379 redis:latest

Step 2: Build and Run the Spring Boot Application Locally

Clone this repository:
    ```bash
    git clone <repository-url>
    cd RedisDemo1
Build the project:

   
    ./mvnw clean package
Run the application:

    java -jar target/RedisDemo1-0.0.1-SNAPSHOT.jar
3. Testing the Application (Local)
Key-Value Storage (DB 3)
Add Data

bash

    POST /api/keyvalue
Body:
{
  "key": "1",
  "value": "Hello Redis"
}
Retrieve Data

bash

    GET /api/keyvalue/1
Hash Storage (DB 4)
Add Employee Data

bash

    POST /employee
Body:
{
  "id": 1,
  "name": "Anudeep"
}
Retrieve All Employee Data

bash

    GET /employee/getallemp
Deploying the Spring Boot Application in Docker
Step 1: Build a Docker Image
Run the following command to build a Docker image for your Spring Boot application:

bash

    docker build -t my-springboot-redis-app .
Step 2: Run the Application in Docker
Start the container with the following command:

bash

    docker run --name my-springboot-redis-app --network bridge -p 8083:8083 my-springboot-redis-app
Testing the Application (Docker)
Key-Value Storage (DB 3)
Add Data

bash

    curl -X POST -d '{"key":"emp:1","value":"Hello Redis"}' -H 'Content-Type: application/json' http://localhost:8083/keyvalue
Retrieve Data

bash

    curl -X GET http://localhost:8083/keyvalue/1
Hash Storage (DB 4)
Add Employee Data

bash

    curl -X POST -d '{"id":1,"name":"Anudeep"}' -H 'Content-Type: application/json' http://localhost:8083/employee
Retrieve All Employee Data

bash

    curl -X GET http://localhost:8083/employee/getallemp
Redis CLI Commands
Connect to Redis via CLI
bash

    docker exec -it my-redis redis-cli
Key-Value Storage (DB 3)
bash

select 3
     keys *
       get "1"
Hash Storage (DB 4)
bash

select 4
    keys *
      hgetall "1"



## **API Endpoints**
list of the API endpoints with their methods, paths, descriptions, and examples.

Example:
| Method | Endpoint       | Description              | Example                  |
|--------|----------------|--------------------------|--------------------------|
| GET    | `/api/keyvalue` | Retrieve value with key  | `curl -X GET http://localhost:8083/keyvalue/1` |
| POST   | `/api/keyvalue`   | Insert a new key value    | `curl -X POST -d '{"key":"emp:1","value":"Hello Redis"}' -H 'Content-Type: application/json' http://localhost:8083/keyvalue` |
| POST    | `/employee`   | Insert new employee  | `curl -X POST -d '{"id":1,"name":"Anudeep"}' -H 'Content-Type: application/json' http://localhost:8083/employee` |
| GET    | `/employee/getallemp`   | Search product by category  | `curl -X GET http://localhost:8083/employee/getallemp` |

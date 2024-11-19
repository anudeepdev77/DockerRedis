Spring Boot Redis Integration
This project demonstrates a Spring Boot application using Redis for:

Key-Value Storage: Data stored in Redis DB 3.
Hash Storage: Data stored in Redis DB 4.

Technologies Used
Spring Boot
Redis
Docker

Setup Instructions
1. Prerequisites
   Docker installed on your system.
   Redis CLI for database interactions.

2. Running the Application
   Step 1: Start Redis in Docker
   Run the following command to start Redis with a custom configuration:

docker run --name my-redis --network bridge -p 6378:6379 redis:latest
--name :u r redis name
-- network :it adds u r redis over a network called bridge-
-p This option maps the container's internal port 6379 (the default Redis port) to port 6378 on the host machine.
redis:latest we are using redis image latest version

Step 2: Build and Run the Spring Boot Application
Clone this repository:

git clone <repository-url>
cd RedisDemo1

Build the project:

./mvnw clean package

Run the application:

java -jar target/RedisDemo1-0.0.1-SNAPSHOT.jar


3. Testing the Application(locally from coding runnig in code editor)
   Use Postman or curl to test the endpoints:

Key-Value Storage (DB 3)
Add Data:
POST /api/keyvalue
Body:
{
"key": "1",
"value": "Hello Redis"
}

Get Data
GET /api/keyvalue/1


Hash Storage (DB 4)
Add Data:
POST /employee
Body:
{
"id":1,
"name":"anudeep"
}

Get Data:
Get /employee/getall

4. Redis CLI Commands
   connect to redis
   docker exec -it redis-container-name redis-cli

switch to db
select "3"(key value db)
keys *(to see all the keys)
get "employee"

select "4"(hashes db)
keys *(to see all the keys)
hgetall "employee"

if everything seems fine now deploye the spring boot applcation(RedisDemo1) in docker and test it from there

steps to deploy springboot application in docker
step 1:clean the application with the following code
code:mvn clean package
step 2:buid a docker image for u r application
code:docker build -t my-springboot-kafkaredis-app .

    The command docker build -t my-springboot-kafkaredis-app . is used to build a Docker image for a Spring Boot application (or any application) using a Dockerfile located in the current directory (denoted by the .). 

Explanation:
docker build:

This is the command used to build a Docker image from a Dockerfile. It tells Docker to create an image based on the instructions specified in the Dockerfile.
-t or --tag:

This option is used to assign a name or tag to the image that will be built. This name helps identify the image later when you want to run, push, or manage it.
In this case, the image will be tagged with my-springboot-kafkaredis-app.
my-springboot-redis-app:

This is the name (and optional tag) for the image. You can give any name to your image (e.g., my-app, springboot-image, etc.).
It is often a good practice to use a descriptive name that includes the purpose or content of the image. In this case, my-springboot-redis-app indicates that the image is for a Spring Boot application that interacts with Redis.
. (dot):

The dot . refers to the current directory. This is where Docker will look for the Dockerfile and any other files that are required for the build.
When you run docker build, it reads the Dockerfile from the current directory and processes the instructions in it to create the image.

What Happens When You Run This Command:
Dockerfile Parsing:

Docker will look for a Dockerfile in the current directory and process the instructions (e.g., FROM, RUN, COPY, etc.).
Build Image:

Based on the Dockerfile instructions, Docker will pull any necessary base images (such as openjdk or maven for Spring Boot), copy files, run commands, and eventually create a new image.
Tagging the Image:

The image will be tagged with my-springboot-redis-app. This allows you to later refer to the image using this tag.


step 3:docker run --name my-springboot-redis-app  --network bridge -p 8083:8083 my-springboot-redis-app

What Happens When You Run This Command:
Docker Container Starts:

The command will start a new container using the my-springboot-redis-app image.
The container will run your Spring Boot application, and it will be accessible on port 8083 on your host machine.
Network Setup:

The container will be attached to the bridge network, allowing it to communicate with other containers on the same network (like Kafka, Redis, etc.).
Port Mapping:

Any traffic directed to localhost:8083 on your local machine will be forwarded to port 8083 inside the container, where your Spring Boot application is running.


Logs: If you want to see the logs of the running container, you can use:
code :docker logs my-springboot-redis-app

Stop:
code:docker stop my-springboot-redis-app
remove:
code docker remove my-springboot-redis-app

Now run the testing commands in docker

API Endpoints for Key-Value and Hash Redis Storage
Key-Value Storage (Redis DB 3)
Method	Endpoint	Description	Example
POST	/keyvalue	Store a key-value pair in Redis DB 3

curl -X POST -d '{"key":"emp:1","value":"anudeep"' -H 'Content-Type: application/json' http://localhost:8083/keyvalue

GET	/keyvalue/{key}	Retrieve a value by key from Redis DB 3	curl -X GET http://localhost:8083/keyvalue/1

Hash Storage (Redis DB 4)
Method	Endpoint	Description	Example
POST	/employee	Store a hash in Redis DB 4
curl -X POST -d '{"id:1","name":"anudeep"}' -H 'Content-Type: application/json' http://localhost:8083/employee


GET	/employee	Retrieve a field from a hash in Redis DB 4	curl -X GET http://localhost:8083/employee/1








  
    






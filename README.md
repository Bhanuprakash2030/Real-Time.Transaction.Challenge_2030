# Getting Started

## Running the application

Make sure you have docker and docker compose installed on your machine. Now follow these steps to start the application:

1. Run docker-compose.yml file
    ```shell
   docker compose -f <location of docker-compose.yml file found in this directory> up
   ```
2. Once the servers are running, navigate to http://localhost:8024/ and configure `axon server` as a standalone server.
3. Run the application by executing:
    ```shell
   mvn clean install
   mvn spring-boot:run
   ```

This should start the application. By default, if you have not changed, the application would be running on the
port `8080`

## Running the tests

The application tests could be executed using the following command:

```shell

mvn clean
mvn test
```

This should generate a `jacoco` test coverage report as well.

## Important links

Application base url: http://localhost:8080

API Doc: http://localhost:8080/v3/api-docs

Sagger UI Url: http://localhost:8080/swagger-ui/index.html
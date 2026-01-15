**Running the Application Locally**

Follow the steps below to set up and run the application in a local development environment.

Prerequisites:
Java (JDK 17)
Maven
PostgreSQL

**Setup Instructions**

1. Clone the Repository

*git clone <repository-url>*

2. Navigate to the Project Directory

*cd sports-store*

3. Configure the Database

Install PostgreSQL if it is not already installed.

Create a PostgreSQL database named *sports-store*.

4. Ensure the database credentials are correctly configured in the application’s configuration files (application.properties or application.yml).

5. Build and Run the Application

*mvn clean spring-boot:run*

-This command will compile the project, resolve dependencies, and start the Spring Boot application.

6. Open a web browser and navigate to:

*http://localhost:8080/swagger-ui.html*

-The Swagger UI provides interactive API documentation and allows you to test available endpoints.

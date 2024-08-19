# Employee Management System

## Overview

The Employee Management System is a Spring Boot application designed to manage employee data efficiently. This application connects to a MySQL database and is deployed on an Apache Tomcat server.

## Prerequisites

- **Java Development Kit (JDK):** 21
- **Maven:** 3.6.0 or higher
- **MySQL:** 5.7 or higher
- **Apache Tomcat:** 9.x or higher

## Database Connection Configuration

The database connection settings for this application are configured in the `application.yml` file located in the `src/main/resources` directory.

### Configuration Details:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/emp_mgmt
    username: root
    password: adminadmin
    driver-class-name: com.mysql.cj.jdbc.Driver

server:
  port: 8081

## User Management
To login as an admin -
Username : admin
Password : admin123

To login as a user -
Username : user
Password : user123

```
## Application Server Deployment

### Deployment Steps:

Prepare the Environment:

Ensure that Apache Tomcat is installed and configured on your system.
Verify that MySQL is installed, running, and accessible on localhost:3306.
Make sure the emp_mgmt database exists and has the necessary schema.
Build the Application:

Navigate to the root directory of your project in the terminal.

Use Maven to clean and package the project into a .war file:

bash
```bash
mvn clean package
```

The generated .war file will be located in the target/ directory, typically named emp-mgmt.war or similar.

### Deploy to Apache Tomcat:

Copy the generated .war file to the webapps/ directory of your Tomcat installation.

bash
```
cp target/emp-mgmt.war /path/to/tomcat/webapps/
```
Start Tomcat using the appropriate script:

On Linux/MacOS:

bash
```
/path/to/tomcat/bin/startup.sh
```

On Windows:

bash
```
C:\path\to\tomcat\bin\startup.bat
```

### Access the Application:

Open a web browser and navigate to http://localhost:8081.
The application should now be running and accessible at this URL.
Monitor the Deployment:

Check the Tomcat logs for any errors during deployment. Logs are typically located in the logs/ directory of your Tomcat installation.
Ensure that the application has started successfully and is functioning as expected.

## SonarQube
Install the server via docker
```
$ docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest
```
To run sonarqube analysis please use the following command in the terminal
```bash
 mvn clean verify sonar:sonar \                                   
  -Dsonar.projectKey=dev-lm-emp-mgmt \                   
  -Dsonar.projectName='dev-lm-emp-mgmt' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_ea09d21fb42e17f72f9eedb23bdec71b80e6483e
```

To view the analysis, go to -
```
http://localhost:9000/dashboard?id=dev-lm-emp-mgmt
```

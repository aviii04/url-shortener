# URL-Shortener
## Desciption:
A Microservice to generate minified URL for a given long URL.

## Technologies & Tools used:
1. Language: Java 15
2. In-memory DB: H2 DB (File based), DB connector - JDBC
3. Framework: Spring Boot 2.5 (includes Apache Tomcat)
4. Build Tool: Maven
5. Container: Docker 20.10

## How to Run:
Service can we run in two ways:
### 1. JAR: 
#### As Spring Boot comes with inhoused Tomcat to run web services, we can get the application up & running by directly executing JAR.

##### Steps to Execute:
1. Make sure **Java 15** is installed & **JAVA_HOME** environment variable is set to JDK home directory.
2. Install **Apache Maven** on machine to build the project & generate `.jar` file. </br> Command to be executed from root directory of project: `mvn clean install`.</br>
  **Time Saving:** Well, this step can be skipped by directly downloading the executable JAR file for the project. Link: [url-shortner.jar](https://drive.google.com/file/d/1jaFXGfNLPdd7p96PTx9BA9Sgbf85dQfE/view?usp=sharing)
3. Navigate to folder containing executable JAR. and run command `java -jar <jarName>`. As in our case jar name is 'url-shortner.jar', so command will be `java -jar url-shortner.jar`
4. We are Done!!!. This should make the serevice available at port 8080. On browser: `http://localhost:8080/urlshortener/`. </br>
   **Note**: This is just a base URL which is not mapped to any end point. Read upcoming section on how to access endpoint & interact with service.


### 2. Docker: Using Docker Image.

More to be added....

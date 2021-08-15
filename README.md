# URL-Shortener
## Desciption:
A RESTful Microservice to generate minified URL for a given long URL. Utilizes file based in-memory DB - H2. It generates fixed length short URL (in our case 7)</br>
**Note:** While generating short URL, by default domain part of submitted URL is subsituted by another fixed domain i.e., `aviii04`</br>
E.g:</br> Submitted Long URL: `https://avi.com/testing/testingAPI/test`</br>
Received short URL: `https://aviii04/jRrwN9T`

## Technologies & Tools used:
1. Language: Java 15
2. In-memory DB: H2 DB (File based), DB connector - JDBC
3. Framework: Spring Boot 2.5 (includes Apache Tomcat)
4. Build Tool: Maven
5. Container: Docker 20.10

## How to Run:
Service can we run in two ways:
### 1. Direclty using JAR: 
#### As Spring Boot comes with inhoused Tomcat to run web services, we can get the application up & running by directly executing JAR.

##### Steps to Execute:
1. Make sure **Java 15** is installed & **JAVA_HOME** environment variable is set to JDK home directory.
2. Install **Apache Maven** on machine to build the project & generate `.jar` file. </br> Command to be executed from root directory of project: `mvn clean install`.</br>
  **Time Saving:** Well, this step can be skipped by directly downloading the executable JAR file for the project. Link: [url-shortner.jar](https://drive.google.com/file/d/1jaFXGfNLPdd7p96PTx9BA9Sgbf85dQfE/view?usp=sharing)
3. Navigate to folder containing executable JAR. and run command `java -jar <jarName>`. As in our case jar name is 'url-shortner.jar', so command will be `java -jar url-shortner.jar`
4. We are Done!!!. This should make the serevice available at port 8080. On browser: `http://localhost:8080/urlshortener/`. </br>
   **Note**: This is just a base URL which is not mapped to any end point. Read upcoming section on how to access endpoint & interact with service.


### 2. Using Container (Docker):
1. Download Docker image for the service straight away and spin it on your local using Docker Engine. Link: To be provided...
2. Or create Docker image on your local using following command (Assuming Docker engine already installed on your system)...
   1. Navigate to Root directory of project and run below command to create image.</br> **Command:** `docker build -f <dockerFileName> -t <ImgNameToBeCreated> <DockerFilePath>` </br>
      e.g: `docker build -f Dockerfile -t url-shortner .`
   3.  Check if generated image is available using command `docker images`.
   4.  Run below command to start containeraized application using command...</br> `docker run -p <dockerPort>:<applicationPort> <imageName>`</br>
      In our case: `docker run -p 8085:8080 url-shortner`.
      
      **Note:** In this case port of base URL will change from 8080 to 8085. i.e: `http://localhost:8085/urlshortener/`</br>
      This is just a base URL which is not mapped to any end point. Read upcoming section on how to access endpoint & interact with service.

### 3. Interacting with Service:
Service provides two REST APIs through which we can interact with the service.</br>
**Request & Response Format:** JSON
#### Base URL:
 1. For JAR: `http://localhost:8080/urlshortener/`
 2. For Docker: `http://localhost:8085/urlshortener/`

#### APIs: 
 **1. GET:** `<baseUrl>/tinyurl?shortUrl=shortURL`</br>
    Description: To get corresponding long/actual URL.</br>
    Request Param: `<baseUrl>/tinyurl?shortUrl=https://aviii04/jRrwN9T` </br>
    Response Body: JSON </br> e.g:
    `{
    "longUrl": "https://avi/testing/finalTesting02",
    "shortUrl": "https://aviii04/jRrwN9T",
    "createDate": "2021-08-15T00:00:00.000+00:00"
}`

 **2. POST:** `<baseUrl>/tinyurl`</br>
 Description: Returns short URL for provided Long URL.</br>
    Request Body: JSON</br> e.g:
 `{
    "longUrl": "https://avi/testing/testingAPI/test"
}`</br>
Response Body: JSON</br> e.g:
`{
    "longUrl": "https://avi/testing/testingAPI/test",
    "shortUrl": "https://aviii04/jRrwN9T",
    "createDate": "2021-08-15T00:00:00.000+00:00"
}`

 
 




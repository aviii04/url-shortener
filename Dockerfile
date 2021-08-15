FROM openjdk:15
ADD target/url-shortner.jar url-shortner.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","url-shortner.jar"]
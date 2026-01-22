FROM eclipse-temurin:17-jdk

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} springbootrestapis.jar

ENTRYPOINT ["java", "-jar", "/springbootrestapis.jar"]

EXPOSE 8090
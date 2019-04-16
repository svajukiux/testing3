FROM maven:3.6-jdk-8 AS build  

WORKDIR /code

COPY src /code/app/src
COPY pom.xml /code/app
RUN mvn -f /code/app/pom.xml clean install

FROM openjdk:8
COPY --from=build /code/app/target/SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar
EXPOSE 5000
CMD ["java","-jar","SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar"]
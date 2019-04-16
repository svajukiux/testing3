FROM maven:3.6-jdk-8 AS build  
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:8
COPY --from=build /home/app/target/SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar
EXPOSE 5000
CMD ["java","-jar","SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar"]
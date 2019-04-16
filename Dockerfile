FROM maven:3.6-jdk-8 AS build  
ADD . /code
WORKDIR /code

COPY src ./
COPY pom.xml ./
RUN mvn -f ./pom.xml clean install

FROM openjdk:8
COPY --from=./ build /target/SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar
EXPOSE 5000
CMD ["java","-jar","SpringWebServiceToDoList-0.0.1-SNAPSHOT.jar"]
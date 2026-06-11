# 1. Usa o Maven para baixar as dependências e compilar o projeto lá na nuvem
FROM maven:3-eclipse-temurin-17 AS build
COPY . .
RUN mvn clean package -DskipTests

# 2. Pega apenas o arquivo .jar gerado e roda a API
FROM eclipse-temurin:17-alpine
COPY --from=build target/*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
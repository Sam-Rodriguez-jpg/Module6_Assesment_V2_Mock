# Etapa 1: Build con JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Caché de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Compilación
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Run con JRE 21 ligero
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copiamos el JAR generado
COPY --from=build /app/target/*.jar app.jar

# IMPORTANTE: El puerto del Mock es el 8081
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
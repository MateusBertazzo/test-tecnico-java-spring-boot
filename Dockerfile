# 1º estágio
FROM eclipse-temurin:17-jdk-jammy as build

WORKDIR /app

COPY . .

RUN ./mvnw clean package -DskipTests

# 2º estágio
FROM eclipse-temurin:17-jdk-jammy

COPY --from=build /app/target/*.jar /app/api.jar

ENTRYPOINT ["java", "-jar", "/app/api.jar"]

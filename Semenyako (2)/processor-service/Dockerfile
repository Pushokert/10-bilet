# Сборка JAR (без go-offline — меньше обрывов загрузки)
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
RUN apt-get update -qq && apt-get install -y -qq maven > /dev/null
COPY pom.xml .
COPY src ./src
RUN mvn -q package -DskipTests

# Запуск
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/processor-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]

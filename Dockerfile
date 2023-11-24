FROM openjdk:21

# Переменные среды для приложения Spring Boot
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://62.84.99.96:5432/postgres?currentSchema=subscription
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres
ENV JWT_SECRET=2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b
ENV JWT_LIFETIME=30m
ENV SPRING_FLYWAY_LOCATIONS=classpath:db/migration
ENV SPRING_FLYWAY_BASELINE_ON_MIGRATE=true

# Копирование JAR-файла в образ
COPY target/vpn-0.0.1-SNAPSHOT.jar app.jar

# Команда для запуска приложения при старте контейнера
ENTRYPOINT ["java", "-jar", "/app.jar"]

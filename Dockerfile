# Etapa 1: Build de la aplicación
# Usa una imagen de OpenJDK 21 para la fase de compilación
FROM openjdk:21-jdk-slim as builder

WORKDIR /app

# Copia los archivos del proyecto al contenedor
COPY . .

# Asegúrate de que el script mvnw sea ejecutable
RUN chmod +x mvnw

# Compila el proyecto y genera el JAR
RUN ./mvnw clean package -DskipTests

# Etapa 2: Usar solo el JAR generado (imagen de ejecución más ligera)
# Usa una imagen de OpenJDK 21 para la fase de ejecución (jre-slim es más pequeño)
FROM openjdk:21-jre-slim

WORKDIR /app

# Copia el JAR generado desde la etapa de construcción
COPY --from=builder /app/target/*.jar app.jar

# Expone el puerto que usa tu aplicación Spring Boot (ej. 8081 para Monitoreo)
EXPOSE 8081

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
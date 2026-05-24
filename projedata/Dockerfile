# Stage 1: Build da aplicação
# Da imagem oficial do Maven com JDK 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
# Seu diretório de trabalho dentro do container será /app assim que o container for iniciado
WORKDIR /app

# Copia o arquivo pom.xml e o diretório src para o container de build
COPY pom.xml .
COPY src ./src

# Builda o JAR da aplicação, ignorando os testes para acelerar o processo
RUN mvn clean package -DskipTests

# Stage 2: Criar a imagem final para rodar a aplicação
# Da imagem oficial do Eclipse Temurin com JDK 21, mas baseada no Alpine para ser mais leve, e tbm apenas com o JRE para rodar a aplicação
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR gerado na etapa de build para a imagem final de runtime
COPY --from=build /app/target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

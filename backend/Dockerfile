# Imagem base apenas com o runtime do Java 17
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o JAR gerado localmente (via mvn package)
COPY target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Define o comando de inicialização com o perfil docker
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=docker"]

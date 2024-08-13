# Use a imagem do OpenJDK como base
FROM openjdk:21-jdk-slim

# Defina o diretório de trabalho no container
WORKDIR /app

# Copie o arquivo JAR para o diretório de trabalho
COPY target/gourmet-connect-0.0.1-SNAPSHOT.jar /app/gourmet-connect.jar

# Exponha a porta em que a aplicação irá rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "gourmet-connect.jar"]

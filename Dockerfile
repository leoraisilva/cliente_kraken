# Etapa 1: Construção do artefato com Maven
FROM maven:3.8-openjdk-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e as dependências para a imagem
COPY pom.xml .

# Baixar as dependências do Maven (sem compilar o código)
RUN mvn dependency:go-offline

# Copiar o restante do código fonte da aplicação para o container
COPY src /app/src

# Compilar o projeto e gerar o JAR
RUN mvn clean package

# Etapa 2: Construção da imagem para execução da aplicação
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado para o container
COPY --from=build /app/target/vendas-0.0.1-SNAPSHOT.jar /app/vendas.jar

# Expor a porta em que o Spring Boot irá rodar (padrão 8080)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/vendas.jar"]

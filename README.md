# Desafio Sicredi

## Objetivo:
Gerenciar essas sessões de votação.

### Tecnologias Utilizadas
Java 17, Spring Boot 3, mySql e Docker.

### Subindo banco de dados e aplicacao

Subindo banco de dados e filas
- Execute o banco de dados: docker-compose up -d db
- Execute aplicação através do IntelliJ e certifique-se de que o Maven esteja instalado no seu sistema navegue até o diretório raiz do seu projeto onde está localizado o arquivo pom.xml e execute o comando: mvn spring-boot:run.
- Com a aplicação rodando acesse: http://localhost:8080/swagger-ui/index.html. Poderá ver todas as funcionalidades.


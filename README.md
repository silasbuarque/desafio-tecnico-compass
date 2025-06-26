# Voting System

Este é um sistema de votação desenvolvido em Java com Spring Boot. O objetivo principal da aplicação é permitir a criação e gerenciamento de pautas de votação, bem como o registro e apuração dos votos em sessões específicas.

## Tecnologias utilizadas

- **Java 21**
- **Spring Boot 3.2.6**
  - Spring Boot Starter Web
  - Spring Boot Starter Data JPA
  - Spring Boot Starter Validation
  - Spring Boot Starter Log4j2
- **Spring Cloud OpenFeign 2023.0.2**
- **Flyway 8.5.13**
  - Flyway Core
  - Flyway MySQL
- **ModelMapper 3.0.0**
- **Lombok 1.18.30**
- **MySQL Connector/J**
- **Mockito Core 5.7.0**
- **Log4j Layout Template JSON**
- **JaCoCo Maven Plugin 0.8.11**
- **Maven Compiler Plugin**
- **Spring Boot Maven Plugin**

## Como executar o projeto

Certifique-se de ter os seguintes itens instalados:

- Java 21+
- Maven 3.8+
- MySQL Server rodando localmente (ou um banco de dados acessível)

### Passos

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/voting-system.git
    cd voting-system
    ```

2. Crie o banco de dados no MySQL:

    ```sql
    CREATE DATABASE compasschallange;
    ```

3. Configure o `application.properties` (ou `application.yml`) com os dados do seu banco de dados:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/compasschallange?createDatabaseIfNotExist=true
    spring.datasource.username=root
    spring.datasource.password=suasenha
    ```

4. Execute a aplicação:

    ```bash
    mvn spring-boot:run
    ```

    A API estará disponível em: [http://localhost:8080](http://localhost:8080)

## Como executar os testes

Para rodar os testes unitários:

```bash
mvn test
```

## Relatório de cobertura de testes (JaCoCo)

Para gerar o relatório de cobertura de testes com o JaCoCo, execute:

```bash
mvn verify
```

O relatório HTML será gerado em:
```bash
target/coverage-report/index.html
```
Abra esse arquivo no navegador para visualizar a cobertura de código.

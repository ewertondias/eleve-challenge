# Exchange Rate :: Service for get conversion rate

---

## Requisitos

O projeto faz uso de:
`Quarkus 3.21.3`
`JDK 21`
`Maven 3.9.6`
`Docker 27.4.0`
`Docker Compose v2.31.0`

- [Quarkus](https://quarkus.io/)
- [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Flyway](https://flywaydb.org/)
- [PostgreSQL](https://www.postgresql.org/)
- [TestContainers](https://testcontainers.com/)
- [WireMock](https://wiremock.org/)
- [Docker](https://docs.docker.com/get-started/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

---

## Instalação

### Clone o repositório

```bash
git clone git@github.com:ewertondias/eleve-challenge.git
```

### Navegue para o diretório do projeto

```bash
cd eleve-challenge
```

### Compilar
```bash
mvn clean install
```

### Executar os testes
```bash
mvn clean verify
```

### Instalação do ambiente via docker
Acessar a pasta onde se encontra o arquivo docker-compose.yml
```
cd src/main/docker/env/
```

Executar o PostgreSQL com credenciais padrão
```
docker compose up -d
```
---

## Banco de dados local da aplicação

`PostgreSQL`
- URL: jdbc:postgresql://localhost:5432/exchange
- usuario: exchange
- senha: exchange

## Executar a aplicação via docker
Na raiz do projeto se encontra o arquivo Dockerfile

Buildar a aplicação
```
docker build -t eleve-api:latest .
```

Para executar a aplicação, é necessário a API-KEY da API de consulta [ExchangeRate-API](https://www.exchangerate-api.com/docs/overview)  
**EXCHANGE_API_KEY=**

Outras variáveis de ambiente podem ser passadas para conexão com outro banco de dados
**QUARKUS_DATASOURCE_JDBC_URL=**  
**QUARKUS_DATASOURCE_USERNAME=**  
**QUARKUS_DATASOURCE_PASSWORD=**

Executar a aplicação apontando para o banco local executado anteriormente.  
**Substituir YOUR_KEY pela sua chave**

```
docker run -e QUARKUS_DATASOURCE_JDBC_URL=jdbc:postgresql://host.docker.internal:5432/exchange \
           -e EXCHANGE_API_KEY=YOUR_KEY \
           --name eleve-api -p 8080:8080 eleve-api:latest
```

## Swagger API REST
http://localhost:8080/q/swagger-ui/
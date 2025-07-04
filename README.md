## Pré-requisitos ##
- Java 17
- Nodejs 22
- Docker
- docker-compose

## Instalação ##

```shell
$ cd api

$ chmod 750 mvnw

$ ./mvnw clean package -DskipTests

$ cd ../frontend

$ npm install

$ npm run build

$ cd ..

$ docker-compose up -d --build
```

## Acesso ##

- Api: 
[http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

- Front-end: 
[http://localhost:8080](http://localhost:8080)

## Observação ##
Para executar a api com kafka na IDE, é necessário adiconar a entrada abaixo no arquivo de hosts:

```shell
127.0.0.1  host.docker.internal
```
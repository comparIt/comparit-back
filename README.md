# COMPARE IT BACK

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

For deploying the application you need :
- [Docker](https://docs.docker.com/install/)
- [Azure CLI](https://docs.microsoft.com/fr-fr/cli/azure/install-azure-cli?view=azure-cli-latest)

## Running the application locally

```shell
mvn spring-boot:run
```

## Deploying the application locally on Docker

To deploy locally the application on Docker, execute these following commands

```shell
docker build -t compare-it-back
docker run -d --name compare-it-back -p 8080:8080 compare-it-back
```

This will create:

* A docker image called "compare-it-back"
* A docker container called "compare-it-back" exposing the 8080 port of the localhost or the docker-machine

## Deploying the application with Docker on Azure Registry

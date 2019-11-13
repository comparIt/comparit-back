# COMPARE IT BACK

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://docs.docker.com/install/)

For deploying the application you need :
- [Docker](https://docs.docker.com/install/)
- [Azure CLI](https://docs.microsoft.com/fr-fr/cli/azure/install-azure-cli?view=azure-cli-latest)

## Recommendation

DO NOT MODIFY THE application.properties, use environnement variable instead !

You can use this example for dev
```
DATABASE_HOST=[URL TO DB];DATABASE_PORT=3306;DATABASE_NAME=compareIt;DATABASE_USERNAME=root;DATABASE_PASSWORD=root;CLIENT_URL=http://localhost:4200
OR with XPORT
DATABASE_HOST=0.0.0.0;DATABASE_PORT=3306;DATABASE_XPORT=33060;DATABASE_NAME=compareIt;DATABASE_USERNAME=root;DATABASE_PASSWORD=rootP@ssw0rd;CLIENT_URL=http://localhost:4200
```
## Building project with units tests

With the implementation of docker into the maven lifecycle, Docker have to be implemented to be able to run unit tests.
Docker will run during the maven build and stop at the end of the cycle. To prevent any conflict on the container port, 
the test container will run on 3307 and 33061

Run build with the following command :

```
mvn -Dhost=<ip where the docker container will run> clean install
```

**/!\ DISCLAIMER : THE DOCKER CONTAINER WILL NOT STOP IF THE BUILD IS FAILURE,
MAKE SURE THE DOCKER HAVE BEEN STOPPED WITH A :**

- `docker ps -a`

**IF THE CONTAINER RUN, DO :**

- `docker rm -f <id container>`

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

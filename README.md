# Currency Calculator

This is a homework assignment written using:

* Java 11
* Spring Boot
* React

## Prerequisites

* [Docker](https://docs.docker.com/get-docker/)
* [Docker Compose](https://docs.docker.com/compose/install/)
* [JDK 11+](https://openjdk.java.net/install/) (e.g. via [`sdk`](https://sdkman.io/))
* [Node.js](https://nodejs.org/en/) (e.g. via [`nvm`](https://github.com/nvm-sh/nvm/))
* [Yarn](https://yarnpkg.com/getting-started/install/) (`npm i -g yarn`)

## Components

### [Back-End](backend/README.md)

* Test: `./gradlew check`
* Build: `./gradlew build`
* Run: `./gradlew bootRun`

### [Front-End](frontend/README.md)

* Test: `yarn test`
* Build: `yarn build`
* Run: `yarn start`

### [DevOps](devops/README.md)

1. Run [`make`](Makefile) to build project
1. Enter your [Fixer API Access Key](https://fixer.io/product) in [`.env`](.env)
1. Run [`make up`](Makefile) to run project
1. Open browser [`http://localhost:8000`](http://localhost:8000)

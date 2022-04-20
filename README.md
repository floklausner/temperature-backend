# temperature Project

## Initializing the project

```
./derbydb-create.sh
```

## Starting the project

```
./derbydb-start.sh
./mvnw clean quarkus:dev
```

- open in browser: http://localhost:8080/api/temperature

## Creating a native linux (!) binary w/ Java 17

```
./mvnw package -Pnative \
-Dquarkus.native.container-build=true \
-Dquarkus.native.builder-image=quay.io/quarkus/ubi-quarkus-native-image:21.3-java17
```

source: [Explore Java 17 language features with Quarkus](https://developers.redhat.com/articles/2021/12/14/explore-java-17-language-features-quarkus)

## Webjars

- Scheint veraltet:
  - ist derzeit 6.6.0
  - in github 7.3.0

- https://medium.com/cofinpro/webjars-6fc02ace5e2a


## D3.js

- https://www.freecodecamp.org/news/d3js-tutorial-data-visualization-for-beginners/
- https://observablehq.com/@d3/lets-make-a-bar-chart/1
- https://observablehq.com/@d3/lets-make-a-bar-chart/2
- https://observablehq.com/@d3/lets-make-a-bar-chart/3

## Data

- https://de.wikipedia.org/wiki/HISTALP
- http://www.zamg.ac.at/histalp/dataset/station/csv.php

```
```





## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/temperature-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# code-with-quarkus

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

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
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- RESTEasy Classic's REST Client JSON-B ([guide](https://quarkus.io/guides/resteasy-client)): JSON-B serialization support for the REST client
- RESTEasy Classic's REST Client ([guide](https://quarkus.io/guides/resteasy-client)): Call REST services
- RESTEasy Classic ([guide](https://quarkus.io/guides/resteasy)): REST endpoint framework implementing Jakarta REST and more

## Provided Code

### RESTEasy Client

Invoke different services through REST with JSON

[Related guide section...](https://quarkus.io/guides/resteasy-client)

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)


## Diagramas

### Diagrama de flujo de la aplicación
```plantuml
@startuml
start

:Usuario envía mensaje;
:Backend recibe mensaje;
:Backend envía prompt a IA (Ollama);
:IA genera respuesta;
:Backend interpreta la respuesta;

if (¿Hay instrucciones tool?) then (sí)
  :Backend ejecuta tool (BD, API, etc.);
  :Tool devuelve resultado;
  :Backend actualiza prompt con resultado tool;
  :Backend envía prompt actualizado a IA;
  :IA genera texto final;
  :Backend envía respuesta final al usuario;
else (no)
  :IA genera texto final;
  :Backend envía respuesta final al usuario;
endif

stop
@enduml
```
### Diagrama de casos de uso 
```plantuml
@startuml
actor Usuario
participant Backend
participant IA
participant Tool

Usuario -> Backend : Envía mensaje
Backend -> IA : Envía prompt

alt Hay instrucciones tool
    Backend -> Tool : Ejecutar tool
    Tool -> Backend : Resultado tool
    Backend -> IA : Actualizar prompt con resultado
else
    IA -> Backend : Generar texto final
end

Backend -> Usuario : Respuesta final
@enduml



```
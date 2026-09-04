# PetStore Rest Assured

Proyecto de automatización de pruebas para la API Petstore usando Java, Rest Assured y TestNG.

## Requisitos

- JDK 17 o superior
- Maven
- IntelliJ IDEA (opcional)

## Estructura

```text
src/
└── test/
    ├── java/com/globant/automation/
    │   ├── config/TestRunner.java
    │   ├── model/
    │   └── test/
    └── resources/
        ├── config.properties
        └── testng.xml
```

## Dependencias principales

- Rest Assured 5.4.0
- TestNG 7.9.0
- Jackson Databind 2.17.0

## Configuración

La URL base se encuentra en:

```text
src/test/resources/config.properties
```

```properties
base.uri=https://petstore.swagger.io/v2
apikey=special-key
```

## Ejecutar las pruebas

Desde la raíz del proyecto:

```powershell
mvn test
```

Para ejecutar la suite de TestNG explícitamente en Windows:

```powershell
mvn test "-DsuiteXmlFile=src\test\resources\testng.xml"
```

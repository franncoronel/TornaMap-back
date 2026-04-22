## Arreglar coverage (y testear)
[![Coverage Status](https://coveralls.io/repos/github/agusnarvaez/tornamap-backend/badge.svg?branch=master)](https://coveralls.io/github/TV3ntu/PDS-2024-backend?branch=master)

# Tornamap - Backend
En este repositorio se encuentra el código fuente del backend de la aplicación Tornamap, desarrollado en Kotlin con Spring Boot.

## Integrantes

## Tecnologías Utilizadas
- **Kotlin**: Lenguaje de programación que corre sobre la máquina virtual de Java.
- **Spring Boot**: Framework de Java para el desarrollo de aplicaciones web.
- **H2 Database**: Base de datos en memoria para desarrollo.
- **Postgres**: Base de datos relacional para producción.

## GitFlow en nuestro Proyecto

Este repositorio sigue el modelo de branching GitFlow para gestionar el desarrollo de software. GitFlow es un flujo de trabajo popular en Git que define una estructura de ramificación para proyectos de software.

### Ramas Disponibles

- **master**: La rama master refleja el estado de producción estable. Los commits en esta rama representan versiones de software listas para ser implementadas en producción.

- **develop**: La rama develop es la rama base para integrar todas las características en progreso. Es donde se fusionan todas las características completadas antes de ser liberadas en producción.

- **release/**: Las ramas de release se utilizan para preparar nuevas versiones para producción. Se crean a partir de la rama develop y se fusionan de vuelta en develop y master una vez finalizadas, después de pasar por pruebas y corrección de errores.

- **feature/**: Las ramas de características se utilizan para desarrollar nuevas funcionalidades. Cada nueva característica debe tener su propia rama de características, que se bifurca de develop y se fusiona de vuelta en develop una vez completada.

- **fix/**: Las ramas de fix se utilizan para corregir problemas. Se crean a partir de la rama develop y se fusionan de vuelta en develop una vez solucionados los problemas.

![image](https://github.com/TV3ntu/PDS-2024-frontend/assets/75498776/b7c98055-ef38-4276-860a-bd74b1728bd9)

## Documentación de endpoints con Swagger
Los mismo pueden accederse mediante:
```
http://localhost:8080/swagger-ui/index.html#/
```

## Comandos importantes para probar build (ícono build) :bulldozer:
1. Sólo la primera vez o cuando cambie la versión de Gradle. Actualiza el wrapper.
```bash
gradle wrapper --gradle-version 8.7
```
2. Construcción + pruebas limpias. Borra artefactos previos, compila y corre tests.
```bash
gradle clean build -x test
```
3. Generar el artefacto ejecutable (JAR Spring Boot). Produce build/libs/tornamap-backend-0.0.1-SNAPSHOT.jar
```bash
gradle bootJar
``` 
4. Smoke-test local sin Docker (opcional)
```bash
java -jar .\build\libs\TORNAMAP-backend-0.0.1-SNAPSHOT.jar
```
5. Reiniciar el stack Docker desde cero 
- Detener y eliminar contenedores y volúmenes de Docker.
```bash
docker-compose down -v
```
- Construye la imagen y levantar los contenedores de Docker.
```bash
docker-compose up --build
```
- Para ver los logs del contenedor del backend en tiempo real.
```bash
docker-compose logs -f backend
``` 

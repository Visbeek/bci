# BCI - UserAPI
_Desarrollado por Matias Visbeek (matiasvisbeek@gmail.com)_



[TOCM]

[TOC]

## Pre-requisitos

 * git
 * gradle

## Trabajar con el proyecto 

_Desde la consola ejecutar el la siguiente línea de código para clonar el proyecto_
```
git clone https://github.com/Visbeek/bci.git
```

### Tests 

_En la raiz del directorio donde se realizó la descarga del proyecto, ejecutar el comando:_
```
./gradlew clean test
```

### Lanzamiento 

_En la raiz del directorio donde se realizó la descarga del proyecto, ejecutar el comando:_
```
./gradlew clean bootrun
```

### Ejecución
Una vez iniciado el proyecto se pueden realizar ejecuciónes desde el swagger generado por código en la url http://localhost:8080/swagger-ui/

## Métodos 

#### Crear Usuario
Crea un usuario con los datos provistos. La respuesta genera un identificador único para el mismo en formato UUID y un token de acceso JWT que es almacenado en base de datos.
 * Método http: Post
 * URL: localhost:8080/user
 * Body:
```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.cl",
    "password": "Hunter22",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
 * Restricciones: 
  * El campo email debe tener 7 carácteres como máximo y el dominio debe ser ".cl".
  * El campo password debe contener una mayúscula al inicio seguido de letras minúscula y con 2 números al final.

#### Buscar Usuario por Id
Retorna un usuario activo para el id provisto.
 * Método http: Get
 * URL: localhost:8080/user/{id}
  * {id}: campo id obtenido en la respuesta del método de creación de usuario en formato UUID.

#### Buscar todos los usuarios
Retorna todos los usuarios activos existentes.
 * Método http: Get
 * URL: localhost:8080/user
 
 #### Editar Usuario
 Permite editar campos de un usuario
 * Método http: Put
 * URL: localhost:8080/user/{id}
   * {id}: campo id obtenido en la respuesta del método de creación de usuario en formato UUID.
 * Body:
```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.cl",
    "password": "Hunter22",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
 * Restricciones: 
  * El campo email debe tener 7 carácteres como máximo y el dominio debe ser ".cl".
  * El campo password debe contener una mayúscula al inicio seguido de letras minúscula y con 2 números al final.
  
  #### Borrado lógico de usuario por Id
Realiza el borrado lógico de un usuario colocando el campo IsActive en false en la base de datos.
 * Método http: Delete
 * URL: localhost:8080/user/{id}
  * {id}: campo id obtenido en la respuesta del método de creación de usuario en formato UUID.

#### Login de Usuario
Permite realizar la autenticación del usuario con email y contraseña, retorna el token de acceso JWT
 * Método http: Post
 * URL: localhost:8080/user/login
 * Body:
```
{
    "email": "juan@rodriguez.cl",
    "password": "Hunter22"
}
```

## Construido con 🛠️

_Utilicé las siguientes herramientas durante el desarrollo_

* [Eclipse 2020-09](https://www.eclipse.org/) - IDE
* [Spring Boot](https://spring.io/projects/spring-boot#overview) 
* [Gradle](https://gradle.org/) - Manejador de dependencias
* [Dozer](http://dozer.sourceforge.net/documentation/about.html) - Mapeador de objetos
* [Git Flow](https://nvie.com/posts/a-successful-git-branching-model/) - Metodología para organizar repositorio git

## Documentación 📖
_En el directorio /diagramas se encuentran los siguientes diagramas:_
 * Diagrama de secuencia "Registro"
 * Diagrama de componentes


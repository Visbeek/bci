# BCI - UserAPI
_Desarrollado por Matias Visbeek (matiasvisbeek@gmail.com)_

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
Una vez iniciado el proyecto se pueden realizar ejecuciónes desde

**Swagger**
En la url http://localhost:8080/swagger-ui/

**Postman Collection**
Desde la colección de Postman provista en el directorio /Adicionales bajo el archivo *BCI - UserAPI.postman_collection*

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

## Información adicional 
_Dentro del directorio /Adicionales puede encontrarse lo siguiente:_
 * Archivo .sql con el DDL de creación de tablas necesarias para el servicio (*user-api-ddl.sql*)
 * Diagramas relacionados al servicio dentro del subdirectorio /Diagramas


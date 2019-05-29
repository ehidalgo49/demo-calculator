# Calculadora: Prueba práctica
### Elaborado por: 
Edwin Hidalgo
Cel: 3175369088

### Caracteristicas

- Version de desarrollo Java 1.8.
- Realizado mediante Spring Boot 1.4.1.
- Base de datos PostgresSQL.
- Integración de JWT para generacion de token.
- Repositorio GitHub.
- Integración de JPA para la persistencia.
- Desarrollo por capas implementando servicios REST.

### Contiene:
- Método de creacion de token de session mediante un usuario (getSession).
- Método de asignacion de numeros a operar (putOperando).
- Método de asignacion de operador y retorno de resultado (putOperador).
- Método utilizado para generar el token.
- Método utilizado para la validacion de un token.


####Configuración:
Una vez descargado el repositorio GIT se debe tener una base de datos **PostgreSQL** configurada en el esquema **publico**, en el archivo **application.properties** se deben ajustar los parametros de conexión.

####Ejecución:
Finalizada la configuración se debe compilar y ejecutar mediante maven
`$ mvn spring-boot:run`

####Payload de servicios:
#####Response metodo get  [http://localhost:9090/calc/usuario](http://localhost:9090/calc/usuario) - (Solicitar sesión)

Content type : JSON
```JSON
{
    "creationDate": null,
    "session": {
        "id": 91,
        "creationDate": 1559107244591,
        "session": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNTU5MTA3MjQzLCJleHAiOjE1NTkxMDc4NDN9.c_bB7djuWbEsYT9R298XsoPx5-p52Z3Tmo-uA0VWUovdSHSbxiR3c3G-Us0J80DdfC-31gNu3Q4M1JHflp9bxA",
        "username": "administrador"
    },
    "header": {
        "code": "200",
        "message": "operación exitosa"
    },
    "body": null
}
```

#####Request metodo get  [http://localhost:9090/calc/putOperando](http://localhost:9090/calc/putOperando) - (Agregar operando)

Content type : JSON
```JSON
{
	"session": {
		"session": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNTU5MTA3MjQzLCJleHAiOjE1NTkxMDc4NDN9.c_bB7djuWbEsYT9R298XsoPx5-p52Z3Tmo-uA0VWUovdSHSbxiR3c3G-Us0J80DdfC-31gNu3Q4M1JHflp9bxA"
	},
	"operando": 2
}
```

#####Request metodo post  [http://localhost:9090/calc/putOperador](http://localhost:9090/calc/putOperador) - (Agregar operador)

Content type : JSON
```JSON
{
	"session": {
		"session": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNTU5MTA3MjQzLCJleHAiOjE1NTkxMDc4NDN9.c_bB7djuWbEsYT9R298XsoPx5-p52Z3Tmo-uA0VWUovdSHSbxiR3c3G-Us0J80DdfC-31gNu3Q4M1JHflp9bxA"
	},
	"operador": "+"
}
```

#####Response metodo post  [http://localhost:9090/calc/putOperador](http://localhost:9090/calc/putOperador) - (Agregar operador)

Content type : JSON
```JSON
{
    "creationDate": null,
    "session": {
        "id": 91,
        "creationDate": 1559107244591,
        "session": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNTU5MTA3MjQzLCJleHAiOjE1NTkxMDc4NDN9.c_bB7djuWbEsYT9R298XsoPx5-p52Z3Tmo-uA0VWUovdSHSbxiR3c3G-Us0J80DdfC-31gNu3Q4M1JHflp9bxA",
        "username": "administrador"
    },
    "header": {
        "code": "200",
        "message": "operación exitosa"
    },
    "body": {
        "id": 94,
        "creationDate": 1559107295500,
        "session": {
            "id": 91,
            "creationDate": 1559107244591,
            "session": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbmlzdHJhZG9yIiwiaWF0IjoxNTU5MTA3MjQzLCJleHAiOjE1NTkxMDc4NDN9.c_bB7djuWbEsYT9R298XsoPx5-p52Z3Tmo-uA0VWUovdSHSbxiR3c3G-Us0J80DdfC-31gNu3Q4M1JHflp9bxA",
            "username": "administrador"
        },
        "operando": 4,
        "operador": "+",
        "resultado": 4
    }
}
```

###Mensajes generados 
| Mensaje      | Código |
| --------- | -----:|
| operación exitosa  | 200  | 
| Exception      |   40001 |
| token invalido  | 40002 |
| no es posible dividir entre cero     |  40002 |
| tipo de operacion no existente      |  40002 |
| debe haber minimo dos operandos para operar      |   40002 |
| User * not save  |  40003 | 
| putOperando have errors  |  40003 | 
| putOperador have errors  | 40003  | 
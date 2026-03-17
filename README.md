# Laboratorio 6 - TDD, Cobertura y Análisis Estático

## Integrantes

* Daniel Ahumada
* Camilo Torres
* Roger Duran
* Camilo León
* Juan Neira


## Descripción General
Este laboratorio se centra en la aplicación de Test-Driven Development (TDD) como práctica fundamental para estructurar proyectos de software. Se desarrolló un sistema básico de gestión de bibliotecas con entidades como Book, User, Loan y Library.

## Objetivos
- Aplicar TDD en el desarrollo de funcionalidades
- Implementar pruebas unitarias con JUnit 5
- Medir la cobertura del código con JaCoCo
- Realizar análisis estático con SonarQube

## Estructura del Proyecto
El proyecto fue construido con Maven y Java 17, siguiendo una organización por paquetes:
- `library.book`
- `library.user`
- `library.loan`
- `library`

Cada paquete cuenta con su respectivo conjunto de pruebas.

## Implementación
Se copiaron las principales funcionalidades en la clase `Library`:
- Agregar libros al sistema
- Registrar usuarios
- Prestar libros con validaciones
- Devolver libros y actualizar su estado

Se aplicó TDD escribiendo primero las pruebas unitarias para los métodos:
- `addBook`
- `loanABook`
- `returnLoan`

## Pruebas
Se utilizó JUnit 5 para crear pruebas unitarias que validan:
- Funcionamiento correcto en casos normales
- Casos límite como libros no disponibles o usuarios inexistentes
- Restricciones como evitar préstamos duplicados

primeramente probamos los tests, para ver como funcionaba; entonces al aplicar el comando

```
mvn test
```

![prueba](./Docs/images/prueba1.png)

Y pues ya despues realizamos el respectivo codigo, para que las pruebas pasaran

![prueba](./Docs/images/prueba2.png)

## Cobertura
Se integró JaCoCo para medir la cobertura del código. Se configuró un mínimo del 80% de cobertura para asegurar la calidad del software.

El reporte de cobertura se genera en:

```
target/site/jacoco/index.html
```

y lo sacamos usando el comando

```
mvn clean verify
```

vimos que con los test que habiamos hecho, nos genero un coverage inferior a 80%, pero agregamos mas pruebas para subir ese coverage, quedamos con el siguiente reporte:

![alt text](./Docs/images/jacoco.png)


## Análisis Estático
Se utilizó SonarQube para analizar la calidad del código:
- Identificación de code smells
- Detección de bugs potenciales
- Validación de cobertura de pruebas

La integración se realizó mediante Maven usando el plugin de Sonar.


## Ejecución
Para compilar el proyecto:


## SonarQube instalacion 

1. Instalacion
Para esto necesitamos tener instalado en nuestro computador docker desktop, teniendo esto ya realizado abrimos power shell en nuestro computador y copiamos estos comandos:
 a. docker pull sonarqube:community
 b. docker volume create sonarqube_data
 c. docker volume create sonarqube_extensions
 d. docker volume create sonarqube_logs
 e. docker run -d `
  --name sonarqube `
  -p 9000:9000 `
  -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true `
  -v sonarqube_data:/opt/sonarqube/data `
  -v sonarqube_extensions:/opt/sonarqube/extensions `
  -v sonarqube_logs:/opt/sonarqube/logs `
  sonarqube:community

2. despues de esto abrimos nuestro docker y nuestro navegador de confianza.
![alt text](./Docs/images/image-3.png)
1. en el navegador escribimos http://localhost:9000
segun el puerto que hayamos utilizado para este caso 
![alt text](./Docs/images/image-2.png)
apenas abramos el nos va a pedir un usuario y una contraseña seran ambas admin, despues las cambiamos y generamos las de nuestra confianza.

1. generamos el token visible para que lo podamos pegar y compilar dentro de nuestro proyecto en el tipo de token lo añadiremos como Global Analysis token 
2. ahora dentro de nuestro Visual vamos ejecutar el siguiente comando:
   1. mvn --% clean verify sonar:sonar -Dsonar.token=AQUI_TU_TOKEN_REAL y esto nos genera un proyecto en SonarQube con el podemos ver ahora si nuestros porcentajes de Duplicacion de cogido y todo. 
  ![alt text](./Docs/images/image-1.png)
   como aparece hay. 

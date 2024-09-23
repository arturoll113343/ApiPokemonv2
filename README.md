## Pokemon Api
Requerimiento
El usuario requiere el desarrollo de un proyecto en Java utilizando Spring, que implemente un servicio REST capaz de consumir la API de PokéAPI. El objetivo principal es que, al recibir un ID de Pokémon, el servicio devuelva información sobre el Pokémon solicitado, así como su cadena de evolución. Además, se debe almacenar en una base de datos los accesos a las APIs y ciertos datos del Pokémon, incluyendo al menos dos niveles de relación.

## 1. Configuración inicial del proyecto
Configurar un proyecto Spring Boot utilizando Java 21 a través de Spring Initializr (https://start.spring.io/) con las siguientes dependencias:

Spring Web: para la creación de endpoints REST.
Spring Data JPA: para interactuar con la base de datos.
H2: como base de datos en memoria para el desarrollo.
Spring Security: para implementar autenticación opcional.
Spring Actuator: para monitorización, con el estado habilitado.
Lenguaje: Java 21
Sistema de construcción: Gradle con Groovy
Empaquetado: JAR

## 2. Arquitectura en capas
Dividir el proyecto en las siguientes capas:

Controller: define los endpoints REST para recibir el ID del Pokémon.
Service: maneja la lógica de negocio, incluyendo las llamadas a la PokéAPI y la persistencia de datos en la base de datos.
Repository: interactúa con la base de datos utilizando JPA.
Entity: modelos para representar las entidades en la base de datos.

## 2.1 Verificación de dependencias
Verificar la correcta instalación de las dependencias en el archivo build.gradle.

## 2.2 Configuración de la base de datos H2
Configurar H2 y JPA en el archivo src/main/resources/application.properties

## 2.3 Autenticación con Spring Security
Habilitar la autenticación mediante una configuración básica en src/main/java/com/pokemon/pokemonApi/security/SecurityConfig.java.

## 2.4 Activación de Actuator
Activar Actuator en el archivo application.properties

## 3. Llamada a la API de PokéAPI
Se utilizará RestTemplate para consumir la API de PokéAPI. Cabe mencionar que RestTemplate ha sido reemplazado en las versiones más recientes de Spring. Alternativamente, se puede usar WebClient, parte del módulo Spring WebFlux, o HttpClient.

## 4. Modelo de la base de datos y persistencia
Para cumplir con los requisitos de almacenamiento de datos del Pokémon y registrar el historial de accesos a la API con múltiples niveles de relación, se crearán dos entidades principales:

Pokemon: almacena detalles del Pokémon, como su ID, nombre, altura y peso.
ApiAccess: registra el historial de accesos a la API, incluyendo el ID del acceso, la fecha y hora del mismo, y el ID del Pokémon solicitado.

## 5. Logs con Log4J
Configurar Log4J para registrar los accesos a los endpoints y otras acciones relevantes, incluyendo auditoría en la base de datos. Asegurarse de añadir las dependencias necesarias en el archivo build.gradle, incluyendo las librerías de Log4J2

## 6.Ejecución del proyecto:

Backend: Abre el proyecto en IntelliJ, localiza la clase principal del backend (PokemonApiApplication), y ejecútala. Esto iniciará el servidor en http://localhost:8080.
Frontend: Abre el proyecto del frontend en IntelliJ, navega a la carpeta donde se encuentra el proyecto, y ejecuta npm start en la terminal. Esto iniciará la aplicación en http://localhost:3000.

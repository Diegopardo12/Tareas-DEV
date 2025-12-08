# Tareas API – Java Spring Boot

API REST para gestión de tareas desarrollada con **Java + Spring Boot**, base de datos **H2 en memoria** y frontend sencillo con **HTML, CSS, jQuery**.

## Tecnologías
- Java 11
- Spring Boot 2.7.12
- Spring Data JPA
- H2 Database
- JUnit 5 + MockMvc
- HTML, CSS, jQuery

## Funcionalidades
- Crear tarea
- Listar tareas
- Marcar tarea como completada
- Eliminar tarea
- Persistencia en H2
- Pruebas unitarias de controlador

## Endpoints
| Método | Endpoint | Descripción |
|------|---------|-------------|
| GET | /tareas | Listar tareas |
| POST | /tareas | Crear tarea |
| PUT | /tareas/{id} | Completar tarea |
| DELETE | /tareas/{id} | Eliminar tarea |

## Ejecutar pruebas
```bash
mvn test

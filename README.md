```markdown
# ZonaFit

Proyecto Java (IDEA) — aplicación con conexión a base de datos.

## Resumen
Breve descripción del propósito del proyecto.

## Requisitos
- Java 11+ (o la versión que uses)
- Maven o Gradle (indica cuál)
- Base de datos (MySQL/Postgres/otro) — indica versión

## Configuración de la base de datos
No incluyas credenciales reales en el repositorio. Usa variables de entorno o un archivo de ejemplo.

Ejemplo: application.properties.example
```
spring.datasource.url=jdbc:mysql://localhost:3306/zonafit
spring.datasource.username=usuario_ejemplo
spring.datasource.password=contrasena_ejemplo
```

Copia ese archivo a application.properties localmente (no subirlo) o configura las variables de entorno.

## Ejecutar
Con Maven:
```
mvn clean package
mvn spring-boot:run
```

Con Gradle:
```
./gradlew build
./gradlew bootRun
```

## Estructura
- src/main/java/zona_fit/conexion
- src/main/java/zona_fit/datos
- src/main/java/zona_fit/dominio
- src/main/java/zona_fit/presentacion

## Notas
- Evita subir /target, archivos .class, .iml, y archivos con credenciales.
- Si necesitas borrar archivos ya subidos (como target o application.properties), sigue las instrucciones abajo.

## Licencia
MIT
```
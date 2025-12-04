# ZonaFit

Proyecto Java (IDEA) — conexión básica a base de datos con JDBC.

Resumen
- Aplicación de ejemplo que muestra una conexión simple a una base de datos mediante JDBC.
- Contiene operaciones CRUD implementadas en la clase ClienteDAO:
  - listarClientes()   -> SELECT
  - buscarClientePorID -> SELECT con parámetro
  - agregarCliente()   -> INSERT
  - modificarCliente() -> UPDATE
  - eliminarCliente()  -> DELETE

Estructura principal
- src/main/java/zona_fit/conexion/Conexion.java
- src/main/java/zona_fit/datos/ClienteDAO.java
- src/main/java/zona_fit/datos/IClienteDAO.java
- src/main/java/zona_fit/dominio/Cliente.java
- src/main/java/zona_fit/presentacion/ZonaFitApp.java

Configuración de la conexión (JDBC)
- Este proyecto no usa Spring; la conexión se gestiona directamente en la clase Conexion.
- Edita zona_fit/conexion/Conexion.java y ajusta la URL JDBC, usuario y contraseña según tu base de datos.
- Ejemplo típico (MySQL) dentro de Conexion.java:
```java
private static final String URL = "jdbc:mysql://localhost:3306/zonafit";
private static final String USER = "tu_usuario";
private static final String PASS = "tu_contrasena";
```
- Asegúrate de tener en el classpath el driver JDBC correspondiente (ej. con Maven: dependencia mysql-connector-java).

Ejecución
- Desde el IDE (IntelliJ): ejecuta la clase con main que prefieras (por ejemplo zona_fit.datos.ClienteDAO o zona_fit.presentacion.ZonaFitApp).
- Desde línea de comandos con Maven (requiere plugin exec o ejecutar el JAR empaquetado). Ejemplo usando el plugin exec:
```
mvn clean compile exec:java -Dexec.mainClass="zona_fit.datos.ClienteDAO"
```
O empaqueta y ejecuta:
```
mvn clean package
java -cp target/<tu-artifact>.jar zona_fit.datos.ClienteDAO
```
(ajusta <tu-artifact>.jar según el nombre que genere tu pom.xml)

Notas de seguridad y buenas prácticas
- No subas credenciales al repositorio. Si vas a mantener datos de configuración, usa variables de entorno o un archivo local que esté en .gitignore.
- Comprueba que .gitignore incluye /target, ficheros de IDE (.iml, .idea), y cualquier archivo con credenciales.
- Si en algún momento subiste credenciales por error, dímelo y te explico cómo eliminarlas del historial.

Ejemplo rápido de uso en ClienteDAO (lo que hace el main)
- El main de ClienteDAO muestra cómo usar los métodos CRUD (listar, buscar, agregar, modificar, eliminar). Puedes descomentar los bloques que necesites para probar cada operación.

Licencia
- MIT 

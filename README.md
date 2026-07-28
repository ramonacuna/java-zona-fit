# Zona Fit (GYM) - Sistema de Control de Clientes

Este proyecto es una aplicación de consola en **Java** diseñada para gestionar los clientes y membresías de un gimnasio llamado **Zona Fit**. Implementa buenas prácticas de desarrollo de software, incluyendo el patrón de diseño **DAO (Data Access Object)**, persistencia en base de datos relacional mediante **JDBC**, y robustez en el flujo de interacción de usuario en la consola.

---

## 🚀 Características

El sistema permite realizar operaciones CRUD completas en una base de datos MySQL:
1. **Listar Clientes:** Recupera y muestra todos los clientes registrados ordenados por ID.
2. **Buscar Cliente:** Busca un cliente específico por su ID y muestra sus datos estructurados en consola.
3. **Agregar Cliente:** Registra un nuevo miembro solicitando nombre, apellido y número de membresía.
4. **Modificar Cliente:** Actualiza los datos de un cliente existente buscando su ID.
5. **Eliminar Cliente:** Elimina del sistema el registro de un cliente según su ID.
6. **Manejo de Errores Robustos:**
   * Validación del ingreso de números (menú, IDs, membresías) para evitar caídas de la aplicación ante ingresos no numéricos (`NumberFormatException`).
   * Manejo adecuado de excepciones en el establecimiento de conexión de base de datos (`RuntimeException`).

---

## 🛠️ Tecnologías y Arquitectura

* **Lenguaje:** Java 25
* **Gestor de Dependencias:** Maven
* **Base de Datos:** MySQL (con driver `mysql-connector-j 8.3.0`)
* **API de Persistencia:** JDBC (Java Database Connectivity)

### Estructura de Capas (Paquetes)
El código se organiza siguiendo el principio de separación de responsabilidades:
* `zona_fit.conexion`: Contiene la clase [Conexion.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/conexion/Conexion.java) encargada de establecer el enlace con MySQL de manera centralizada.
* `zona_fit.dominio`: Contiene la clase de entidad/POJO [Cliente.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/dominio/Cliente.java).
* `zona_fit.datos`: Define la interfaz [IClienteDAO.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/datos/IClienteDAO.java) y su implementación concreta [ClienteDAO.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/datos/ClienteDAO.java), aislando las sentencias SQL (Queries, PreparedStatements) del resto del código.
* `zona_fit.presentacion`: Contiene la clase principal [ZonaFitApp.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/presentacion/ZonaFitApp.java) que gestiona la lectura del teclado, el bucle del menú interactivo y la salida en consola.

---

## 🗄️ Configuración de la Base de Datos

Antes de ejecutar la aplicación, debes crear la base de datos en tu servidor MySQL:

```sql
CREATE DATABASE IF NOT EXISTS zona_fit_db;
USE zona_fit_db;

CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    membresia INT NOT NULL UNIQUE
);
```

### Configurar Credenciales
Asegúrate de ajustar los parámetros de conexión en [Conexion.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/conexion/Conexion.java#L7-L18):
```java
var baseDatos = "zona_fit_db";
var url = "jdbc:mysql://127.0.0.1:3306/" + baseDatos;
var usuario = "root";       // Cambiar por tu usuario
var password = "admin";     // Cambiar por tu contraseña
```

---

## 🏃 Cómo Ejecutar el Proyecto

1. Clona este repositorio o abre la carpeta del proyecto en tu IDE preferido (como **IntelliJ IDEA** o **Eclipse**).
2. Asegúrate de tener configurado el JDK versión 21 o superior.
3. Importa el proyecto como un proyecto Maven para descargar la dependencia de MySQL.
4. Asegúrate de tener el servidor MySQL encendido con la base de datos `zona_fit_db` creada.
5. Ejecuta la clase principal [ZonaFitApp.java](file:///c:/Cursos_Undemy/Java/ZonaFit/src/main/java/zona_fit/presentacion/ZonaFitApp.java) ejecutando el método `main`.
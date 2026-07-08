# 📚 PrestamoLibro — Sistema de Gestión de Préstamos de Libros

Aplicación web desarrollada con **Spring Boot 3.5.4 + Thymeleaf + Spring Security 6 + JPA/Hibernate + MySQL** para la gestión de préstamos de libros en una biblioteca.

---

## 🧱 Estructura del Proyecto

```
prestamoLibro/
├── pom.xml                                              # Dependencias Maven
├── PrestamoLibro.sql                                    # Backup de la base de datos
├── src/main/java/com/example/prestamoLibro/
│   ├── PrestamoLibroApplication.java                    # Clase principal @SpringBootApplication
│   ├── SpringSecurityConfig.java                        # Configuración de seguridad (Spring Security)
│   ├── ConfigMVC.java                                   # Configuración MVC (vistas)
│   ├── Controller/
│   │   ├── LoginController.java                         # Controlador de autenticación
│   │   ├── PrestamoController.java                      # Controlador de préstamos
│   │   └── UsuriosController.java                       # Controlador de usuarios
│   ├── Model/
│   │   ├── Dao/
│   │   │   ├── LibrosDAOIface.java                      # DAO de Libro
│   │   │   ├── PrestamosDAOIface.java                   # DAO de Prestamo
│   │   │   ├── UsuariosDAOIface.java                    # DAO de Usuario
│   │   │   └── UserDAOIface.java                        # DAO de UserAccount
│   │   ├── Entity/
│   │   │   ├── Usuario.java                             # Entidad Usuario
│   │   │   ├── Libro.java                               # Entidad Libro
│   │   │   ├── Prestamo.java                            # Entidad Prestamo
│   │   │   ├── UserAccount.java                         # Entidad de cuenta de usuario (seguridad)
│   │   │   └── Rol.java                                 # Entidad de roles
│   │   └── Service/
│   │       ├── PrestamoServiceIface.java                # Interfaz del servicio
│   │       ├── PrestamoService.java                     # Implementación del servicio
│   │       └── UsuarioDetailService.java                # Servicio de autenticación personalizado
│   └── Utils/paginator/
│       ├── PageRender.java                              # Renderizador de paginación
│       └── ItemPage.java                                # Ítem de página
├── src/main/resources/
│   ├── application.properties                           # Configuración de la aplicación
│   ├── messages.properties                              # Internacionalización (vacío)
│   ├── static/css/styles.css                            # Estilos CSS
│   └── templates/
│       ├── inicio.html                                  # Página de inicio
│       ├── login/login.html                             # Formulario de login
│       ├── error/error_403.html                         # Página 403
│       ├── usuario/
│       │   ├── listado_usuarios.html                    # Listado de usuarios
│       │   └── consulta_usuario.html                    # Detalle de usuario
│       ├── prestamo/prestamo_nuevo.html                 # Formulario de nuevo préstamo
│       └── plantillas/
│           ├── principal.html                           # Layout principal (header/footer)
│           ├── paginador.html                           # Fragmento de paginación
│           ├── plantilla_detalle.html                   # Template para filas de detalle
│           └── autocompleta_libro.html                  # Script JS de autocompletado
```

---

## 🗄️ Motor de Base de Datos

- **Motor:** MySQL / MariaDB
- **Base de datos:** `prestamoLibro`
- **Configuración:** `application.properties`
  ```properties
  spring.datasource.url=jdbc:mysql://localhost/prestamoLibro?serverTimezone=America/Bogota&createDatabaseIfNotExist=true
  spring.datasource.username=root
  spring.datasource.password=
  spring.jpa.hibernate.ddl-auto=update
  ```
- **Esquema:** Gestionado por JPA/Hibernate con `ddl-auto=update` (sincronización automática)
- **Backup:** Archivo `PrestamoLibro.sql` con datos de ejemplo (15 libros, 6 préstamos, 10 usuarios)

---

## 📦 Entidades / Tablas

### Usuario (`usuario`)
| Campo | Tipo | Descripción |
|---|---|---|
| `id` | Long (PK) | Identificador único |
| `identificacion` | String(10) | Número de identificación |
| `nombre_completo` | String(80) | Nombre completo |
| `correo` | String(80) | Correo electrónico |
| `telefono` | String(15) | Teléfono |
| *Relación:* | `@OneToMany` con `Prestamo` | Un usuario puede tener muchos préstamos |

### Libro (`libro`)
| Campo | Tipo | Descripción |
|---|---|---|
| `id` | Long (PK) | Identificador único |
| `isbn` | String(50) | Código ISBN |
| `titulo` | String(100) | Título del libro |
| `autor` | String(100) | Autor |
| `editorial` | String(50) | Editorial |
| `año` | String(10) | Año de publicación |
| `edicion` | String(30) | Edición |
| `estado` | Boolean | `true`=disponible, `false`=prestado |
| `prestamo_id` | Long (FK) | Préstamo al que pertenece (nullable) |
| *Relación:* | `@ManyToOne` con `Prestamo` | Un libro pertenece a un préstamo |

### Prestamo (`prestamo`)
| Campo | Tipo | Descripción |
|---|---|---|
| `id` | Long (PK) | Identificador único |
| `fecha_prestamo` | LocalDate | Fecha del préstamo |
| `fecha_devolucion` | LocalDate | Fecha de devolución |
| `observacion` | String | Observación opcional |
| `usuario_id` | Long (FK) | Usuario que solicita el préstamo |
| *Relaciones:* | `@ManyToOne` con `Usuario`, `@OneToMany` con `Libro` | |

### UserAccount (`usersAccount`) — Seguridad
| Campo | Tipo | Descripción |
|---|---|---|
| `id` | Long (PK) | Identificador único |
| `nombre` | String(20) | Nombre de usuario (único) |
| `clave` | String(128) | Hash BCrypt de la contraseña |
| `activo` | boolean | Si la cuenta está activa |
| *Relación:* | `@OneToMany` con `Rol` | Roles del usuario |

### Rol (`roles`)
| Campo | Tipo | Descripción |
|---|---|---|
| `id` | Long (PK) | Identificador único |
| `nombre` | String(20) | Nombre del rol (`ROLE_ADMIN`, `ROLE_USER`) |
| *Restricción:* | `UniqueConstraint(userAccount_id, nombre)` | Evita roles duplicados por usuario |

### Diagrama de Relaciones

```
Usuario (1) ────── (N) Prestamo (1) ────── (N) Libro

UserAccount (1) ────── (N) Rol
```

---

## 🔐 Seguridad

- **Spring Security 6** con autenticación basada en formularios
- **BCryptPasswordEncoder** para hash de contraseñas
- **UsuarioDetailService** personalizado que implementa `UserDetailsService`
- **Roles:** `ROLE_ADMIN` y `ROLE_USER` almacenados en la tabla `roles`
- **Política de acceso:**
  | Ruta | Acceso |
  |---|---|
  | `/`, `/css/**`, `/js/**`, `/img/**` | Público |
  | `/prestamolibro/usuarioslistar` | Público |
  | `/prestamolibro/usuarioconsultar/**` | `ADMIN` o `USER` |
  | `/prestamolibro/prestamonuevo/**` | Solo `ADMIN` |
  | `/prestamolibro/guardarprestamo` | Autenticado |
  | Cualquier otra ruta | Autenticado |
- Página personalizada de error 403: `/error_403`

---

## 🌐 Endpoints REST y Controladores

### LoginController
| Método | Ruta | Función |
|---|---|---|
| `GET` | `/login` | Muestra formulario de login |
| `GET` | `/login?error` | Login con error |
| `GET` | `/login?logout` | Confirmación de cierre de sesión |

### UsuriosController (`@RequestMapping("/prestamolibro")`)
| Método | Ruta | Función |
|---|---|---|
| `GET` | `/prestamolibro/usuarioslistar?pag={n}` | Lista paginada de usuarios (5 por página) |
| `GET` | `/prestamolibro/usuarioconsultar/{id}` | Detalle de usuario con sus préstamos |

### PrestamoController (`@RequestMapping("/prestamolibro")`)
| Método | Ruta | Función |
|---|---|---|
| `GET` | `/prestamolibro/prestamonuevo/{id}` | Formulario de nuevo préstamo para el usuario |
| `GET` | `/prestamolibro/cargarlibros/{term}` | **JSON** — Autocompletado de libros disponibles por título |
| `POST` | `/prestamolibro/guardarprestamo` | Guarda el préstamo con sus libros asociados |

---

## ⚙️ Métodos y Tecnologías Utilizadas

| Tecnología | Propósito |
|---|---|
| **Spring Boot 3.5.4** | Framework principal |
| **Spring MVC** | Patrón Modelo-Vista-Controlador |
| **Spring Data JPA / Hibernate** | ORM y persistencia |
| **Spring Security 6** | Autenticación y autorización |
| **Thymeleaf** | Motor de plantillas (vistas HTML) |
| **Thymeleaf Extras Springsecurity6** | Integración seguridad en vistas |
| **Jakarta Validation** | Validación de formularios (`@NotEmpty`, `@Email`, `@NotNull`) |
| **MySQL Connector J** | Conexión a base de datos MySQL |
| **Bootstrap 5.3.6** | Framework CSS (vistas responsivas) |
| **Axios (CDN)** | Peticiones AJAX para autocompletado |
| **BCrypt** | Hash de contraseñas |
| **Maven** | Gestión de dependencias y build |

### Patrones de Diseño
- **MVC** — Separación en capas (Modelo, Vista, Controlador)
- **DAO/Repository** — Acceso a datos mediante interfaces que extienden `JpaRepository`
- **Service Layer** — Lógica de negocio encapsulada en servicios
- **Session Attributes** — Atributos de sesión para manejar el préstamo en creación
- **Fragmentos Thymeleaf** — Reutilización de vistas (layout, paginador)

---

## 📥 Instalación y Arranque

### Prerrequisitos
- **Java 17** o superior
- **Maven** (incluye wrapper `mvnw`)
- **MySQL** o **MariaDB** corriendo en `localhost:3306`

### Pasos

1. **Clonar o situarse en el proyecto:**
   ```bash
   cd prestamoLibro
   ```

2. **(Opcional) Importar la base de datos:**
   ```bash
   mysql -u root < PrestamoLibro.sql
   ```
   > Si no importa el SQL, Hibernate creará las tablas automáticamente gracias a `ddl-auto=update`, pero sin datos iniciales.

3. **Configurar la conexión a la base de datos** (si es necesario):
   Editar `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=tu_password
   ```

4. **Compilar y ejecutar la aplicación:**
   ```bash
   # Con el wrapper de Maven
   ./mvnw spring-boot:run

   # O empaquetar y ejecutar
   ./mvnw package -DskipTests
   java -jar target/comercial-0.0.1-SNAPSHOT.jar
   ```

5. **Acceder a la aplicación:**
   - URL: [http://localhost:8080](http://localhost:8080)

### Credenciales de Prueba

La aplicación imprime en consola el hash BCrypt de `Abc123` al iniciar. Debe registrar manualmente usuarios en la tabla `usersAccount` con contraseñas codificadas con BCrypt. Ejemplo para generar el hash:

```java
System.out.println(new BCryptPasswordEncoder().encode("Abc123"));
```

### Notas
- El proyecto usa `createDatabaseIfNotExist=true`, por lo que creará la base de datos automáticamente si no existe
- `ddl-auto=update` sincroniza el esquema de tablas con las entidades JPA automáticamente
- Puerto por defecto: `8080`
- Zona horaria: `America/Bogota`

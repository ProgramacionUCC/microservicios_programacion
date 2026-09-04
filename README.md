# Cogollo

API y pantalla de pruebas para HU-03, HU-04 y HU-05.

## Requisitos

- Windows 10/11.
- JDK 17 o superior. El proyecto compila actualmente con Java 17.
- Internet la primera vez para descargar dependencias Maven.
- Puerto `8080` disponible.

Tecnologias: Spring Boot 4.0.0, Spring Security, Spring Data JPA, H2 y JJWT.

## Instalar Java 17

En PowerShell:

```powershell
winget install --id EclipseAdoptium.Temurin.17.JDK --exact --accept-source-agreements --accept-package-agreements
```

Cierra y abre la terminal y comprueba:

```powershell
java -version
```

Si no se reconoce Java en la terminal actual:

```powershell
$java = Get-ChildItem 'C:\Program Files\Eclipse Adoptium' -Directory | Select-Object -First 1
$env:JAVA_HOME = $java.FullName
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
java -version
```

## Maven local

El repositorio incluye `mvnw.cmd`, que usa Maven 3.9.10 dentro de `.tools`. Si no existe `.tools\apache-maven-3.9.10`, descarga este ZIP oficial:

<https://archive.apache.org/dist/maven/maven-3/3.9.10/binaries/apache-maven-3.9.10-bin.zip>

Extraelo para obtener:

```text
.tools\apache-maven-3.9.10\bin\mvn.cmd
```

`.tools` esta excluido de Git porque contiene herramientas locales.

## Compilar y ejecutar

Desde la carpeta donde esta `pom.xml`:

```powershell
cd C:\Users\owenm\Downloads\cogollo
.\mvnw.cmd clean test
.\mvnw.cmd spring-boot:run
```

La compilacion correcta termina en `BUILD SUCCESS`. Al ejecutar, la terminal queda ocupada porque el servidor permanece activo. Debe aparecer `Tomcat started on port 8080` y `Started CogolloApplication`.

Abre <http://localhost:8080> para ver la pantalla de pruebas. Si ves una version vieja, usa `Ctrl + F5`. Deten el servidor con `Ctrl + C`.

Si aparece `Port 8080 was already in use`, no inicies otra instancia. Usa `Ctrl + C` en la terminal anterior o:

```powershell
netstat -ano | Select-String ':8080\s+.*LISTENING'
Stop-Process -Id <PID> -Force
```

## Usuarios de prueba

Se crean al iniciar la base H2:

| Rol | Correo | Clave |
|---|---|---|
| ADMINISTRADOR | `admin@cogollo.com` | `Admin123!` |
| PROPIETARIO | `propietario@cogollo.com` | `Owner123!` |

Tambien se crea un restaurante demo asociado al propietario, normalmente con ID `1`. H2 usa `jdbc:h2:mem:cogollo`, por lo que los datos se pierden al reiniciar.

## Prueba paso a paso

### 1. Login, HU-05

```http
POST /api/auth/login
Content-Type: application/json
```

```json
{"correo":"propietario@cogollo.com","clave":"Owner123!"}
```

Guarda el `token` de la respuesta y envialo en las siguientes solicitudes:

```http
Authorization: Bearer <token>
```

### 2. Crear perfil propietario

Con token de administrador:

```http
POST /api/propietarios
```

```json
{"correo":"nuevo.propietario@cogollo.com","clave":"NuevaClave123!"}
```

Respuesta esperada: `201 Created` con ID, correo y rol.

### 3. Crear restaurante

Con token de administrador:

```http
POST /api/restaurantes
```

```json
{"nombre":"Mi restaurante","propietarioId":2}
```

### 4. Crear plato, HU-03

Con token del propietario y su restaurante:

```http
POST /api/platos
```

```json
{"nombre":"Hamburguesa","precio":18000,"descripcion":"Hamburguesa con queso","restauranteId":1}
```

El restaurante es obligatorio y debe pertenecer al propietario autenticado.

### 5. Modificar plato, HU-04

```http
PUT /api/platos/1
```

```json
{"precio":22000,"descripcion":"Hamburguesa con queso y tocineta"}
```

Solo cambian `precio` y `descripcion`; el nombre y el restaurante no se modifican.

### 6. Crear empleado

Con token del propietario:

```http
POST /api/empleados
```

```json
{"correo":"empleado@cogollo.com","clave":"Empleado123!"}
```

Respuesta esperada: `201 Created`. Un administrador o empleado recibe `403 Forbidden`.

## Autorizacion

| Metodo | Ruta | Rol |
|---|---|---|
| POST | `/api/propietarios` | ADMINISTRADOR |
| POST | `/api/empleados` | PROPIETARIO |
| POST | `/api/restaurantes` | ADMINISTRADOR |
| POST | `/api/platos` | PROPIETARIO |
| PUT | `/api/platos/{id}` | PROPIETARIO del restaurante |
| POST | `/api/pedidos` | EMPLEADO |

Las claves se almacenan con BCrypt. Los JWT expiran en una hora.

## Errores frecuentes

- `JAVA_HOME environment variable is not defined correctly`: abre una terminal nueva o configura `$env:JAVA_HOME` como se indica arriba.
- `release version 25 not supported`: verifica que `pom.xml` tenga `<java.version>17</java.version>` y ejecuta `.\mvnw.cmd clean test`.
- `UnsupportedClassVersionError`: elimina bytecode viejo con `.\mvnw.cmd clean spring-boot:run` y no ejecutes dos compiladores a la vez.
- `403 Forbidden`: revisa el rol del token, el encabezado Bearer y que el restaurante pertenezca al propietario.

## Estado actual

`clean test` termina en `BUILD SUCCESS`. Todavia no hay pruebas automatizadas; el flujo funcional puede probarse desde la pantalla web o con PowerShell. Consulta [OPTIMIZACIONES.md](OPTIMIZACIONES.md) para preparar una entrega de produccion y [GIT_FLOW.md](GIT_FLOW.md) para el flujo de ramas.

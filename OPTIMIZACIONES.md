# Optimizaciones pendientes

Este documento separa lo que ya funciona en la demo de lo necesario para una entrega mantenible y segura.

## Prioridad alta

### 1. Cambiar secretos y credenciales demo

- No usar la clave JWT incluida en `application.yml` en ningun ambiente real.
- Mover `app.jwt.secret` a una variable de entorno o un gestor de secretos.
- Eliminar o proteger las credenciales iniciales antes de publicar.
- No mostrar claves de prueba en la interfaz de produccion.

### 2. Usar una base de datos persistente

H2 en memoria borra perfiles, restaurantes y platos al reiniciar. Configurar PostgreSQL o MySQL con migraciones Flyway/Liquibase, indices unicos para correo, restricciones de integridad y variables de entorno para URL, usuario y clave.

### 3. Agregar pruebas automatizadas

Crear pruebas con MockMvc o RestAssured para login correcto/incorrecto, BCrypt, JWT ausente/expirado/invalido, todas las combinaciones de rol, empleado creado por propietario, plato sin restaurante, plato de otro propietario y modificacion limitada a precio y descripcion.

### 4. Manejar errores de forma uniforme

Agregar un `@RestControllerAdvice` con un formato comun que incluya `timestamp`, `status`, `code` y `message`. Evitar respuestas vacias en `401` y `403` y no revelar detalles internos.

## Prioridad media

### 5. Separar responsabilidades

- Mover la logica de platos a un `PlatoService` transaccional.
- Mover creacion de propietarios y empleados a servicios de usuarios.
- Usar DTOs de respuesta para no serializar entidades JPA directamente.
- Validar que `propietarioId` tenga rol `PROPIETARIO` al crear restaurantes.
- Reemplazar `orElseThrow()` sin mensaje por excepciones de dominio.

### 6. Mejorar el modelo

- Usar `BigDecimal` para precios.
- Agregar estados y auditoria de pedidos.
- Configurar cuidadosamente relaciones JPA, `fetch` y serializacion.
- Agregar fechas de creacion y actualizacion.

### 7. Configurar produccion

- Desactivar H2 Console fuera de desarrollo.
- Desactivar `open-in-view` y ajustar consultas lazy.
- Configurar CORS solo para origenes autorizados.
- Usar HTTPS, logs estructurados, health checks y metricas.
- Añadir rate limiting y auditoria de login si cambia el requisito de intentos ilimitados.

## Prioridad baja

### 8. Mejorar interfaz

- Separar pantalla demo de la aplicacion cliente real.
- Mostrar rol y usuario autenticado.
- Cargar restaurantes y platos desde endpoints de consulta.
- Mostrar validacion junto a cada campo.
- Añadir boton para limpiar token y estado local.

### 9. Calidad y entrega

- Agregar Maven Wrapper oficial completo.
- Configurar Checkstyle, SpotBugs y JaCoCo.
- Crear pipeline CI con compilacion, pruebas y analisis de seguridad.
- Generar OpenAPI/Swagger.
- Agregar Dockerfile y perfiles `dev`, `test` y `prod`.

## Criterio de terminado

1. Todas las pruebas de roles pasan automaticamente.
2. Ningun secreto esta en el repositorio.
3. La base sobrevive reinicios.
4. Los errores tienen respuestas HTTP consistentes.
5. La documentacion de ejecucion y Git Flow esta versionada.

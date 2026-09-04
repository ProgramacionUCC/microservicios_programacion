# Estrategia Git Flow

## Ramas principales

- `main`: version estable y entregable.
- `develop`: integracion del trabajo del sprint.

## Ramas de apoyo

- `feature/HU-01-crear-propietario`
- `feature/HU-02-crear-restaurante`
- `feature/HU-03-crear-plato`
- `feature/HU-05-autenticacion`
- `feature/HU-11-realizar-pedido`
- `release/sprint-1`, `release/sprint-2`, `release/sprint-3`
- `hotfix/correccion-login`

## Flujo

1. Cada historia nace desde `develop` en su rama `feature`.
2. Los cambios se integran mediante Pull Request hacia `develop`.
3. Al cerrar el sprint se crea `release/sprint-x`.
4. Tras validar pruebas y documentación, la release se fusiona en `main` y `develop`.
5. Los errores criticos de produccion se corrigen desde `main` en una rama `hotfix` y se integran en ambas ramas principales.

Cada HU debe tener commits frecuentes y la documentacion debe versionarse junto con el codigo.

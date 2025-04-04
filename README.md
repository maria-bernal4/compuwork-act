# Sistema de Gestión de RRHH - CompuWork

Este sistema de gestión de recursos humanos está diseñado para CompuWork, permitiendo la administración eficiente de empleados y departamentos, así como la generación de reportes de desempeño.

## Características

- Gestión de empleados permanentes y temporales
- Administración de departamentos
- Cálculo automático de salarios
- Generación de reportes de desempeño
- Interfaz gráfica intuitiva

## Requisitos

- Java 17 o superior
- Maven 3.6 o superior
- JavaFX 17

## Instalación

1. Clonar el repositorio
2. Navegar al directorio del proyecto
3. Ejecutar `mvn clean install`

## Ejecución

Para ejecutar la aplicación:

```bash
mvn javafx:run
```

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── compuwork/
│   │           ├── modelo/
│   │           │   ├── Empleado.java
│   │           │   ├── EmpleadoPermanente.java
│   │           │   ├── EmpleadoTemporal.java
│   │           │   └── Departamento.java
│   │           ├── controlador/
│   │           │   └── MainController.java
│   │           └── Main.java
│   └── resources/
│       └── vista/
│           └── main.fxml
└── test/
    └── java/
        └── com/
            └── compuwork/
                └── modelo/
                    ├── EmpleadoTest.java
                    └── DepartamentoTest.java
```

## Funcionalidades

### Gestión de Empleados
- Crear nuevos empleados (permanentes o temporales)
- Asignar empleados a departamentos
- Calcular salarios con bonificaciones

### Gestión de Departamentos
- Crear y administrar departamentos
- Ver lista de empleados por departamento
- Generar reportes departamentales

### Reportes
- Reportes individuales de desempeño
- Reportes departamentales con totales de salarios

## Pruebas

El proyecto incluye pruebas unitarias para las clases principales. Para ejecutar las pruebas:

```bash
mvn test
```

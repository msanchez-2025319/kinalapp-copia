# KinalApp

Programa que gestiona el registro de usuarios, clientes, productos y detalles de ventas, cada uno con su respectivo CRUD funcional.

## Tecnologías Utilizadas

* **Java 21**
* **Spring Boot 4.0.2**
* **Maven** (Gestor de dependencias)
* **MySQL** (Sistema Gestor de Base de Datos)

## Requisitos Previos

Antes de ejecutar el proyecto es importante contar con:

* JDK 17 o superior instalado
* Maven configurado
* Instancia de MySQL activa para revisar la base de datos
* Editor de código (IntelliJ IDEA o Visual Studio Code)

## Instalación y Ejecución

Pasos para instalar y ejecutar el programa:

* Clonar el repositorio
* Ejecutar la aplicación desde un editor de código (preferiblemente IntelliJ IDEA)

## Estructura del Proyecto

El proyecto sigue la arquitectura en capas de Spring Boot:

- **Entidades**: contiene las clases `Cliente`, `DetalleVenta`, `Producto`, `Usuario` y `Venta`.  
- **Repositorios**: una interfaz por cada entidad que extiende de `JpaRepository`.  
- **Servicios**: interfaz y su implementación por cada entidad con las operaciones CRUD.  
- **Controladores**: exponen los endpoints REST por cada entidad.  


## Buenas Prácticas Aplicadas

* Uso de **Long** como clase envolvente en lugar de `int`
* Uso de **BigDecimal** con `precision = 10` y `scale = 2` para valores decimales el precision y scale se colocan en columna
* **FetchType.LAZY** en todas las llaves foráneas
* **@Transactional(readOnly = true)** en métodos de solo consulta
* Validaciones en la capa de servicio


## Flujo de Trabajo con Git

1. Crear una rama: `git checkout -b docs/update-readme`
2. Editar el archivo `README.md`
3. Guardar y subir los cambios con `git add`, `git commit` y `git push`
4. Abrir el Pull Request en GitHub y fusionar a `main`
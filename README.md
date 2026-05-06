# KinalApp

Programa que gestiona el registro de usuarios, clientes, productos y detalles de ventas, cada uno con su respectivo CRUD funcional.

Este proyecto lo desarrollé como parte de mi aprendizaje en Spring Boot, poniendo en práctica la conexión con base de datos, manejo de entidades y la estructura en capas. Durante el proceso fui mejorando varios aspectos para que el sistema funcionara de manera más ordenada y clara.

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
* Uso de **BigDecimal** con `precision = 10` y `scale = 2` para valores decimales
* **FetchType.LAZY** en relaciones
* **@Transactional(readOnly = true)** en métodos de solo consulta
* Validaciones en la capa de servicio

## Nota Personal

Durante el desarrollo de este proyecto, hubieron partes que me tomaron más tiempo entender y hacer funcionar correctamente, pero eso me ayudó a comprender mejor cómo trabaja Spring Boot con bases de datos y la organización del código. Actualmente, las llaves foráneas aún se encuentran en proceso de mejora para optimizar su funcionamiento dentro del sistema. En general, este proyecto me sirvió bastante para reforzar lo aprendido en clase y practicar la lógica del CRUD completo.

## Flujo de Trabajo con Git

1. Crear una rama: `git checkout -b docs/update-readme`
2. Editar el archivo `README.md`
3. Guardar y subir los cambios con `git add`, `git commit` y `git push`
4. Abrir el Pull Request en GitHub y fusionar a `main`
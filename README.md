# Ecommerce Shop - Spring Boot Backend

Este proyecto es una implementación de un backend para un ecommerce con un carrito de compras, utilizando **Spring Boot** y **Spring Security**. A lo largo del desarrollo, se cubren conceptos fundamentales de creación de entidades, operaciones CRUD, servicios y controladores, así como la integración de JWT para la autenticación y seguridad de los usuarios.

> **Nota:** Este proyecto aún está en desarrollo. Algunas funcionalidades pueden estar incompletas o sujetas a cambios.

## Contenido del Proyecto

1. **Generación del Proyecto**
    - Configuración inicial del proyecto utilizando Spring Boot.

2. **Entidades y Mapeo**
    - Creación y mapeo de las entidades principales: Productos, Categorías, Carrito, Usuarios y Pedidos.

3. **Operaciones CRUD**
    - Implementación de CRUD para productos, categorías y carrito.

4. **Servicios y Controladores**
    - Implementación de servicios y controladores para productos, categorías, imágenes, carrito y usuarios.

5. **Autenticación y Seguridad**
    - Integración de Spring Security y JWT para proteger las APIs del proyecto.

6. **Pruebas**
    - Pruebas de los endpoints de productos, carrito y usuarios.
    - Resolución de errores y pruebas de seguridad.

## Funcionalidades Principales

- **Productos:**
    - CRUD de productos.
    - Gestión de imágenes de productos.

- **Categorías:**
    - CRUD de categorías.

- **Carrito de Compras:**
    - Añadir, eliminar y actualizar ítems en el carrito.
    - Gestión del carrito a través de un servicio y controlador.

- **Usuarios:**
    - Gestión de usuarios con roles asignados.
    - Autenticación con JWT.

- **Pedidos:**
    - Creación y gestión de pedidos de usuarios.

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.3**
- **Spring Security**
- **JWT (JSON Web Tokens)**
- **Hibernate/JPA** para la persistencia de datos.
- **MySQL** como base de datos.
- **Maven** para la gestión de dependencias.

## Dependencias del Proyecto

El proyecto utiliza las siguientes dependencias:

- **Spring Boot Data JPA**: Para la persistencia de datos.
- **Spring Boot Validation**: Para la validación de datos.
- **Spring Boot Web**: Para la creación de APIs REST.
- **Spring Boot DevTools**: Herramientas de desarrollo para recarga en caliente (hot reload).
- **MySQL Connector**: Conector para la base de datos MySQL.
- **Lombok**: Para reducir el boilerplate de código.

## Estructura del Proyecto

- `src/main/java`: Contiene la lógica de negocio, controladores, servicios y entidades del proyecto.
- `src/test/java`: Contiene las pruebas unitarias y de integración del proyecto.

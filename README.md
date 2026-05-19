# Red Panda 🐼🍜

¡Bienvenido a **Red Panda**!Este proyecto es una aplicación web *Full-Stack* interactiva desarrollada como Proyecto de Fin de Ciclo (TFC) para el Ciclo Formativo de Grado Superior en Desarrollo de Aplicaciones Web (Curso 2025-2026).

El propósito principal del sistema es la digitalización integral de un restaurante de gastronomía asiática tradicional[cite: 11, 344]. [cite_start]Permite a los usuarios explorar la carta de forma dinámica, gestionar un carrito en tiempo real, personalizar ingredientes y finalizar sus pedidos mediante una pasarela de pago segura sin intermediarios.

---

## 🧑‍💻 Autores
* **Iker Martin Buruaga** – *Desarrollo Full-Stack (Frontend, Backend & Base de Datos)*
* **Ibrahim Kidi Collado** – *Desarrollo Full-Stack (Frontend, Backend & Base de Datos)*

---

## 🚀 Características Principales

* **Catálogo Interactivo y Paginado:** Exploración de productos divididos por categorías gastronómicas (Sushi, Ramen, Entrantes, Carnes, Pescados, Bebidas y Postres) usando paginación y optimización de imágenes (WebP) para un alto rendimiento.
* **Gestión de Carrito Avanzada:** Añadir, modificar raciones y eliminar platos en tiempo real con cálculo automático del precio final mediante gestión de estado global.
* **Personalización de Platos:** Visualización y filtro de los ingredientes de cada plato con la posibilidad de quitarlos del pedido antes de comprar.
* **Autenticación Segura:** Sistema de registro e inicio de sesión de usuarios con contraseñas encriptadas mediante algoritmos de hashing en la base de datos.
* **Pasarela de Pago Integrada:** Simulación de pagos de forma segura utilizando la API de pruebas de PayPal.
* **Panel de Administración (CRUD):** Interfaz centralizada para que el restaurante pueda crear, editar, eliminar productos o monitorizar las ventas en tiempo real.
* **Historial de Pedidos:** Área personal donde el cliente puede consultar sus transacciones anteriores y detalles del pedido.
* **Internacionalización (i18n):** Soporte multiidioma que incluye traducciones al Catalán, Francés y Japonés.

---

## 🛠️ Tecnologías y Arquitectura

El sistema se diseñó bajo una **Arquitectura en 3 Capas** para garantizar la modularidad y la total independencia entre la lógica de negocio y la interfaz de usuario.

### 📐 Arquitectura del Sistema
1. **Capa de Cliente (Frontend):** Construida con **Vue 3** (Composition API) y **TypeScript**, utilizando **Pinia** para la gestión reactiva del estado y **SCSS** para un diseño adaptativo (*Responsive*) optimizado para móviles, tablets y ordenadores. Comunica con el servidor mediante peticiones asíncronas JSON vía **Fetch API**.
2. **Capa de Servidor (Backend):** Implementada con **Spring Boot 3** bajo el patrón MVC, exponiendo una **API REST** robusta y estructurada mediante endpoints independientes.
3. **Capa de Datos (Persistencia):** Base de datos relacional administrada en **MySQL**, mapeada mediante **Hibernate / Spring Data JPA** asegurando la integridad referencial y las relaciones entidad-relación (1:N y N:M).

---

## 📦 Requisitos Previos para la Ejecución

Asegúrate de tener instalado en tu entorno local:
* [XAMPP](https://www.apachefriends.org/) (con módulos Apache y MySQL) 
* [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/)
* [Node.js (versión LTS)](https://nodejs.org/)
* [Maven](https://maven.apache.org/) (incluido en la mayoría de IDEs como IntelliJ o Eclipse)

---

## 🔧 Instalación y Configuración

### 1. Base de Datos (MySQL)
1. Abre el Panel de Control de XAMPP e inicia **únicamente el módulo de MySQL** (no es necesario activar Apache).
2. Para crear la base de datos, accede a tu gestor de MySQL y ejecuta la primera sentencia del script que se encuentra en la carpeta del proyecto:
   * **Ruta del script:** `/backend/bbdd.sql` .
3. **Población de datos:** No necesitas importar tablas de forma manual; el ORM **Hibernate** está configurado para generar automáticamente toda la estructura y mapeo de las entidades. Al levantar el servidor, este leerá e insertará de forma nativa los datos iniciales y registros de prueba desde el archivo `data.sql`.

### 2. Despliegue del Backend (Spring Boot)

1. Abre la consola y muevete a la carpeta `/backend/restaurante`, una vez ahí ejecuta el comando: '.\mvnw.cmd spring-boot:run', este iniciará los servicios de backend.
2. Dirigete a la carpeta `/frontend`y una vez ahí ejecuta el comando 'npm run dev', este levantará los servicios del frontend.

**Cabe recalcar que para ejecutar el comando anterior es necesario haber instalado las librerias de npm : `npm install`**

3. Una vez tenemos ambos servicios levantados nos dirigimos al navegador y nos dirigimos a la ruta `http://localhost:5173`

### Credenciales de Fábrica 📋 
Utiliza estas cuentas preconfiguradas en el sistema para evaluar los diferentes roles de acceso y completar el flujo de simulación de pago en el entorno sandbox:

### 1. Cuentas para Inicio de Sesión (Roles de la Aplicación)
Estas credenciales dan acceso a los entornos protegidos de la web según el tipo de usuario:

**Rol Cliente:**

Email: cliente1@example.com
Contraseña: abcd

**Rol Administrador:**

Email: admin@example.com
Contraseña: 1234

### 2. Credenciales para la Simulación de Pago (Sandbox de PayPal)
Una vez que el carrito esté listo y accedas a la pasarela de pago integrada, utiliza los siguientes datos para completar la transacción de prueba:

**Cuenta Personal de Comprador (Sandbox Personal):**

Email: correoPruebaPago@
Contraseña: contraPruebaPago
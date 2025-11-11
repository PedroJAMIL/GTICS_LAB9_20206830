# 🛒 Lab09-Cliente - Aplicación Cliente para API REST Protegido

## ✅ Ejercicio 4: Consumo de Servicios Protegidos (6 puntos)

Esta es una aplicación **Spring Boot cliente** que consume el API REST protegido del servidor Lab09.

---

## 📋 Requisitos Previos

1. **El servidor API debe estar ejecutándose en el puerto 8080**
   - Proyecto: `Lab09`
   - Puerto: 8080
   - Endpoint: http://localhost:8080/api/product

2. **Java 17 o superior instalado**

3. **Maven (incluido con el proyecto)**

---

## 🚀 Cómo Ejecutar

### Opción 1: Desde la Terminal (PowerShell)

```powershell
# Navegar a la carpeta del proyecto
cd C:\Users\cs\Desktop\Lab09-Cliente

# Ejecutar la aplicación
.\mvnw.cmd spring-boot:run
```

### Opción 2: Desde IntelliJ IDEA

1. Abrir IntelliJ IDEA
2. File → Open → Seleccionar la carpeta `Lab09-Cliente`
3. Esperar a que Maven descargue las dependencias
4. Ejecutar `ClienteApplication.java`
5. La aplicación se iniciará en el puerto **8081**

---

## 🌐 Acceso a la Aplicación

Una vez iniciada, abrir en el navegador:

**http://localhost:8081**

---

## 📂 Estructura del Proyecto

```
Lab09-Cliente/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/example/cliente/
│       │       ├── ClienteApplication.java          # Clase principal
│       │       ├── controller/
│       │       │   └── ProductClientController.java # Controlador web
│       │       ├── service/
│       │       │   └── ProductApiService.java       # Servicio que consume API
│       │       └── dto/
│       │           ├── ProductDTO.java              # DTO de Producto
│       │           ├── CategoryDTO.java             # DTO de Categoría
│       │           └── SupplierDTO.java             # DTO de Proveedor
│       └── resources/
│           ├── application.properties               # Configuración (puerto 8081)
│           ├── templates/                           # Vistas HTML Thymeleaf
│           │   ├── index.html                       # Página de inicio
│           │   ├── productos.html                   # Lista de productos
│           │   └── buscar.html                      # Búsqueda por ID
│           └── static/
│               └── css/
│                   └── styles.css                   # Estilos CSS
├── pom.xml                                          # Dependencias Maven
└── README.md                                        # Este archivo
```

---

## 🎯 Funcionalidades Implementadas

### ✅ Ejercicio 4.1: Configuración del Cliente (2 puntos)

**Archivo**: `ProductApiService.java`

- ✅ Configuración de autenticación HTTP Basic
- ✅ Uso de Apache HttpClient con credenciales
- ✅ Manejo de respuestas del API (200, 404, 401)
- ✅ Conversión de JSON a objetos Java (DTOs)

**Credenciales configuradas**:
- Usuario: `admin`
- Contraseña: `pass123`

### ✅ Ejercicio 4.2: Listar Productos Autenticado (2 puntos)

**Ruta**: http://localhost:8081/productos

**Endpoint consumido**: `GET http://localhost:8080/api/product`

**Características**:
- ✅ Vista HTML con tabla de todos los productos
- ✅ Datos obtenidos del endpoint protegido
- ✅ Muestra: ID, Nombre, Categoría, Proveedor, Unidad, Precio
- ✅ Manejo de errores de conexión
- ✅ Mensajes de éxito/error apropiados

### ✅ Ejercicio 4.3: Buscar un Producto Autenticado (2 puntos)

**Ruta**: http://localhost:8081/buscar

**Endpoint consumido**: `GET http://localhost:8080/api/product/{id}`

**Características**:
- ✅ Formulario de búsqueda por ID
- ✅ Consume endpoint del API con autenticación
- ✅ Muestra detalles completos del producto (incluye categoría y proveedor)
- ✅ Manejo de producto no encontrado (404)
- ✅ Manejo de errores de autenticación (401)
- ✅ Mensajes de error apropiados

---

## 🔧 Configuración

**Archivo**: `src/main/resources/application.properties`

```properties
# Puerto del cliente
server.port=8081

# Configuración del API servidor
api.base.url=http://localhost:8080/api/product
api.username=admin
api.password=pass123
```

---

## 🧪 Pruebas

### 1. Listar Productos

1. Iniciar el servidor API (puerto 8080)
2. Iniciar el cliente (puerto 8081)
3. Navegar a: http://localhost:8081/productos
4. Debe mostrar tabla con todos los productos

### 2. Buscar Producto Existente

1. Navegar a: http://localhost:8081/buscar
2. Ingresar ID: `1`
3. Click en "Buscar Producto"
4. Debe mostrar detalles completos del producto

### 3. Buscar Producto Inexistente

1. Navegar a: http://localhost:8081/buscar
2. Ingresar ID: `9999`
3. Click en "Buscar Producto"
4. Debe mostrar mensaje: "Producto no encontrado con ID: 9999"

### 4. Verificar Autenticación

1. Detener el servidor API
2. Intentar listar productos
3. Debe mostrar mensaje de error de conexión

---

## 📦 Dependencias Principales

- **Spring Boot 3.5.7**: Framework principal
- **Spring Web**: Para MVC y REST
- **Thymeleaf**: Motor de plantillas HTML
- **Apache HttpClient 5**: Cliente HTTP con autenticación
- **Jackson**: Procesamiento JSON

---

## 🎨 Características de la UI

- Diseño moderno con degradados
- Navegación intuitiva entre páginas
- Tablas responsivas
- Alertas visuales para mensajes de éxito/error
- Badges para precios
- Diseño responsive (funciona en móviles)

---

## 🔒 Seguridad

La aplicación cliente se conecta al API usando:
- **HTTP Basic Authentication**
- Credenciales configuradas en `application.properties`
- Las credenciales se envían en cada petición al servidor

---

## 🐛 Solución de Problemas

### Error: "Connection refused"
**Causa**: El servidor API no está ejecutándose
**Solución**: Iniciar el servidor Lab09 en el puerto 8080

### Error: "401 Unauthorized"
**Causa**: Credenciales incorrectas
**Solución**: Verificar que las credenciales en `application.properties` coincidan con las del servidor

### Error: "Port 8081 already in use"
**Causa**: El puerto 8081 está ocupado
**Solución**: Cambiar el puerto en `application.properties` o detener el proceso que usa el puerto

---

## ✅ Checklist del Ejercicio 4

- [x] **Configuración del Cliente (2 puntos)**
  - [x] Lógica para consumir servicios protegidos
  - [x] Autenticación configurada (HTTP Basic)
  
- [x] **Listar Productos Autenticado (2 puntos)**
  - [x] Vista HTML con tabla de productos
  - [x] Datos obtenidos del endpoint protegido
  
- [x] **Buscar Producto Autenticado (2 puntos)**
  - [x] Funcionalidad de búsqueda por ID
  - [x] Consume endpoint del API
  - [x] Muestra resultados o mensajes de error apropiados

---

## 👨‍💻 Autor

Laboratorio 09 - Spring Boot con Spring Security

**Fecha**: 10 de noviembre de 2025

---

## 📸 Capturas de Pantalla Sugeridas

Para documentar el laboratorio, tomar capturas de:

1. Página de inicio (http://localhost:8081)
2. Lista de productos con datos (http://localhost:8081/productos)
3. Búsqueda exitosa de un producto
4. Mensaje de producto no encontrado
5. Código fuente de `ProductApiService.java` mostrando autenticación

# ⛩️ Demon Slayer Corps' Coordination System

![Logo](https://i.imgur.com/xCymHMx.png)

## 📝 Descripción del proyecto
Sistema de coordinación para los **Pilares del Cuerpo de Cazadores de Demonios**. 

La aplicación permite:
- Registrar/actualizar posiciones de los Pilares.
- Ver y almacenar mensajes fragmentados y reconstruidos.
- Realizar una triangulación de posiciones para estimar la ubicación de Muzan.
- Ver Pilares y consultar información de cada Pilar.

**Tecnologías utilizadas:**
- Java 17  
- Spring Boot 2.7.13  
- MySQL / MariaDB (via XAMPP)  
- JPA / Hibernate  
- REST API  

---

# 🚀 Cómo ejecutar el proyecto

## 📜 Software requerido

| Herramienta | Descarga | 
|-------------|-----------|
| Java 17 (JDK) | [Instalar aquí](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) |
| Apache Maven | [Instalar aquí](https://maven.apache.org/download.cgi) |
| XAMPP (incluye MySQL) | [Instalar aquí](https://www.apachefriends.org/es/download.html) |
| Postman (para pruebas) | [Instalar aquí](https://www.postman.com/downloads/) |

---

## 📥 Instalación

1. Inicie los servicios **Apache** y **MySQL** en **XAMPP**.

![XAMPP Settings](https://github.com/professorjosedeassis/carometro/blob/main/assets/xampp1.png?raw=true)

2. Dirigete a **http://localhost/phpmyadmin** y crea una nueva base de datos llamada **`coordinationdb`**.

![phpMyAdmin Database Creation](https://i.imgur.com/g1Nq1L4.png)

## 🏃 Ejecución

Abre una **terminal** en la carpeta del proyecto y ejecuta el comando **`mvn spring-boot:run`**.


## 📍 Endpoints disponibles

- Para probar los endpoints de la API, puedes usar **[Postman](https://www.postman.com/downloads/)**.

### 1️⃣ Obtener un Pilar por ID

**GET `/api/pilares/{id}`**

![Ejemplo](https://i.imgur.com/yvgPeN5.png)

**✔ Response 200**
```json
{
    "id": 1,
    "nombre": "Giyu Tomioka",
    "posX": -500,
    "posY": -200,
    "estado": "Combatiendo"
}
```

**❌ Response 404**
```json
{
    "timestamp": "...",
    "error": "Pilar no encontrado"
}
```

### 2️⃣ Registrar o actualizar posición de un Pilar

**POST `/api/pilares/actualizar-posicion`**

![Ejemplo](https://i.imgur.com/qz4Ur6C.png)

**📨 Request**
```json
{
    "pilarId": 1,
    "posX": -480,
    "posY": -210,
    "estado": "Herido"
}
```

**✔ Response 201**
```json
{
    "mensaje": "Posición actualizada exitosamente.",
    "pilar": {
        "id": 1,
        "nombre": "Giyu Tomioka",
        "posX": -480,
        "posY": -210,
        "estado": "Herido"
    }
}
```

**❌ Response 400**
```json
{
    "timestamp": "...",
    "error": "JSON mal formado o datos inválidos"
}
```

**❌ Response 404**
```json
{
    "timestamp": "...",
    "error": "Pilar no existe"
}
```

### 3️⃣ Registrar mensaje fragmentado

**POST `/api/mensajes`**

![Ejemplo](https://i.imgur.com/C7Ymz4o.png)

**📨 Request**
```json
{
    "pilarId": 2,
    "contenidoFragmentado": "M... apa... nor..."
}
```

**✔ Response 201**
```json
{
    "id": 1,
    "pilarId": 2,
    "contenidoFragmentado": "M... apa... nor...",
    "contenidoReconstruido": null,
    "timestamp": "..."
}
```

### 4️⃣ Reconstruir mensaje

**PUT `/api/mensajes/{id}/reconstruir`**

![Ejemplo](https://i.imgur.com/kRU7fxG.png)

**📨 Request**
```json
{
    "contenidoReconstruido": "Muzan apareció al norte."
}
```

**✔ Response 200**
```json
{
    "id": 1,
    "pilarId": 2,
    "contenidoFragmentado": "M... apa... nor...",
    "contenidoReconstruido": "Muzan apareció al norte.",
    "timestamp": "..."
}
```

### 5️⃣ Triangulación — estimar ubicación de Muzan

**GET `/api/inteligencia/triangulacion`**

![Ejemplo](https://i.imgur.com/jr6q1hE.png)

**✔ Response 200**
```json
{
    "descripcion": "Alta probabilidad de presencia demoníaca cerca del punto calculado.",
    "posiblePosicionMuzan": {
        "x": 33,
        "y": -67
    },
    "nivelConfianza": 0.63
}
```

### 6️⃣ Obtener todos los Pilares

**GET `/api/pilares`**

![Ejemplo](https://i.imgur.com/AVo5eBg.png)

**✔ Response 200**
```json
[
    {
        "id": 1,
        "nombre": "Giyu Tomioka",
        "posX": -500,
        "posY": -200,
        "estado": "Combatiendo"
    },
    {
        "id": 2,
        "nombre": "Sanemi Shinazugawa",
        "posX": 100,
        "posY": -100,
        "estado": "Combatiendo"
    },
    {
        "id": 3,
        "nombre": "Mitsuri Kanroji",
        "posX": 500,
        "posY": 100,
        "estado": "Combatiendo"
    }
]
```

### 7️⃣ Obtener todos los mensajes

**GET `/api/mensajes`**

![Ejemplo](https://i.imgur.com/3MoiNmo.png)

**✔ Response 200**
```json
[
    {
        "id": 4,
        "pilarId": 2,
        "contenidoFragmentado": "M... apa... nor...",
        "contenidoReconstruido": "Muzan apareció al norte.",
        "timestamp": "2025-11-21T20:25:56"
    },
    {
        "id": 5,
        "pilarId": 1,
        "contenidoFragmentado": "M... des... puer...",
        "contenidoReconstruido": null,
        "timestamp": "2025-11-21T20:26:06"
    }
]
```
**❌ Response 404**
```json
{
    "mensaje": "No hay mensajes registrados.",
    "total": 0
}
```

<sub> 👨🏻‍💻 Desarrollado por **Cristian Crespo** 🖤 </sub>

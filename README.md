<img width="1660" height="500" alt="BannerBack" src="https://github.com/user-attachments/assets/b8c092fe-6903-4dd0-aaef-49b61e1188c3" />

# 📚 LearnSphere

LearnSphere es una aplicación móvil educativa desarrollada para facilitar la comunicación y el seguimiento académico entre profesores, tutores legales y alumnos. La plataforma permite gestionar notas, faltas, observaciones, horarios y comunicación en tiempo real, ofreciendo una experiencia diferenciada según el rol del usuario.

---

## 🛠 Tecnologías Utilizadas

### Backend
- **Java 21+**
- **Spring Boot** — Framework principal
- **Spring Web** — API REST
- **Spring Data JPA** — Persistencia con base de datos
- **Lombok** — Reducción de código repetitivo
- **BCrypt** — Cifrado de contraseñas

### Base de Datos
- **PostgreSQL** — Base de datos relacional

### Herramientas
- **Maven** — Gestión de dependencias
- **Postman** — Pruebas de endpoints

---

## 👥 Roles de Usuario

| Rol | Descripción |
|---|---|
| **Tutor legal** | Padre o madre del alumno. Consulta notas, observaciones, horario y se comunica con profesores |
| **Profesor** | Gestiona sus alumnos, registra notas y observaciones, y se comunica con tutores |
| **Admin** | Gestión global de la plataforma *(pendiente de implementar)* |

---

## 📱 Funcionalidades

### Tutor legal
- Consulta de notas por trimestre de su hijo
- Lectura de observaciones de los profesores
- Visualización de horario
- Chat con profesores y grupos de asignaturas
- Notificaciones de nuevas notas, observaciones, material escolar y mensajes
- Acceso al material escolar del curso

### Profesor
- Listado de sus alumnos
- Registro y edición de notas por trimestre
- Creación de observaciones por alumno
- Subida de material escolar
- Chat con tutores y grupos

---

## 📂 Instalación y Configuración

### Requisitos previos
- Java 21+
- Android Studio (Hedgehog o superior)
- PostgreSQL
- Maven

### Backend

1. Clona el repositorio
```bash
git clone https://github.com/Adriana-002/LearnSphere-Backend.git
```

2. Configura la base de datos en `application.properties`
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/learnsphere
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=validate
```

3. Ejecuta el script de base de datos
```bash
psql -U tu_usuario -d learnsphere -f init.sql
```

4. Arranca el servidor
```bash
mvn spring-boot:run
```

El servidor quedará disponible en `http://localhost:8081`

### Frontend (Android)

1. Abre el proyecto en Android Studio
2. En `ApiClient.java` verifica que la URL base apunte al servidor:
```java
// Para emulador Android
public static final String BASE_URL = "http://10.0.2.2:8081/api/";

// Para dispositivo físico (usa la IP local de tu máquina)
public static final String BASE_URL = "http://192.168.X.X:8081/api/";
```
3. Compila y ejecuta en un emulador o dispositivo físico

---

## 🗄 Estructura del Proyecto
```
learnsphere-backend/
├── controller/       ← Endpoints de la API REST
├── service/          ← Lógica de negocio
├── repository/       ← Acceso a base de datos (JPA)
├── model/            ← Entidades JPA
├── dto/              ← Objetos de transferencia de datos
└── enums/            ← Enumeraciones (TipoNotificacion...)

learnsphere-android/
├── data/
│   ├── api/          ← ApiClient + ApiService (Retrofit)
│   ├── model/        ← Modelos de datos
│   ├── repository/   ← Llamadas a la API
│   └── local/        ← SessionManager (SharedPreferences)
└── ui/
    ├── auth/         ← Login
    ├── main/         ← MainActivity + BottomNav
    ├── inicio/       ← Notificaciones y avisos
    ├── chat/         ← Mensajería
    ├── seguimiento/  ← Notas, faltas, observaciones
    └── equipos/      ← Asignaturas y material escolar
```

---

## 🔐 Autenticación

La autenticación se realiza mediante un sistema de login con email y contraseña. Las contraseñas se almacenan cifradas con **BCrypt**. Al iniciar sesión correctamente el servidor devuelve los datos del usuario (token, rol, nombre) que se guardan localmente en el dispositivo mediante `SharedPreferences`. El rol determina qué contenido y funcionalidades ve cada usuario.

---

## 📡 Arquitectura

El proyecto sigue una arquitectura **cliente-servidor** con patrón **MVVM** en el frontend:
```
Android (MVVM)                    Spring Boot
─────────────────                 ─────────────────────
Fragment (Vista)                  Controller
    ↕                                 ↕
ViewModel (Lógica UI)             Service (Lógica negocio)
    ↕                                 ↕
Repository (Datos)   ←── HTTP ──→ Repository (JPA)
    ↕                                 ↕
ApiService (Retrofit)             PostgreSQL
```

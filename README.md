# CQRS Pattern - Sistema de Gestión de Estudiantes

Este proyecto implementa el patrón **CQRS (Command Query Responsibility Segregation)** utilizando Spring Boot y Kafka para la sincronización entre servicios.

## 📋 Descripción

El proyecto está dividido en dos microservicios independientes:

- **cqrs_command**: Maneja las operaciones de escritura (CREATE, UPDATE, DELETE)
- **cqrs_query**: Maneja las operaciones de lectura (SELECT)

La comunicación entre ambos servicios se realiza mediante eventos a través de **Apache Kafka**.

## 🏗️ Arquitectura

```
┌─────────────────┐         ┌──────────┐         ┌─────────────────┐
│  CQRS Command   │────────>│  Kafka   │────────>│   CQRS Query    │
│   (Escritura)   │  Events │          │ Events  │   (Lectura)     │
└─────────────────┘         └──────────┘         └─────────────────┘
        │                                                  │
        ▼                                                  ▼
   [DB Write]                                         [DB Read]
```

## 🚀 Tecnologías

- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **Apache Kafka**
- **MySQL** (o base de datos relacional)
- **Maven**
- **Lombok**

## 📦 Requisitos Previos

- JDK 21 o superior
- Maven 3.6+
- Apache Kafka 2.8+
- MySQL 8.0+ (o base de datos compatible)
- Docker (opcional, para ejecutar Kafka)

## ⚙️ Configuración

### 1. Base de Datos

Ejecuta el script SQL incluido en cada módulo:
- `cqrs_command/src/main/resources/create_database.sql`
- `cqrs_query/src/main/resources/create_database.sql`

### 2. Apache Kafka

Puedes ejecutar Kafka usando Docker:

```bash
docker-compose up -d
```

O instalar Kafka manualmente y asegurarte de que esté ejecutándose en `localhost:9092`.

### 3. Configuración de Aplicación

Revisa y ajusta los archivos de configuración según tu entorno:

**cqrs_command/src/main/resources/application.yml**
**cqrs_query/src/main/resources/application.yml**

## 🔧 Instalación y Ejecución

### Opción 1: Ejecutar con Maven Wrapper

#### Servicio Command (Puerto 8080)
```bash
cd cqrs_command
mvnw.cmd spring-boot:run
```

#### Servicio Query (Puerto 8081)
```bash
cd cqrs_query
mvnw.cmd spring-boot:run
```

### Opción 2: Compilar y ejecutar JAR

```bash
# Compilar Command
cd cqrs_command
mvnw.cmd clean package
java -jar target/cqrs_command-0.0.1-SNAPSHOT.jar

# Compilar Query
cd cqrs_query
mvnw.cmd clean package
java -jar target/cqrs_query-0.0.1-SNAPSHOT.jar
```

## 📡 Endpoints API

### CQRS Command (Puerto 8080)

- **POST** `/students` - Crear estudiante
- **PUT** `/students/{id}` - Actualizar estudiante
- **DELETE** `/students/{id}` - Eliminar estudiante

### CQRS Query (Puerto 8081)

- **GET** `/students` - Listar todos los estudiantes
- **GET** `/students/{id}` - Obtener estudiante por ID

## 📝 Ejemplo de Uso

### Crear un estudiante (Command)
```bash
curl -X POST http://localhost:8080/students ^
  -H "Content-Type: application/json" ^
  -d "{\"name\":\"Juan Pérez\",\"email\":\"juan@example.com\",\"age\":20}"
```

### Consultar estudiantes (Query)
```bash
curl http://localhost:8081/students
```

## 🔄 Flujo de Datos

1. El cliente envía un comando de escritura al servicio **Command**
2. El servicio **Command** procesa la operación y persiste en su base de datos
3. Se publica un evento en **Kafka**
4. El servicio **Query** consume el evento de Kafka
5. El servicio **Query** actualiza su base de datos de lectura
6. Los clientes pueden consultar datos actualizados desde el servicio **Query**

## 📂 Estructura del Proyecto

```
cqrs_pattern/
├── cqrs_command/          # Microservicio de comandos
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── co/vinni/cqrs/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── dto/
│   │       │       └── persistence/
│   │       └── resources/
│   └── pom.xml
├── cqrs_query/            # Microservicio de consultas
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── co/vinni/cqrs/
│   │       │       ├── controller/
│   │       │       ├── service/
│   │       │       ├── dto/
│   │       │       └── persistence/
│   │       └── resources/
│   └── pom.xml
└── pom.xml                # POM padre (si aplica)
```

## 🤝 Contribuciones

Si deseas contribuir a este proyecto:

1. Haz un fork del repositorio
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

## 📄 Licencia

Este proyecto es de código abierto y está disponible para fines educativos.

## 👥 Autor

Desarrollado como ejemplo de implementación del patrón CQRS con Spring Boot.

## 📞 Soporte

Para preguntas o problemas, por favor abre un issue en el repositorio.

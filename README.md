# LiterAlura
**LiterAlura** es una aplicación de biblioteca desarrollada en Java que permite obtener, gestionar y almacenar libros de la [API Gutendex]() mientras persiste la información en una base de datos PostgreSQL. Está diseñada para ofrecer una experiencia sencilla y eficiente en la creación de colecciones literarias personalizadas.
## Características
1. **Exploración de libros desde Gutendex API**
   Permite consultar libros y detalles relevantes directamente desde la API.
2. **Persistencia en base de datos**
   Almacena información sobre libros y autores en una base de datos PostgreSQL para consultas posteriores.
3. **Opciones principales del programa:**
   - Búsqueda de libros por:
      - Título.
      - Idioma.

   - Muestra de libros y autores registrados.
   - Filtros avanzados como autores vivos en un determinado año.

## Tecnologías utilizadas
- **Java JDK 21**: Utiliza la última versión LTS para garantizar seguridad y rendimiento.
- **Spring Boot**: Framework robusto para desarrollo rápido de aplicaciones web RESTful.
- **Jakarta Persistence (JPA)**: Para la capa de acceso y gestión de datos.
- **PostgreSQL**: Base de datos relacional.

## Cómo ejecutar el proyecto
### Requisitos previos
- **JDK 17 o 21** instalado.
- PostgreSQL configurado y ejecutándose en tu máquina.

### Pasos
1. Clonar el repositorio:
``` bash
   git clone https://github.com/usuario/literalura.git
   cd literalura
```
1. Configurar la base de datos:
   - Crea una base de datos en PostgreSQL, por ejemplo, `literalura_db`.
   - Configura las credenciales de conexión en el archivo `application.properties`:
``` properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/literalura_db
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
```
1. Ejecutar el proyecto:
   - Abre el proyecto con **IntelliJ IDEA** u otro IDE.
   - Ejecuta la clase principal `LiteraluraApplication`.

2. Interacción:
   - Usa la terminal integrada o herramientas de línea de comandos para interactuar con las opciones del programa.

## Estructura del proyecto
- **src/main/java**:
   - **Controllers**: Manejan las entradas del usuario.
   - **Services**: Contienen la lógica empresarial.
   - **Repositories**: Gestión de acceso a datos.
   - **Models**: Representan las entidades de base de datos.

- **src/main/resources**: Archivos de configuración.
- **pom.xml**: Configuración y dependencias del proyecto Maven.

## Contribuciones
¡Eres bienvenido a contribuir a este proyecto! Haz un fork del repositorio, aplica tus cambios y envía un pull request.

# Sistema de Gestión de Cuentas Bancarias

...

## Documentación Técnica

### Arquitectura del Proyecto

El sistema está basado en una arquitectura de **capas** que incluye las siguientes capas principales:

1. **Capa de controladores**:
   - Aquí se gestionan las interacciones con el usuario. En este proyecto, se exponen servicios REST utilizando Spring Boot, que permiten a los clientes (ya sea aplicaciones frontend o servicios de otro tipo) interactuar con el sistema.

2. **Capa de servicios**:
   - **CustomerService**: Este servicio maneja la lógica de negocio relacionada con los clientes (por ejemplo, búsqueda, creación, y validación de clientes).
   - **CustomerServiceImpl**: Es la implementación del servicio que interactúa con el repositorio para acceder y manipular los datos de los clientes.

3. **Capa de persistencia**:
   - Utiliza **Spring Data JPA** para la interacción con la base de datos.
   - **CustomerRepository** es el repositorio que proporciona métodos de acceso a los datos, como la búsqueda de clientes por ID.

4. **Base de datos**:
   - Se utiliza una base de datos en memoria **H2** para pruebas.

### Flujos de Trabajo y Funcionalidades

1. **Búsqueda de Clientes**:
   - Cuando un cliente realiza una solicitud para obtener los datos de un cliente por ID, el `CustomerServiceImpl` consulta el `CustomerRepository`, que a su vez realiza la consulta a la base de datos para recuperar la información del cliente.
   - Si el cliente existe, se devuelve un objeto de tipo `Customer` con los detalles del cliente (ID, nombre, apellido, email). Si no se encuentra el cliente, se devuelve un error 404.

2. **Manejo de Excepciones**:
   - En los tests, se valida que el sistema devuelva los errores correctos en estos casos (por ejemplo, 404 para un cliente no encontrado).

### Base de Datos
- Se utiliza **H2** para las pruebas, una base de datos en memoria que permite ejecutar las pruebas sin afectar a una base de datos real.
- La estructura de la base de datos tiene la tabla `customers`, con las siguientes columnas:
  - `customer_id` (clave primaria, tipo long)
  - `name` (tipo String)
  - `email` (tipo String, no nulo)
  - `last_name` (tipo String)

### Modelo Entidad Relación

![WhatsApp Image 2025-01-04 at 12 58 24 AM](https://github.com/user-attachments/assets/e5284d01-c45d-4be4-be49-60d8eaf5fc27)

### Pruebas

#### Pruebas Unitarias

Las pruebas unitarias están diseñadas para probar la lógica de negocio en aislamiento. Usamos **JUnit 5** y **Mockito** para simular las dependencias y garantizar que los componentes individuales funcionen correctamente.

Ejemplo de prueba unitaria: Se verifica que el servicio `CustomerServiceImpl` maneje correctamente la lógica de negocio y devuelva la información correcta.

#### Pruebas de Integración

Las pruebas de integración validan que los componentes del sistema (por ejemplo, el servicio y el repositorio) interactúan correctamente.

Las pruebas de integración incluyen:
- Validación de que los servicios interactúan correctamente con la base de datos.
- Verificación de que el flujo de trabajo completo (desde la solicitud hasta la respuesta) funciona sin errores.

### Flujo de Solicitudes

1. **Cliente realiza una solicitud** al endpoint para obtener un cliente por su ID.
   - Ejemplo de solicitud: `GET http://localhost:8080/customer/{id}`

2. **Controlador** recibe la solicitud y delega la responsabilidad al **Servicio** correspondiente.

3. El **Servicio** (`CustomerServiceImpl`) consulta la **base de datos** utilizando el **Repositorio** (`CustomerRepository`) y obtiene la información del cliente.

4. Si el cliente existe, se devuelve la información del cliente en formato JSON.

5. Si el cliente no existe, se devuelve un error 404 con un mensaje indicando que no se encontró el cliente.

### Decisiones de Diseño

- **Inyección de dependencias con Spring**: Utilizamos la anotación `@Autowired` para la inyección de dependencias en lugar de crear instancias manualmente. Esto permite un manejo más flexible y escalable de las dependencias.
- **Uso de JPA para persistencia**: Optamos por usar **JPA** con Spring Data para facilitar la gestión de la base de datos y las consultas.
- **Transacciones**: En las pruebas de integración, utilizamos la anotación `@Transactional` para garantizar que los cambios en la base de datos durante las pruebas no persistan entre pruebas.

### Configuración de Entorno

- **Base de datos en memoria**: Usamos H2 en el entorno de pruebas.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para la creación del backend.
- **Spring Data JPA**: Para la interacción con la base de datos.
- **H2**: Base de datos en memoria utilizada en las pruebas de integración.
- **JUnit 5**: Para la ejecución de pruebas unitarias y de integración.
- **Mockito**: Para la ejecución de pruebas unitarias.
- **Gradle**: Para la gestión de dependencias y ejecución de pruebas.

### Enpoints Usados
Aca tenemos los servicios usados para la creación de los servicios solicitados en la aplicación.

![image](https://github.com/user-attachments/assets/528d37cd-be0b-4c00-8b79-00db7690ffa1)


## Instalación

Para ejecutar este proyecto en tu máquina local, sigue estos pasos:

1. Clona este repositorio:
   ```bash
   git clone https://github.com/Dani-Sofka/BankAccounts.git

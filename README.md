# :trophy: CUSTOMER API: Visitors Usecase


En esta entrega se implementó la lógica de negocio y la estructura base para la entidad Visitors, aplicando los principios de Arquitectura Hexagonal.


## 🧩 Estructura de Carpetas - Visitors


```tree /F
main                                                              
├─ java                                                           
│  └─ com                                                         
│     └─ bootcamp                                                 
│        └─ biopark                                               
│           ├─ application                                        
│           │  └─ port                                            
│           │     ├─ in                                           
│           │     │  ├─ shared                                    
│           │     │  │  └─ BaseResponse.java                      
│           │     │  ├─ CreateVisitorUseCase.java                 
│           │     │  ├─ GetVisitorUseCase.java                    
│           │     │  ├─ SearchVisitorUseCase.java                 
│           │     │  └─ VisitorCommand.java                       
│           │     └─ out                                          
│           │        └─ VisitorRepositoryPort.java                
│           ├─ domain                                             
│           │  └─ model                                           
│           │     └─ Visitor.java                                 
│           ├─ infraestructure                                    
│           │  ├─ entity                                          
│           │  │  └─ VisitorEntity.java                           
│           │  ├─ in                                              
│           │  │  ├─ controller                                   
│           │  │  │  └─ VisitorController.java                    
│           │  │  ├─ dto                                          
│           │  │  │  ├─ request                                   
│           │  │  │  │  └─ VisitorRequestDto.java                 
│           │  │  │  └─ response                                  
│           │  │  │     └─ VisitorResponseDto.java                
│           │  │  ├─ exception                                    
│           │  │  │  ├─ GlobalExceptionHandler.java               
│           │  │  │  ├─ ResourceAlreadyExistsException.java       
│           │  │  │  ├─ ResourceDeletionNotAllowedException.java  
│           │  │  │  ├─ ResourceDuplicatedException.java          
│           │  │  │  └─ ResourceNotFoundException.java            
│           │  │  ├─ mapper                                       
│           │  │  │  └─ VisitorWebMapper.java                     
│           │  │  └─ shared                                       
│           │  │     └─ ErrorResponse.java                        
│           │  └─ out                                             
│           │     ├─ adapter                                      
│           │     │  └─ VisitorPersistenceAdapter.java            
│           │     ├─ mapper                                       
│           │     │  └─ VisitorPersistenceMapper.java             
│           │     └─ repository                                   
│           │        └─ VisitorJpaRepository.java                 
│           └─ BioparkApplication.java                            
└─ resources                                                      
   ├─ static                                                      
   ├─ templates                                                   
   └─ application.properties        
```

🧱 Implementaciones

✅ Domain:

- **`Visitor.java`**: Modelo de dominio puro que representa la entidad del visitante con sus atributos y reglas de negocio asociadas.

✅ Application:

- **Puertos de Entrada (`port.in`)**: Interfaces que definen las acciones que el mundo exterior puede ejecutar en el sistema (Casos de Uso):
    - **`CreateVisitorUseCase.java`**: Contrato para la creación de un nuevo visitante.
    - **`GetVisitorUseCase.java`**: Contrato para obtener un visitante por un identificador.
    - **`SearchVisitorUseCase.java`**: Contrato para la búsqueda avanzada de visitantes.
    - **`VisitorCommand.java`**: Objeto de transferencia de datos interno usado para orquestar los comandos hacia los casos de uso.
    - **`shared/BaseResponse.java`**: Estructura estandarizada para el manejo de respuestas del sistema.
- **Puertos de Salida (`port.out`)**:
    - **`VisitorRepositoryPort.java`**: Interfaz que define las operaciones de persistencia que la aplicación necesita (abstracción del repositorio), cumpliendo con la inversión de dependencias.

✅ Infrastructure:

- **Adaptadores de Entrada (`in/controller` e `in/dto`)**:
    - **`VisitorController.java`**: Controlador REST que expone los endpoints HTTP para interactuar con la entidad.
    - **`dto/request/...`**: Objetos que reciben y validan los datos crudos enviados por el cliente antes de transformarlos en comandos internos.
- **Persistencia (`entity`)**:
    - **`VisitorEntity.java`**: Entidad de persistencia mapeada para la base de datos (por ejemplo, mediante JPA/Hibernate), separando el almacenamiento físico del modelo de dominio.


---

## 🔌 Rutas Implementadas:

__URL BASE:__ http://localhost:8080/api/v1/

| <center>Método HTTP</center> | <center>Ruta</center>      | <center>Descripción</center>                     | <center>Status</center> |
| ---------------------------- | -------------------------- | ------------------------------------------------ | ----------------------- |
| **`GET`**                    | `/visitor/all`             | Obtiene lista completa de visitantes             | `200 OK`                |
| **`GET`**                    | `/visitor/{id}`            | Busca un visitante específico por su ID único    | `200 OK`                |
| **`GET`**                    | `/visitor/por-nombre`      | Filtra y obtiene un visitante buscado por nombre | `302 FOUND`             |
| **`POST`**                   | `/visitor/`                | Registra un nuevo visitante en el sistema        | `201 CREATED`           |
| **`POST`**                   | `/visitor/all`             | Registra un grupo de visitantes en el sistema    | `201 CREATED`           |
| **`PUT`**                    | `/visitor/actualizar/{id}` | Actualiza un visitante por su ID                 | `200 OK`                |
| **`DELETE`**                 | `/visitor/delete/{id}`     | Elimina un visitante del sistema por su ID       | `200 OK`                |
| **`DELETE`**                 | `/VISITOR/delete-all`      | Elimina todos los visitantes                     | `200 OK`                |

## 📆 Branch utilizado

> Todos estos cambios se encuentran en la rama `ref-visitors-usecase` del repositorio:  
**[LDK-R45TT / proyecto-biopark-api (Branch: ref-visitors-usecase)](https://github.com/LDK-R45TT/proyecto-biopark-api/commits/ref-visitors-usecase)**
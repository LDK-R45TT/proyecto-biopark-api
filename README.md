# :trophy: CUSTOMER API: Security Config


## 🧠 Contenidos
Diseño e **implementación** de la capa de seguridad y **autenticación** del sistema bajo arquitectura hexagonal. Se **priorizó la integración** de Spring Security para asegurar las rutas y tokens JWT para **el control de** acceso.

## 🧱 Entidades trabajadas:

- USER ENTITY

## 🔒 Estrategia de Permisos (Security Filter Chain)
El sistema implementa un modelo de seguridad evaluado de arriba hacia abajo de forma estricta. El acceso a los recursos del sistema se gestiona bajo las siguientes condiciones:

| **Modulos**                                              |      **Metodos Http**       | **Roles Permitidos** | **Acción**                                         |
| :------------------------------------------------------- |:---------------------------:| :------------------- | :------------------------------------------------- |
| **`modulos`** environment, rate (excepto **`visitors`**) |          **`GET`**          | `ADMIN / USER`       | Lectura global.                                    |
| **`Autenticación`** (`/auth/**`, `/error`)               |      **`CUALQUIERA`**       | `Publico`            | Registro, Login y manejo global de errores.        |
| **`Visitors`** (`/visitors/**`)                          | **`POST`, `PUT`, `DELETE`** | `ADMIN`              | Escritura y eliminacion.                           |
| **`Environment`** (`/environment/**`)                    | **`POST`, `PUT`, `DELETE`** | `ADMIN`              | Escritura y eliminacion recintos.                  |
| **`Rate`** (`/rate/**`)                                  | **`POST`, `PUT`, `DELETE`** | `ADMIN`              | Escritura y eliminacion tarifas.                   |
| **`Cualquier otra ruta`**                                |      **`CUALQUIERA`**       | `Authenticated`      | Requiere un token JWT válido de forma obligatoria. |

## 📁 Estructura del Proyecto

userSession                                       
├─ application                                    
│  ├─ port                                        
│  │  ├─ in                                                                  
│  │  │  └─ usecase                               
│  │  │     └─ AdminUseCase.java                  
│  │  └─ out                                      
│  │     └─ UserSessionRepositoryPort.java        
│  └─ service                                     
│     └─ AdminService.java                        
├─ domain                                         
│  └─ model                                       
│     ├─ AuthenticatedUser.java                   
│     ├─ Role.java                                
│     └─ User.java                                
├─ infraestructure                                
│  ├─ entity                                      
│  │  └─ UserEntity.java                          
│  ├─ in                                          
│  │  ├─ controller                               
│  │  │  └─ AuthController.java                   
│  │  ├─ dto                                      
│  │  │  ├─ request                               
│  │  │  │  ├─ LoginRequest.java                  
│  │  │  │  ├─ RegisterRequest.java               
│  │  │  │  └─ UserSessionRequest.java            
│  │  │  └─ response                              
│  │  │     ├─ AuthResponse.java                  
│  │  │     └─ UserSessionResponse.java           
│  │  ├─ exception                                
│  │  │  ├─ GlobalExceptionHandler.java           
│  │  │  ├─ InvalidAuthCredentialsException.java  
│  │  │  └─ ResourceAlreadyExistsException.java   
│  │  ├─ mapper                                   
│  │  └─ shared                                   
│  ├─ out                                         
│  │  ├─ adapter                                  
│  │  │  └─ UserSessionAdapter.java               
│  │  ├─ mapper                                   
│  │  │  └─ UserPersistenceMapper.java            
│  │  └─ repository                               
│  │     └─ UserDataSourceRepository.java         
│  └─ security                                    
│     ├─ config                                   
│     │  └─ SecurityConfig.java                   
│     ├─ AuthenticationService.java               
│     ├─ CustomUserDetailsService.java            
│     ├─ ErrorResponse.java                       
│     ├─ JwtAuthenticationFilter.java             
│     ├─ SecurityErrorHandler.java                
│     ├─ SecurityUser.java                        
│     └─ TokenService.java                        
└─ UserSessionApplication.java

## 🔌 Rutas Implementadas:

__URL BASE:__ http://localhost:8080/api/v1

| <center>Método HTTP</center> | <center>Ruta</center>      | <center>Descripción</center>                                                                                            | <center>Status</center> |
| ---------------------------- | -------------------------- | ----------------------------------------------------------------------------------------------------------------------- | ----------------------- |
| **`POST`**                   | `/auth/register`           | Registra un usuario en el sistema. Retorna una confirmacion exitosa.                                                    | `200 OK`                |
| **`POST`**                   | `/auth/login`              | Autentica un usuario existente mediante sus credenciales. Retorna el token de acceso (JWT) para peticiones posteriores. | `200 OK`                |

## 📆 Branch utilizado

> Todos estos cambios se encuentran en la rama `ref-security-config` del repositorio:    
**[LDK-R45TT / proyecto-biopark-api (Branch: ref-security-config)](https://github.com/LDK-R45TT/proyecto-biopark-api/commits/ref-security-config))**45TT / proyecto-biopark-api (Branch: ref-security-config)](https://github.com/LDK-R45TT/proyecto-biopark-api/commits/ref-security-config))**
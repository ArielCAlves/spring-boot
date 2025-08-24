| Descrição | Link |
|:---------:|:----:|
| TP1 | [Link do TP1](https://github.com/ArielCAlves/spring-boot/tree/main/tp1) |
| TP2 | [Link do TP2](https://github.com/ArielCAlves/spring-boot/tree/main/tp2) |
| TP3 | Em Construção |
| AT  | Em Construção |

# TP1 - Desenvolvimento de Serviços REST com Spring Boot

## Descrição
Este projeto foi desenvolvido como primeiro projeto da disciplina **Spring Boot** implementando serviços REST para operações matemáticas básicas.

O sistema expõe endpoints para:
- **Adição**
- **Subtração**
- **Multiplicação**
- **Divisão**
- **Potência**

As operações podem ser chamadas via **GET** ou **POST**.

---

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.5.4
- Spring Web
- Spring Validation
- Spring Actuator
- Lombok
- Maven

---

# TP2 - Desenvolvimento de Serviços REST para Gerenciamento de Lista de Animes

## Descrição
Este projeto foi desenvolvido como segundo projeto da disciplina **Spring Boot**, implementando um sistema de gerenciamento de lista de animes para acompanhar o status de episódios assistidos.  

A aplicação expõe endpoints REST para realizar operações de **CRUD (Create, Read, Update, Delete)** sobre os animes cadastrados, permitindo:  
- **Cadastrar** um novo anime na lista  
- **Listar** todos os animes com paginação  
- **Buscar** um anime pelo identificador único
- **Atualizar** informações de um anime existente  
- **Remover** um anime da lista  

Os recursos seguem o padrão RESTful e retornam códigos de status HTTP adequados para cada operação.

---

## Tecnologias Utilizadas
- Java 17  
- Spring Boot 3.5.5  
- Spring Web  
- Spring Data JPA  
- Spring Validation  
- Spring Actuator  
- Flyway  
- H2 Database (desenvolvimento)  
- PostgreSQL (produção)  
- Lombok  
- Maven  

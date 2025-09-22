# Repositório Dedicado às entregas de Spring Boot
### Abaixo segue o link de cada TP e as respecitvas descrições.

| Descrição | Link |
|:---------:|:----:|
| TP1 | [Link do TP1](https://github.com/ArielCAlves/spring-boot/tree/main/tp1) |
| TP2 | [Link do TP2](https://github.com/ArielCAlves/spring-boot/tree/main/tp2) |
| TP3 | [Link do TP3](https://github.com/ArielCAlves/spring-boot/tree/main/tp3) |
| AT  | [Link do AT](https://github.com/ArielCAlves/spring-boot/tree/main/assessment) |

---

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



---

# TP3 - Desenvolvimento de Serviços REST com persistência em Banco de Dados (em memória)

## Descrição
Este projeto foi desenvolvido como terceiro projeto da disciplina **Spring Boot**, com foco na implementação de um sistema de **CRUD completo** para gerenciamento de dados empresariais.  

A aplicação expõe endpoints REST para realizar operações de **Create, Read, Update e Delete** sobre as seguintes entidades:  
- **Funcionários (Employees)**  
- **Produtos (Products)**  
- **Fornecedores (Suppliers)**  
- **Clientes (Customers)**  
- **Categorias (Categories)**  

Cada recurso é acessível através de métodos **GET, POST, PUT e DELETE**, retornando códigos de status HTTP adequados.  
Os dados são persistidos utilizando **Spring Data JPA** com banco de dados **H2** em memória.  

Foram também implementados **testes unitários** para validar as principais funcionalidades de cada serviço, garantindo confiabilidade e cobertura de código acima de 80%.

---

## Tecnologias Utilizadas
- Java 17  
- Spring Boot 3.5.5  
- Spring Web  
- Spring Data JPA  
- H2 Database  
- Spring Validation  
- Lombok  
- Maven  
- JUnit 5  
- Mockito

  ---

# AT - Assessment

## Descrição
O **Assessment** foi desenvolvido como entrega final da disciplina **Spring Boot**, implementando um sistema completo de **CRUD** com as seguintes entidades sendo principais:  
- **Alunos**  
- **Disciplinas**  
- **Notas**  

A aplicação expõe endpoints REST que permitem:  
- Cadastrar alunos e disciplinas  
- Atribuir e atualizar notas  
- Consultar desempenho dos alunos por disciplina  
- Remover registros  

Além do CRUD, foram implementadas regras de negócio para cálculo automático da **situação do aluno** (Aprovado, Reprovado, Em Recuperação).  

---

## Tecnologias Utilizadas
- Java 17  
- Spring Boot 3.5.6  
- Spring Web  
- Spring Data JPA  
- H2 Database  
- Spring Security  
- Spring Validation  
- Lombok  
- Maven  
- JUnit 5  

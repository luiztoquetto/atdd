# Projeto AC2

Esse projeto irá contemplar:

- Clean architecture (Parcial)
    - Por conta do escopo, não iremos separar a entidade do banco de dados da entidade do domínio
    - Por conta do escopo, não iremos utilizar casos de uso

- Estrutura de cada módulo
    - dominio
        - entidades
            - entidades daquele módulo (entidade de dominio + entidade de banco de dados)
        - repositorios
            - interfaces de repositorio
    - infra
        - repositorios
            - implementações de repositorio (por exemplo, JPA ou em memória)
    - presenter
        - controllers
            - controllers da aplicação
        - dtos
            - inputs
                - interfaces de entrada
            - outputs
                - interfaces de saída 


- Cada integrante irá desenvolver um GET, POST e um teste (do GET pelo menos)
    - GET
        - [x] Leo
        - [x] Luiz
        - [x] Laura
        - [x] Vinicius
    - POST
        - [x] Leo
        - [x] Luiz
        - [x] Laura
        - [x] Vinicius

- Ambiente Dev 
    - Pipeline rodando os testes (+ relatório Jacoco) e gerando imagem docker
    - Banco de dados H2

- Ambiente Homologação
    - Pipeline rodando um GET /cursos no ambiente de homologação
    - Banco de dados PostgreSQL (local)

- Ambiente Produção
    - Pipeline rodando um restart na máquina da Azure que está rodando a imagem do docker gerada anteriormente
    - Banco de dados PostgreSQL
    - Rodar na Azure
        - App Service
        - Azure PostgreSQL Database

> Para o banco de dados, fizemos um seed dos dados no arquivo `AtddApplication.java` pois achamos ais prático para a disciplina.

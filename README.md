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
        - [ ] Luiz
        - [ ] Laura
        - [ ] Vinicius
    - POST
        - [x] Leo
        - [ ] Luiz
        - [ ] Laura
        - [ ] Vinicius

- Ambiente Dev 
    - Pipeline rodando os testes (+ relatório Jacoco) e gerando imagem docker
    - Banco de dados H2

- Ambiente Homologação
    - Pipeline rodando [a preencher]
    - Banco de dados PostgreSQL

- Ambiente Produção
    - Pipeline rodando [a preencher]
    - Banco de dados PostgreSQL
    - Rodar na Azure

> Para o banco de dados H2, fizemos um seed dos dados no arquivo `AtddApplication.java`.
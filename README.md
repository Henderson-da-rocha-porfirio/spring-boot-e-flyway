# Spring Boot e Swagger
### Versões envolvidas:
| Software | Versão |
| :--- | :---: |
| `Java` | 11 |
| `Spring` | 2.7.2 |
### Dependências:
| Dependências        |             utilidade             |
|:--------------------|:---------------------------------:|
| `Spring Web`        | Para rodar dois endpoints simples |
| `Lombok`            |       Diminui Boiler Plate        |
| `Flyway Migration`  |        Fará as migrations         |
| `PostgreSQL Driver` |             Database              |
| `Spring Data JPA`   |    Consulta e cadastro simples    |
| `Swagger`           |    Especificação aberta para definição de APIs REST    |

### Para usar o Swagger:
#### URL:
````
http://localhost:8080/swagger-ui/#/usuario-controller
````
#### URL:
#### GET:
````
Try it out -> Execute
````
#### POST:
````
1 -> Try it out
2 -> Adicione para alterar ou inserir exemplo: 
{
  "cpf": "00098712341",
  "id": 2,
  "idade": 30,
  "nome": "Ricardo Paviloche"
}
3 -> Execute
````

### Erro: Failed to start bean 'documentationPluginsBootstrapper'
##### - adicionar ao application.properties
````
spring.mvc.pathmatch.matching-strategy = ANT_PATH_MATCHER
````

### Testando no Postman
#### GET
````
http://localhost:8080/usuario
````
#### POST - INSERINDO e ALTERANDO exemplo:
#### Config:
````
{
"cpf": "00098712341",
"id": 3,
"idade": 30,
"nome": "Ricarde"
}
````
````
{
"cpf": "00098712341",
"id": 3,
"idade": 30,
"nome": "Ricardo"
}
````
#### Rodando:
````
http://localhost:8080/usuario
````

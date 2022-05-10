# SPRING DATA JPA

## ORM - Object Relation Mapping:
````
É uma técnica para mapeamento de classes (orientação a objeto) para tabelas de banco relacional.
De forma a simplificar a persitência de dados e tornar possível que
o progamador trabalhe mais com a orientação a objeto e menos no modelo relacional.
````
### Comparação I

|   Modelo Relacional   | Modelo OO |
|   :---:     |   :---:     |
| Tabela  | Classe |
| Linha  | Objeto |
| Coluna  | Atributo |
| :---:   | Método |
| Chave Estrangeira(FK)  | Associação entre Classes |

### Comparação II

|   Modelo Relacional   | Modelo OO |
|   :---:     |   :---:     |
| tab_pessoas  | public class Pessoa |
| :---:  | :---: |
| id BIGINT(20)  | private Long id; |
| :---:   | :---: |
|:---:  | :---: |

### 1. Persistência Com ORM:
### - A ORM traduz as instruções que o nosso código Java passar para ela.
````
Código Java -> ORM -> Driver -> SQL
````
### i. JPA: Java Persistence API
````
É uma especificação JEE, ou seja, uma solução ORM para a persitência de dados padronizado para aplicações Java.
````
#### - Define a forma de mapeamento de objeto relacional
#### - Possui uma API de consultas
#### - Possui uma API de modificação de dados
#### - Tem uma Linguagem de Consultas (JPQL)

### ii. Hibernate
````
É uma implementação da especificação JPA.
````
### 2. JPA vs HIBERNATE
````
Código Java -> ORM = JPA (ESPECIFICA:métodos; interfaces;) / HIBERNATE (IMPLEMENTA: métodos e interfaces) -> Driver -> SQL
````

## A. Entendendo o Fluxo de Execução do Spring ao corrermos ( run ) a nossa aplicação:
#### 1. MAVEN CLASSPATH: Ele começa buscando em nossa classpath as dependências do Maven que foram inicializadas na instalação do projeto como o Spring:
##### a. Se não usarmos outro fornecedor(vendor) ORM ( Object Relational Mapping = processo de mapeamento de uma classe java à tabela do database e seus campos ou membros para as colunas das tabelas existentes. ), ele usará o hibernate por default.
##### b. Busca também pelo connector sql ( ou mysql se estiver usando ).
#### 2. APPLICATION PROPERTIES: Percebe que nós estamos buscando conectar o sql database e busca pelo application.properties.
##### a. E acha as informações relativas a conexão como url, username, password e etc.
#### 3. PACKAGES: Escaneia todos os sub-packages do package principal em que a classe Main estar presente.
##### a. Por isso esta classe precisa estar com a annotation @SpringBootApplication para ela escanear tanto o Package quanto os sub-packages.
#### 4. REPOSITORY: O Spring Boot terá acesso a todas as informações e/ou os dados Spring que possuem a informação para criar a JPA entity manage factory usando as informações de data source que nós informamos para ele na properties.
##### a. Ele sabe como conectar-se e também estabelecer uma conexão ao database.
##### b. Dessa forma, ele criará uma EntityManager que é a classe chave no JPA.
##### c. E quando executa nossa Interface ProdutoRepositorio com o método respository.save, Spring cria internamente uma implementação, uma implementação representante ( proxy ) dessa classe que retornará os métodos invocados em EntityManager.
##### d. Então, quando invocamos métodos como "save" ou um "find one" ou um "delete", internamente, Spring está invocando os métodos de EntityManager através de uma classe que gera em tempo real a implementação nessa interface em particular e executa as operações de gerenciamento necessárias no Database.
##### e. Com isso, evitamos todos os códigos boiler plates ( vários códigos e estruturas ) e configuração ao simplesmente usar Spring Data que simplifica nossas operações de Database para as nossas aplicações.
## B. JPQL
##### a. Significa Java Persistence Query Language.
##### b. Ele é um padrão para fazer queries do JPA que nada tem a ver com objetos e classe de domínios que são escritos em queries sequenciais, diferentes de como são escritas para tabelas e colunas do database.
##### c. JPQL queries são escritas de maneira diferente de nossos objetos e seus campos.
##### d. Exemplo:
##### 1. Database functab:
|   funcid   |  pname  |    uname    |
| :---         |     :---:      |          ---: |
|   |      |    |
|      |       |      |


##### 2. Entidade Funcionario (definida com JPA) com os três campos que mapeiam as três colunas de functab:
|   Funcionario     | 
| :---         |
| int id;   |
| String pName  (P = primeiro nome)   |
| String uName  (U = ultimo nome)   |
| select * from Funcionario (SQL)|
| select pName,uName from Funcionario(JPQL)   |

##### e. No exemplo acima, as instruções JPQL estão convertendo os domínios das classes e seus campos, que são internamente convertidos pelo nosso ORM igual ao Hibernate no SQL.
##### f. Ele é convertido apropriadamente para o SQL correspondente ao mapeamento da entidade (Funcionario) e o da tabela(functab) do database.
##### g. É possível chamar os parâmetros passados dentro dessas queries.
##### h. Também pode-se utilizar select e nao-select operações como: inster, update, deletes usando JPQL.
##### i. Fazer uso de funções de agregação relacionais e Joins.
##### j. Muito do que há em SQL pode ser convertido e usado em palavras-chaves e síntaxe JPQL.
##### k. JPQL é case sensitive quando quando se trata de nomes de classe de domínio (Funcionario) e seu campos (pName e etc).
##### l. Contudo, JPQL não é case sensitive quando se trata de palavras-chave na própria sintaxe da linguagem como, por exemplo, se você estivesse usando funções agregadas como contagem.


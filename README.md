# SPRING_DATA_JPA_CONSULTAS_COM_REPOSITORYIMPL

## 1. JPA e Hibernate:
### — A. Uma Interface Repository ( Repositório Orientado a persistência ):
````
* a. Pertence ao Domínio da Aplicação:
* b. Não deve ter muitos detalhes técnicos
* c. Não informa nada sobre o mecanismo de persistência dos dados
* d. Por isso é colocada dentro de Domain
* e. É preciso criar uma classe para implementar esta classe.
* f. Por padrão, ele deve imitar uma Coleção: add, get e etc...
* g. Não se cria um repositório por tabela e/ou por entidade.
* h. Um repository é por agregado (conjunto de agregado/aggregate)
* i. Não criar repositórios para entidades que não são "aggregate root".
````

### — B. Pensando na Camada de Negócio de um Delivery de Comida: o que um repositório de cozinha tem que ter:
````
* a. Listar Cozinhas
* b. Buscar Cozinhas pelo ID
* c. Salvar uma Cozinha
* d. Remover uma Cozinha
* e. Poderia ter outros métodos específicos
* f. List<Kitchen> consultarPorNome(String nome): é uma nova assinatura de método
````

## 2. SPRING DATA JPA (SDJ) - cria uma implementação em tempo de execução
````
*  a. @Repository: recebe a anotação para persistir com o SDJ.
*  b. Uma interface tem que herdar de outra interface: JpaRepository<T, D>
*  c. <T, ID>: recebe dois argumentos de tipo, sendo o "T"(tipo da entidade) e o "ID"(tipo do ID dessa entidade).
````

## 3. Dependência:
````
    <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
````

## 3. JpaRepository herda, em sequência, os métodos de:
### i. PagingAndSortingRepository
### ii. CrudRepository
### iii. Repository (este não possui nenhum método)
### — Caso não queira herdar todos os métodos é só escolher um desses daí.

## Keywords Spring DAta JPA:
### Link: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation

## Prefixos que tem a mesma funcionalidade:
### find
### query
### read
### get
### stream

## META-INF - externalizando meta-informações para xml
### Criar o arquivo dentro dela: orm.xml
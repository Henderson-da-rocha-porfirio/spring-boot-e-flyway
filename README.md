# Flyway Com Postgresql
### *** Verificar as Versões em db/migration
### *** Dica importante ***
````
Sempre criar a tabela antes no PG Admin (ou outro que use) e verifique os acertos e erros. Ficando certo, copie
e cole no versionador do flyway. E delete a tabela antes de rodar no flyway.
````
## Pool de Conexões - Conjunto de Requisições Para a Melhoria da Performance
### 1. Aplicação Sem Pool de Conexões:
### - Ela não mantém um conjunto de conexões com o Database, apenas faz uma única conexão por vez, e com isso não tem nenhuma pronta para a reutilização com o Database.
### - Ela aumenta o tempo gasto na abertura e fechamento das conexões.

|   Requisição 1   |  Aplicação Web  |    Database    |
| :---:         |     :---:      |          :---: |
| HTTP    | realiza ações no database      | executa e encerra a conexão      |
| Feita do Consumidor Para ---->   | Cria uma nova conexão com ---->     | Depois que realiza o que precisa ser feito, ela é encerrada    |

### - A API atende as requisições e o database executa e encerra as conexões. Ou seja, depois da execução, ela não tem mais nenhuma conexão.
### - Sendo assim, a aplicação continua rodando mas não tem nenhuma conexão com o Database.


### 2. Aplicação Com Pool de Conexões:
### - É um componente de software(ou biblioteca) que mantém um conjunto de conexões com o Database, não importando quantas, prontas para a reutilização com o Database.
### - Ou seja, uma mesma conexão é usada diversas vezes em momentos diferentes reduzindo o tempo gasto na abertura e fechamento das conexões.
### - É necessário configurar no mínimo três conexões.
### - Quando estas três conexões não estão sendo usadas, elas ficam em estado de "Idle".
### - Idle: é uma conexão ociosa.
### - Três conexões ociosas são importantes porque a qualquer momento se pode precisar delas.
### - Nesse estado de Idle, as conexões nunca são fechadas. Elas ficam em espera e são usadas assim que requisitadas.
### - Tem uma configuração máxima de 5 conexões.
### - Neste caso, havendo mais de 5 requisições, elas ficarão na fila de espera de espera.
### - Assim que uma requisição for atendida, ela entra em estado de Idle e libera a fila para a próxima.
### - Depois de executar as requisições, as conexões entram em estado de Idle e durante um tempo, mantém as duas excedentes e depois as fecha e ficam com as 3 mínimas.

### 3. Hikari
### - A solução padrão de pool de conexões no Spring Boot
### 4. Configurando o pool de conexões do Hikari

### 5. Flyway - gerenciamento do versionamento do database.
### - Ele sabe qual a versão atual dos scripts.
### 6. Adicionando Flyway
### - Adicione através do starter do spring boot.
````
pom.xml -> Btn Direito em: spring-boot-starter-parent -> Generate
````
### - Depois do passo acima, será adicionada a dependência ao pom.xml:
````
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
````
### - Adicione os Plugins:
#### - Flyway Migration Creation
#### - JPA Buddy

### 7. Tipos de nomeação de versão para o Flyway
#### - V1, v2 e assim sucessivamente...
#### - V1.1, V1.2 e etc...
#### - V1_0, V2_1 e etc...
#### - V001, V003 e etc...
#### - V20220725140023
### Exemplo:
````
V001__primeira-migration.sql
````

### 8. Erro: Validate failed: Migration checksum mismatch for migration version 002
#### 1 Solução:
#### - Sempre parar o projeto (localhost) para poder não dar este erro.
#### - O erro se dá porque tentaram alterar o conteúdo ou o nome de um arquivo '.sql' que já havia sido criadopelo Flyway.
#### - Apagar o que tem dentro do '.sql' que deu problema e salvar.
#### - Criar uma nova versão com os dados corretos e
#### - Apagar o arquivo '.sql' aqui no Intellij e também deletar a linha correspondente no flyway_schema_history que está no database que não altera 'nada' no database.
#### 2 Por linha de comando se desejar: 
#### a. Criar em resources um arquivo flyway.properties com o conteúdo:
````
#MYSQL: flyway.url=jdbc:mysql://localhost/algafood?createDatabaseIfNotExist=true&serverTimezone=UTC
flyway.url=jdbc:postgresql://localhost:5432/dbfood
flyway.user= 'seu-usuario'
flyway.password= 'sua-senha'
````
#### b. tem que entrar na pasta do projeto pelo terminal e rodar:
````
./mvnw flyway:repair -Dflyway.configFiles=src/main/resources/flyway.properties
````
### 9. Inserção de massa de dados sempre renovada para teste no Flyway
#### - Atenção: não pode adicionar inserts em migrations
#### - Isso é possível ao se criar um arquivo que será chamado num callback do Flyway
##### - afterMigrate.sql:
````
resources/db/massdata/afterMigrate.sql
````
#### - Adicionar na application.properties:
````
spring.flyway.locations=classpath:db/migration,classpath:db/massdata
````

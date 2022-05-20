# REST - Representation State Transfer
## - Entendendo antes o que é uma API
## *** API (Application Programming Interface)
````
1. É um software que possui um conjunto de funções que intermediam o acesso as funcionalidades de algum sistema operacional, aplicação ou um serviço.
2. De maneira simples, é uma interface para acessar funcionalidades de um outro sistema.
````
## *** API: PROVEDOR
````
Expor uma função ou uma funcionalidade
````

## *** API: CONSUMIDOR
````
Consome as funções ou as funcionalidades
````

|   CODIGO   | API | SOFTWARE |
|   :---:     |   :---:     | :---:  |
| meu_codigo  | API Collections do Java |Implementação do Collections |
| :---:  | :---: |:---: |

## *** WEB SERVICES (ou WEB API's)
````
São API's WEB. Contudo, nem toda API é WEB.
````
|   CODIGO   | API | SOFTWARE WEB|
|   :---:     |   :---:     | :---:  |
| Disponibilizar Vagas  | API do GlassDoor |GlassDoor |
| :---:  | :---: |:---: |

## *** WEB SERVICES PODEM SER CONSUMIDOS DE VÁRIOS PROVEDORES
````
Aplicação de Agências de Viagens -> Consumindo:
API da CVC
API da Delta
API da Gol
````
## *** MAS O QUE É REST AFINAL?
### 1. É um modelo arquitetural
### 2. É uma especificação que define a forma de comunicação entre componentes de software na web independente da linguagem de programação usada.
### 3. É um estilo arquitetural de desenvolvimento de web services.
### 4. É uma API que segue as melhores práticas de Roy Fielding.

|   SISTEMA A (CLIENT) |     AÇÃO                  | SISTEMA B (SERVER)  |
|   :---:              |     :---:                 |  :---:              |
| CONSUMIDOR           |     REQUISIÇÃO ->         | PROVEDOR  (REST API)|
|   :---:              |     <- RESPOSTA           |  :---:              |

## *** Vantagens de usar Rest API?
### 1. Separação entre Client e Server
### 2. Melhora a escalabilidade
````
Consiste no quão bem um software (e a equipe responsável por ele) conseguem se adaptar para entregar um produto de igual qualidade a um número maior ou menor de clientes, ou em cenários inesperados.
````
### 3. Pode-se utilizar várias linguagens de programação.
### 4. Demanda do mercado. Ou seja, várias empresas fazem uso das API's umas das outras.
### 5. Vários serviços fazem uso de API's cada vez mais em seus sistemas e equipamentos.

## *** CONSTRAINTS - As melhores práticas em REST
### 1. O REST formaliza um conjunto de Constraints:
#### A. Client <-> Server:
##### i. Tem que evoluir separadamente sem qualquer dependência entre eles.
#### B. Stateless:
##### i. Requisição feita ao servidor deve conter tudo que seja necessário para que tudo seja devidamente processado.
##### ii. O servidor não mantém a sessão. Ou seja, o histórico de uso não permanece.
#### C. Cache:
##### i. Melhora a escalabilidade e performance da aplicação por reduzir o número de acessos(hits) no Server.
##### ii. Usar apenas quando necessário
#### D. Interface Uniforme:
##### i. Conjunto de operações bem definidas do sistema
##### ii. Identificação do que o sistema contém através de URI's
##### iii. Uso do protocolo HTTP
##### iv. Uso dos verbos do protocolo HTTP: GET, POST, PUT e DELETE
#### E. Sistema em Camadas:
##### i. Amplia a possibilidade de uso de outros servidores que ficam entre o Client <-> Server
##### ii. Dessa forma, estes servidores oferecem camadas de segurança, cache e etc.
##### iii. E isso não deve interferir entre a requisição(client) e a resposta(server).
#### F. Códigos sob Demanda:
##### i. Ela é opcional.
##### ii. O Server pode enviar como resposta de uma requisição algum código que deve ser executado no Client.
##### iii. Por exemplo, um Server pode retornar um Javascript para o Client que é responsável por montar um gráfico.

## *** REST VERSUS RESTful API
### 1. REST: É o estilo arquitetural que possui as Constraints. Isto é, a especificação.
### 2. RESTful API: É uma API desenvolvida em conformidade 100% com as Constraints.

## *** PROTOCOLO HTTP
### - COMPOSIÇÃO DA REQUISIÇÃO
````
 [MÉTODO][URI] HTTP/[VERSÃO]    ->         POST /produtos HTTP/1.1 
 
 [CABEÇALHOS]                   ->           Content-Type: application/json
										Accept: application/json|
										
										{ 
											"nome": "Notebook i5",\
 [CORPO/PAYLOAD]		        ->			"preco": 2100.0
										}
 						
````

### - COMPOSIÇÃO DA RESPOSTA
````
 [HTTP/[VERSÃO] [STATUS]   ->         HTTP/1.1 201 Created
 
 [CABEÇALHOS]              ->         Content-Type: application/json
																				
										{ 
											"codigo": 322,
											"nome": "Notebook i5",\
 [CORPO]		           ->			    "preco": 2100.0
										}
 						
````

## ***  REST  RESOURCES
### 1. Singleton Resource: Coleção de Produtos é Um Recurso
### 2. Identificando Recursos: URI ( Uniform Resource Identifier ): dá endereço aos recursos
### 3. URI vs URL:
````
URL ( Uniform Resource Locale ) = é um tipo de URI. Identifica o Identificador e também a Localização do recurso

````
### 4. A URI deve se referenciar a um substantivo ou alguma coisa e não a um verbo. Porque coisas possuem propriedades e verbos não possuem.
#### Errado:
````
/listarProdutos
````
#### Certo:
````
/produtos
````

### 5. É importante usar no plural: /produtos{codigo}
````
/produtos/223
````
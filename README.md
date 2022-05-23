# Collection Resource - Coleção de recursos e não recursos exclusivos

### *** RESOURCE:
````
Recurso é qualquer coisa exposta na WEB: documento, vídeo, imagem, produto e qualquer entidade no sistema
````
### *** RESOURCE URI e URL
````
1. URI: Para um recurso ser alcançado
2. URL: Para requisitar um recurso usando o protocolo HTTP
````
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

### *** REPRESENTAÇÃO DE RECURSOS
#### 1. JSON - é a representação mais utilizada em API's. Embaixo, um exemplo de uma coleção (lista)
##### /produtos
````
[
    { 
	    "codigo": 400,
	    "nome":"Notebook i5",
	    "preco":2100.0
    },
    { 
	    "codigo": 401,
	    "nome":"Notebook Acer",
	    "preco":2000
    },
]
````
#### - Isso é a representação do recurso e não o próprio. Recurso é o objeto.

#### 2. XML
##### /produtos
````
<products>
    <product>
        <codigo>400<codigo>
        <nome>Notebook i5</nome>
        <preco>2100.0<preco>
    </product>
     <product>
        <codigo>401<codigo>
        <nome>Notebook Dell</nome>
        <preco>2000<preco>
    </product>
</products>
````
#### 3. JPG

### *** REQUISIÇÃO
#### a. Exemplo: Client só aceita o formato json:
````
    GET /products HTTP/1.1 
	Accept: application/json
````

#### - Exemplo: Client só aceita o formato json(cabeçalho mediatype):
````
    GET /products HTTP/1.1 
	Accept: application/json
````
#### b. Exemplo: Client só aceita o formato xml(cabeçalho mediatype):
````
    GET /products HTTP/1.1 
	Accept: application/xml
````
#### - O formato dependerá do consumidor (Client).

### *** Configurando no Postman o cabeçalho mediatype:
````
Headers -> KEY -> Digitar Accept -> VALUE -> application/json ou application/xml ( de acordo com o Client )
````

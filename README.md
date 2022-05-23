# Singleton Resource
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

### 6. Bind (ligação)
````
/produtos/{produtosId}
````
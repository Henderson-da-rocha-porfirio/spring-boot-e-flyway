# Modelo de Maturidade de Richardson - RMM
## São 4 os níveis de maturidade de uma API
## Nível 3 - Glória do REST - 100% - Purista
````
HATEOAS - Hypertext As The Engine Of Application State
````
### - Todas as constraints são seguidas obrigatoriamente na api.
### - Chamado no popular de HyperMedia.
### - O propósito do HATEOAS:
````
1. Dizer o que pode ser feito como próximo passo após uma requisição de um recurso.
2. Qual a URI desse próximo passo.
````
### - HTML é uma linguagem HyperMedia (HATEOAS):
````
<html>
    <body>
        <a href="/produtos/thinkpad.html">ThinkPad 11</a> 
        <a href="/carrinho.html">Carrinho</a>
    </body>
</html>
````
### - Interligamento de Recursos (tipo teia (ing: web))
### - Na API ajuda os consumidores a descobrir as funcionalidades e o fluxo da navegação:
````
" / (Root Entry Point) " -> link produtos -> " /produtos " -> link produtos -> " /produtos{id} " -> link fornecedor -> " /fornecedor{id} " -> link fornecedores -> " /fornecedores "
````

### - EXEMPLO: 
#### REQUISIÇÃO 1:
````
GET /produtos/344
````
#### REQUISIÇÃO 2:
````
HTTP/1.1 200 OK
{
    "id": 344,
    "nome": "ThinkPad 11",
    "preco": 5000,
    "links": {
        "inativar": "pagamentos/43",
        "fornecedor": "fornecedores/54"
    }
}
````

## Nível 2 - Considerado REST pela grande maioria das empresas
````
VERBOS HTTP
````
### - Introduz o uso de verbos HTTP de acordo com a semântica de cada um.
### - A API usa os STATUS HTTP e os VERBOS HTTP de forma correta.

### - EXEMPLO 1 - VERBOS HTTP COM COLLECTION RESOURCE:
#### Requisição Cadastrar
````
POST /produtos HTTP/1.1
<produto>
<nome>ThinkPad 11</nome>
<preco>4500<preco>
</produto>
````

#### Requisição Alterar
````
PUT /produtos/344 HTTP/1.1
<produto>
<nome>ThinkPad 11</nome>
<preco>5000<preco>
<produto>
````

### - EXEMPLO 2 - STATUS:
#### Tipo de Resposta 1
````
{
    "id": 344,
    "nome": "ThinkPad 11",
    "preco": 5000
}
````

#### Tipo de Resposta 2
````
HTTP/1.1 404 Not Found
````

## Nível 1 - Não se considera REST
````
RECURSOS (RESOURCES)
````
### - A API Utiliza o HTTP apenas como mecanismo de transporte de dados.
### - O EndPoint é único.
### - A API Não usa os STATUS HTTP e nem os VERBOS HTTP de forma correta.
### - Já tem URI's que identificam Recursos (RESOURCES)
### - Existe o conceito de Recurso(resource), collection resource, mas não é aplicado corretamente ainda.

### - EXEMPLO:
#### Requisição Cadastrar
````
POST /produtos HTTP/1.1
<cadastrarProduto>
<nome>ThinkPad 11</nome>
<preco>4500<preco>
</cadastrarProduto>
````

#### Requisição Alterar
````
POST /produtos/344 HTTP/1.1
<alterarProduto>
<nome>ThinkPad 11</nome>
<preco>5000<preco>
</alterarProduto>
````

## Nível 0 - Nem se cogita se é REST. É rudimentar.
````
POX (PLAIN OLD XML)
````
### - A API Utiliza o HTTP apenas como mecanismo de transporte de dados.
### - A API Não usa os STATUS HTTP e nem os VERBOS HTTP de forma correta.
### - O EndPoint é único.

### - EXEMPLO:
#### Requisição Cadastrar
````
POST /servicoLoja HTTP/1.1
<cadastrarProduto>
<nome>ThinkPad 11</nome>
<preco>4500<preco>
</cadastrarProduto>
````

#### Requisição Alterar
````
POST /servicoLoja HTTP/1.1
<alterarProduto>
<codigo>112<codigo>
<nome>ThinkPad 11</nome>
<preco>5000<preco>
</alterarProduto>
````
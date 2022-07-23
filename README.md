# Eager Loading - carregamento ansioso/antecipado
## ToOne - Por padrão tudo que termina com "ToOne" usa a estratégia Eager Loading.
### 1. Toda vez que uma instância de Restaurant é carregada a partir do banco de dados, ele vai carregar as associações que usam Eager Loading, no caso da propriedade Kitchen que está com @ManyToOne dentro de Restaurant.
### 2. Está buscando 'ansiosasemente' uma Kitchen:
````
	@ManyToOne
    @JoinColumn(name = "kitchen_id", nullable = false)
    private Kitchen kitchen;
````
### 3. Associação Eager pode ser apenas um select ou não. Depende da implementação.
#### a. nullable = false:
##### — significa not null no database. Ou seja, se Kitchen é not null (não pode estar nulo), então não pode existir um Restaurant sem ter uma associação de Kitchen. E sempre existirá uma Kitchen para um Restaurant.
##### — certamente faz um inner join (junção entre duas chaves correspondentes) com muita segurança entre Restaurant e Kitchen.
#### a. nullable = false: ele faz um leftjoin devido City não ter um nullable = false. Porque ele põe por padrão um 'true'.
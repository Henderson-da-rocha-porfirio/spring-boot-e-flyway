# Data-Driven Development
## *** Domain-Driven Design ou Projeto Orientado a Domínio é um padrão de modelagem de software orientado a objetos que procura reforçar conceitos e boas práticas relacionadas à OO.

## *** Isso vem em contrapartida com o uso comum do Data-Driven Design ou Projeto Orientado a Dados, que a maioria dos desenvolvedores usa sem mesmo ter consciência disso.

### 1. Já ouvi várias vezes que os dados são a coisa mais importante em uma empresa, logo a modelagem deve sempre começar pensando no banco de dados.

### 2. Não é nada incomum desenvolvedores .Net, Java e C++ começarem um sistema estabelecendo os tipos que eles vão usar e o relacionamento entre eles. Esses tipos geralmente são objetos "burros", com getters e setters, representando nada mais, nada menos, que uma tabela do banco de dados.

### 3. O problema com essa abordagem é que ela não faz bom uso dos recursos da Orientação a Objetos. Muitos acham que getters e setters são o auge do encapsulamento, mas na prática esses métodos permitem ao usuário recuperar e alterar todos os atributos. Não há ganho algum, a não ser muito código desnecessário.

### 4. Enfim, muita gente acha que está usando OO, mas as classes poderiam ser facilmente substituídas por registros ou estruturas, de acordo com a linguagem utilizada.

### 5. A ideia inicial do DDD é voltar à uma modelagem OO mais pura, por assim dizer. Devemos esquecer de como os dados são persistidos e nos preocupar em como representar melhor as necessidades de negócio em classes e comportamentos (métodos).

### 6. Isso significa que em DDD um Cliente pode não ter um setter para os seus atributos comuns, mas pode ter métodos com lógica de negócio que neste domínio de negócio pertencem ao cliente, como void associarNovoCartao(Cartao) ou Conta recuperarInformacoesConta().

### 7. Em resumo, as classes modeladas e os seus métodos deveriam representar o negócio da empresa, usando inclusive a mesma nomenclatura. A persistência dos dados é colocada em segundo plano, sendo apenas uma camada complementar.

## *** Quando não usar DDD
### 1. Às vezes só é necessário um CRUD. DDD não é uma solução para tudo. A maioria dos sistemas possui uma boa parte composta por cadastros básicos (CRUD) e não seria adequado usar DDD para isso.

### 2. O DDD deve ajudar na modelagem das classes mais importantes e mais centrais do sistema de forma e diminuir a complexidade e ajudar na manutenção das mesmas, afinal este é o objetivo dos princípios de orientação a objetos.

## *** Compartilhando dados com outros sistemas
### 1. Rotinas de integração que recebem ou disponibilizam dados para outros sistemas não devem ser "inteligentes".

### 2. Muitos desenvolvedores acabam modelando suas classes de negócios tentando resolver as questões internas do sistema e, ao mesmo tempo, pensando em como essas classes serão expostas para outros sistemas.

### 3. Padrões como DTO (Data Transfer Object) que usam objetos "burros" são mais adequados para isso.

## *** Considerações finais
### 1. O DDD não tenta resolver todos os problemas de todas as camadas de um sistema.

### 2. Seu foco é na modelagem das entidades principais de negócio usando a linguagem adequada daquele domínio para facilitar a manutenção, extensão e entendimento.

### 3. Particularmente, eu não seguiria à risca o padrão, até porque existem inúmeros padrões e variações de modelagem OO. Estude os princípios por detrás desses padrões, pois eles são geralmente parecidos e veja o que funciona melhor para cada projeto.

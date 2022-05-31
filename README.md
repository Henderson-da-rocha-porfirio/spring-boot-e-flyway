# Domain Services
### 1. É uma operação sem estado que realiza uma tarefa específica do domínio.
### 2. É uma tarefa de negócio.
### 3. Quando um processo de domínio não é uma responsabilidade natural de uma entidade, é criado um serviço de domínio.
### 4. Não é interessante o Controller ter acesso direto ao repositório para fazer operações que modificam o estado da aplicação: salvar, excluir e etc.
### 5. Já o Controller ter acesso ao repositório ter acesso ao banco para fazer consulta (listar).
### 6. Por isso é interessante criar uma classe para isso, chamada de domain service.

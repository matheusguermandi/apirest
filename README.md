## API Rest de um cadastro de Pessoas, utilizando Spring Boot e Java

### Requisitos

- Possuir ao menos os endpoints: 
  - GET: Buscar uma única Pessoa
  - GET: Busca paginada com opção de filtro para retornar várias pessoas
  - POST: Cadastrar uma pessoa
  - PUT: Atualizar uma pessoa
  - DELETE: Excluir uma pessoa
- O cadastro de pessoa deve ter os campos: Id, Nome, CPF, Data de nascimento.
- A pessoa deve possuir uma lista de contatos com os campos: Id, Nome, Telefone e Email.
- Os dados devem ser persistidos utilizando um banco de dados relacional.

### Validações

- Todos os campos são obrigatórios, tanto da pessoa como do contato
- A Pessoa deve possuir ao menos um contato
- O CPF deve ser um CPF válido
- A Data de nascimento não pode ser uma data futura
- Validar sintaxe do email do contato

### Requisitos técnicos:

- Criar testes unitários
- Implementar um front-end para consumir a API.

----------------------------------------------------------------------
### Passo a passo para acessar/testar o frontend da aplicação
- É possivel ter acesso ao frontend acessando: 
  - https://apirestpessoas.netlify.app/
 
----------------------------------------------------------------------

### Passo a passo para acessar/testar o backend da aplicação
- É possivel ter uma visão geral dos métodos da API a partir da seguinte documentação
  - https://apirestpessoas.herokuapp.com/swagger-ui.html
- Para realizar as requisições, basta clicar no botão abaixo que todas as rotas vão ser importadas para o Insomnia

[![Run in Insomnia}](https://insomnia.rest/images/run.svg)](https://insomnia.rest/run/?label=APIREST%20-%20Spring%20Boot&uri=https%3A%2F%2Fraw.githubusercontent.com%2Fmatheusguermandi%2Fapirest%2Fmain%2FInsomnia_2021-05-03.json)


 


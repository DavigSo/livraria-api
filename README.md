Esse projeto, na verdade é um exercício do curso de Spring e Angular - Wellington Araujo do youtube, o objetivo inicial era se desafiar e fazer um CRUD de uma livraria com os ensinamentos aprendidos no curso e buscar mais!

# Livraria API

## Descrição

A API da Livraria é uma aplicação Spring Boot que fornece operações CRUD (Criar, Ler, Atualizar e Deletar) para gerenciar livros em uma livraria. A aplicação usa armazenamento em memória para simular a persistência de dados, ideal para desenvolvimento e testes.

## Funcionalidades

- **Criar Livro**: Adiciona um novo livro à lista.
- **Listar Livros**: Retorna todos os livros.
- **Obter Livro por ID**: Retorna um livro específico pelo seu ID.
- **Atualizar Livro**: Atualiza um livro existente.
- **Excluir Livro**: Remove um livro da lista.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Java**: Linguagem de programação utilizada.
- **Maven**: Gerenciador de dependências e construção do projeto.

## Estrutura do Projeto

- **`src/main/java/com/exemplo/livraria`**: Código fonte da aplicação.
  - **`controller`**: Contém o controlador da API.
  - **`model`**: Contém a classe `Livro`, que representa a entidade de livro.
  - **`service`**: Contém o serviço `LivroService`, que gerencia a lógica de negócios e armazenamento em memória.
- **`src/main/resources`**: Arquivos de configuração e recursos.
  - **`application.properties`**: Arquivo de configuração do Spring Boot.
- **`pom.xml`**: Arquivo de configuração do Maven.

## Configuração e Execução

### Pré-requisitos

Certifique-se de ter o Java 11 ou superior e o Maven instalados na sua máquina.

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/DavigSo/livraria-api.git
   ```

2. Navegue para o diretório do projeto:
   ```bash
   cd livraria-api
   ```

3. Compile e execute a aplicação usando Maven:
   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080/livros`.

## Endpoints da API

- **Criar Livro**
  - **Método**: POST
  - **Endpoint**: `/livros`
  - **Body**:
    ```json
    {
      "titulo": "Título do Livro",
      "autor": "Autor do Livro",
      "editora": "Editora do Livro",
      "ano": 2024
    }
    ```

- **Listar Livros**
  - **Método**: GET
  - **Endpoint**: `/livros`

- **Obter Livro por ID**
  - **Método**: GET
  - **Endpoint**: `/livros/{id}`

- **Atualizar Livro**
  - **Método**: PUT
  - **Endpoint**: `/livros/{id}`
  - **Body**:
    ```json
    {
      "titulo": "Título Atualizado",
      "autor": "Autor Atualizado",
      "editora": "Editora Atualizada",
      "ano": 2024
    }
    ```

- **Excluir Livro**
  - **Método**: DELETE
  - **Endpoint**: `/livros/{id}`


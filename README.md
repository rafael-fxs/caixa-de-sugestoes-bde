# Caixa de Sugestões
- Aluno: Rafael Felipe Xavier da Silva
- Curso: Sistemas de Informação
- Matéria: Banco de Dados Evolucionários (Turma 6º U)
## Descrição do Projeto

O projeto **Caixa de Sugestões** é uma aplicação web que permite aos usuários enviar sugestões sobre produtos e serviços. Os usuários podem interagir com as sugestões, curtindo ou removendo curtidas, promovendo um ambiente colaborativo e de feedback. O sistema visa oferecer recompensas para sugestões populares no futuro.

### Funcionalidades Principais:
- **Envio de Sugestões**: Usuários podem enviar sugestões com descrição e categoria.
- **Curtidas**: Outros usuários podem curtir ou descurtir sugestões, aumentando o engajamento.
- **Ranking**: Listagem das três sugestões mais curtidas.
- **Gerenciamento de Usuários**: Cadastro e consulta de usuários.
- **Deleção de Sugestões**: Possibilidade de remover sugestões.

## Tecnologias Utilizadas

- **Backend**: Java 17 com Spring Boot
- **Banco de Dados**: PostgreSQL
- **Migrações**: Flyway
- **Documentação da API**: Swagger
- **Containerização**: Docker Compose

## Como Rodar o Projeto

### Pré-requisitos

- **Docker**: Instalado na máquina para rodar o banco de dados em um container.
- **Java 17**: Instalado.

### Configuração do Backend

1. **Configuração do Banco de Dados (Docker Compose)**:
   - Navegue até a pasta `src/main/infra` e execute o seguinte comando no terminal para subir o container PostgreSQL:
     ```bash
     docker-compose up
     ```

2. **Rodar a Aplicação Spring Boot**:
   - Acesse o projeto `CaixaDeSugestoesApplication` e inicie-o.

3. **Acessar o Swagger**:
   - Após iniciar a aplicação, o Swagger estará disponível em `http://localhost:8080/swagger-ui.html` para testar as APIs.

### Configuração do Frontend

1. **Utilizar Live Server**:
   - Instale a extensão **Live Server** no Visual Studio Code.
   - Abra o projeto frontend e inicie o Live Server. Normalmente, a aplicação será servida em `http://127.0.0.1:5500/`.

2. **Conexão com o Backend**:
   - As requisições da aplicação frontend serão direcionadas para o backend em `http://localhost:8080`.

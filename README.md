# API Odontológica - Backend

## Descrição

Este é o repositório do backend da aplicação API Odontológica, desenvolvido em Java com Spring Boot. A API fornece serviços para gerenciar agendamentos, pacientes, dentistas e salas de uma clínica odontológica. 

## Funcionalidades

- **Gerenciamento de Agendamentos:** Endpoints para criar, editar, listar e excluir agendamentos.
- **Gerenciamento de Pacientes:** Endpoints para adicionar, atualizar, listar e remover pacientes.
- **Gerenciamento de Dentistas:** Endpoints para administrar os dados dos dentistas.
- **Gerenciamento de Salas:** Endpoints para listar e gerenciar salas de atendimento.

## Requisitos

- Java 11 ou superior
- Maven 3.6.0 ou superior
- Banco de dados MySQL

## Instalação e Configuração

1. **Clone o repositório:**

    ```bash
    git clone https://github.com/usuario/apiodontologica-backend.git
    ```

2. **Navegue até o diretório do projeto:**

    ```bash
    cd apiodontologica-backend
    ```

3. **Configure o Banco de Dados:**

   Crie um banco de dados MySQL para a aplicação. No arquivo `src/main/resources/application.properties`, atualize as configurações do banco de dados:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/apiodontologica
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    ```

4. **Compile e execute a aplicação:**

    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

    A aplicação será iniciada na porta 8080. Acesse `http://localhost:8080/api` para verificar se o backend está funcionando corretamente.

## Estrutura do Projeto

- `src/main/java/com/apiodontologica/` - Contém os arquivos fonte da aplicação.
- `src/main/resources/` - Contém os arquivos de configuração, incluindo `application.properties`.
- `src/test/java/com/apiodontologica/` - Contém os testes unitários e de integração da aplicação.

## Endpoints Principais

### Agendamentos

- `GET /api/agendamentos` - Lista todos os agendamentos.
- `POST /api/agendamentos` - Cria um novo agendamento.
- `PUT /api/agendamentos/{id}` - Atualiza um agendamento existente.
- `DELETE /api/agendamentos/{id}` - Exclui um agendamento.

### Pacientes

- `GET /api/pacientes` - Lista todos os pacientes.
- `POST /api/pacientes` - Adiciona um novo paciente.
- `PUT /api/pacientes/{id}` - Atualiza os dados de um paciente.
- `DELETE /api/pacientes/{id}` - Remove um paciente.

### Dentistas

- `GET /api/dentistas` - Lista todos os dentistas.
- `POST /api/dentistas` - Adiciona um novo dentista.
- `PUT /api/dentistas/{id}` - Atualiza os dados de um dentista.
- `DELETE /api/dentistas/{id}` - Remove um dentista.

### Salas

- `GET /api/salas` - Lista todas as salas.
- `POST /api/salas` - Adiciona uma nova sala.
- `PUT /api/salas/{id}` - Atualiza os dados de uma sala.
- `DELETE /api/salas/{id}` - Remove uma sala.

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para mais detalhes.

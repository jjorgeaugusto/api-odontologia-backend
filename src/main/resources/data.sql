CREATE DATABSE IF NOT EXISTS apiodontologica;

USE apiodontologica;


    CREATE TABLE IF NOT EXISTS pacientes(
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        email VARCHAR(255),
        telefone VARCHAR()
    );

    CREATE TABLE IF NOT EXISTS dentista(
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
        especialidade VARCHAR(255) NOT NULL
    );

    CREATE TABLE IF NOT EXISTS sala(
        id INT AUTO_INCREMENT PRIMARY KEY,
        nome VARCHAR(255) NOT NULL,
    );
CREATE TABLE IF NOT EXISTS agendamento (
   id INT AUTO_INCREMENT PRIMARY KEY,
   paciente_id INT NOT NULL,
   dentista_id INT NOT NULL,
   sala_id INT NOT NULL,
   data_hora DATETIME NOT NULL,
   status ENUM('a confirmar', 'confirmado', 'cancelado') DEFAULT 'a confirmar',
    FOREIGN KEY (paciente_id) REFERENCES paciente(id) ON DELETE CASCADE,
    FOREIGN KEY (dentista_id) REFERENCES dentista(id) ON DELETE CASCADE,
    FOREIGN KEY (sala_id) REFERENCES sala(id) ON DELETE CASCADE
    );

-- Inserção de dados iniciais na tabela 'paciente'
INSERT INTO paciente (nome, email, telefone) VALUES
   ('João Silva', 'joao.silva@example.com', '(11) 98765-4321'),
   ('Maria Souza', 'maria.souza@example.com', '(21) 98765-4321');

-- Inserção de dados iniciais na tabela 'dentista'
INSERT INTO dentista (nome, especialidade) VALUES
  ('Dr. Carlos Pereira', 'Ortodontia'),
  ('Dra. Fernanda Almeida', 'Endodontia');

-- Inserção de dados iniciais na tabela 'sala'
INSERT INTO sala (nome) VALUES
   ('Sala 1'),
   ('Sala 2');

-- Inserção de um agendamento inicial
INSERT INTO agendamento (paciente_id, dentista_id, sala_id, data_hora, status) VALUES
    (1, 1, 1, '2024-09-10 14:00:00', 'confirmado');
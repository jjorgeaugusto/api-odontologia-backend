package api.agenda.odontologia.repository;

import api.agenda.odontologia.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    boolean existsByNome(String nome);
    boolean existsByTelefone(String telefone);
}

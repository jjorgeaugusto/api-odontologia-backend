package api.agenda.odontologia.repository;

import api.agenda.odontologia.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataHora(LocalDateTime dataHora);

    boolean existsByPacienteId(Long id);
}

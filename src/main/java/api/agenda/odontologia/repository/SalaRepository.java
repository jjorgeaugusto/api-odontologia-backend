package api.agenda.odontologia.repository;

import api.agenda.odontologia.model.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaRepository extends JpaRepository<Sala, Long> {
}

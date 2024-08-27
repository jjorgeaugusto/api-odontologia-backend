package api.agenda.odontologia.repository;

import api.agenda.odontologia.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
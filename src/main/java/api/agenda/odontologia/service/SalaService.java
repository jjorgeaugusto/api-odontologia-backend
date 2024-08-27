package api.agenda.odontologia.service;

import api.agenda.odontologia.dto.SalaDTO;
import api.agenda.odontologia.model.Sala;
import api.agenda.odontologia.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalaService {

    @Autowired
    private SalaRepository salaRepository;

    public List<SalaDTO> findAll() {
        return salaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalaDTO save(SalaDTO salaDTO) {
        Sala sala = convertToEntity(salaDTO);
        Sala savedSala = salaRepository.save(sala);
        return convertToDTO(savedSala);
    }

    public void delete(Long id) {
        salaRepository.deleteById(id);
    }

    private SalaDTO convertToDTO(Sala sala) {
        return new SalaDTO(sala.getId(), sala.getNome());
    }

    private Sala convertToEntity(SalaDTO salaDTO) {
        Sala sala = new Sala();
        sala.setId(salaDTO.getId());
        sala.setNome(salaDTO.getNome());
        return sala;
    }
}

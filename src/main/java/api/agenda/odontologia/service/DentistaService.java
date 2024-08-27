package api.agenda.odontologia.service;

import api.agenda.odontologia.dto.DentistaDTO;
import api.agenda.odontologia.model.Dentista;
import api.agenda.odontologia.repository.DentistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistaService {

    @Autowired
    private DentistaRepository dentistaRepository;

    public List<DentistaDTO> findAllDentistas() {
        List<Dentista> dentistas = dentistaRepository.findAll();
        return dentistas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public DentistaDTO findById(Long id) {
        Dentista dentista = dentistaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        return convertToDTO(dentista);
    }

    public DentistaDTO save(DentistaDTO dentistaDTO) {
        Dentista dentista = convertToEntity(dentistaDTO);
        Dentista savedDentista = dentistaRepository.save(dentista);
        return convertToDTO(savedDentista);
    }

    public void deleteById(Long id) {
        dentistaRepository.deleteById(id);
    }

    private DentistaDTO convertToDTO(Dentista dentista) {
        DentistaDTO dto = new DentistaDTO();
        dto.setId(dentista.getId());
        dto.setNome(dentista.getNome());
        dto.setEspecialidade(dentista.getEspecialidade());
        return dto;
    }

    private Dentista convertToEntity(DentistaDTO dto) {
        Dentista dentista = new Dentista();
        dentista.setId(dto.getId());
        dentista.setNome(dto.getNome());
        dentista.setEspecialidade(dto.getEspecialidade());
        return dentista;
    }
}
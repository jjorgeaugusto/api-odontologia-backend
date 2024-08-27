package api.agenda.odontologia.service;
import api.agenda.odontologia.repository.*;
import api.agenda.odontologia.dto.PacienteDTO;
import api.agenda.odontologia.model.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<PacienteDTO> findAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public PacienteDTO findById(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        return convertToDTO(paciente);
    }

    public String save(PacienteDTO pacienteDTO) {
        String validationMessage = validatePacienteUniqueness(pacienteDTO);
        if (validationMessage != null) {
            return validationMessage;
        }
        Paciente paciente = convertToEntity(pacienteDTO);
        pacienteRepository.save(paciente);
        return "Paciente cadastrado com sucesso!";
    }
    public String deleteById(Long id) {
        if (agendamentoRepository.existsByPacienteId(id)) {
            return "Erro: Não é possível excluir o paciente, pois ele possui agendamentos relacionados.";
        }
        pacienteRepository.deleteById(id);
        return "Paciente excluído com sucesso.";
    }
    private String validatePacienteUniqueness(PacienteDTO pacienteDTO) {
        boolean existsByNome = pacienteRepository.existsByNome(pacienteDTO.getNome());
        boolean existsByTelefone = pacienteRepository.existsByTelefone(pacienteDTO.getTelefone());

        if (existsByNome) {
            return "Já existe um paciente com este nome.";
        }

        if (existsByTelefone) {
            return "Já existe um paciente com este número de telefone.";
        }

        return null;
    }

    private PacienteDTO convertToDTO(Paciente paciente) {
        PacienteDTO dto = new PacienteDTO();
        dto.setId(paciente.getId());
        dto.setNome(paciente.getNome());
        dto.setTelefone(paciente.getTelefone());
        dto.setEmail(paciente.getEmail());
        return dto;
    }

    private Paciente convertToEntity(PacienteDTO dto) {
        Paciente paciente = new Paciente();
        paciente.setId(dto.getId());
        paciente.setNome(dto.getNome());
        paciente.setTelefone(dto.getTelefone());
        paciente.setEmail(dto.getEmail());
        return paciente;
    }

    public PacienteDTO update(Long id, PacienteDTO pacienteDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);

        if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            paciente.setNome(pacienteDTO.getNome());
            paciente.setTelefone(pacienteDTO.getTelefone());
            paciente.setEmail(pacienteDTO.getEmail());
            Paciente pacienteAtualizado = pacienteRepository.save(paciente);
            return new PacienteDTO(pacienteAtualizado); // Converte para DTO antes de retornar
        } else {
            throw new RuntimeException("Paciente não encontrado");
        }
    }
}
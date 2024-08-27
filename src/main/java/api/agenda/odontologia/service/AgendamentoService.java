package api.agenda.odontologia.service;
import api.agenda.odontologia.dto.DentistaDTO;
import api.agenda.odontologia.dto.SalaDTO;
import api.agenda.odontologia.exception.ResourceNotFoundException;

import api.agenda.odontologia.dto.AgendamentoDTO;
import api.agenda.odontologia.dto.PacienteDTO;
import api.agenda.odontologia.model.Agendamento;
import api.agenda.odontologia.model.Dentista;
import api.agenda.odontologia.model.Paciente;
import api.agenda.odontologia.model.Sala;
import api.agenda.odontologia.repository.AgendamentoRepository;
import api.agenda.odontologia.repository.DentistaRepository;
import api.agenda.odontologia.repository.PacienteRepository;
import api.agenda.odontologia.repository.SalaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private DentistaRepository dentistaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Transactional
    public String updateAgendamento(Long id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamentoExistente = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));

        // Buscar entidades relacionadas
        Paciente paciente = pacienteRepository.findById(agendamentoDTO.getPaciente().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado com ID: " + agendamentoDTO.getPaciente().getId()));
        Dentista dentista = dentistaRepository.findById(agendamentoDTO.getDentista().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado com ID: " + agendamentoDTO.getDentista().getId()));
        Sala sala = salaRepository.findById(agendamentoDTO.getSala().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sala não encontrada com ID: " + agendamentoDTO.getSala().getId()));

        // Atualizar o agendamento
        agendamentoExistente.setPaciente(paciente);
        agendamentoExistente.setDentista(dentista);
        agendamentoExistente.setSala(sala);
        agendamentoExistente.setDataHora(agendamentoDTO.getDataHora());
        agendamentoExistente.setStatus(agendamentoDTO.getStatus());

        agendamentoRepository.save(agendamentoExistente);
        return "Agendamento atualizado com sucesso!";
    }

    public List<AgendamentoDTO> findAllAgendamentos() {
        List<Agendamento> agendamentos = agendamentoRepository.findAll();
        return agendamentos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    // Pesquisa ID do agendamento
    public AgendamentoDTO findById(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));
        return convertToDTO(agendamento);
    }

    public String save(AgendamentoDTO agendamentoDTO) {
        if (isScheduleConflict(agendamentoDTO.getDataHora())) {
            return "Já existe uma consulta agendada para esta data e horário.";
        }

        Agendamento agendamento = convertToEntity(agendamentoDTO);
        agendamentoRepository.save(agendamento);
        return "Consulta agendada com sucesso!";
    }

    public void deleteById(Long id) {
        agendamentoRepository.deleteById(id);
    }

    private boolean isScheduleConflict(LocalDateTime dataHora) {
        return agendamentoRepository.existsByDataHora(dataHora);
    }

    private AgendamentoDTO convertToDTO(Agendamento agendamento) {
        AgendamentoDTO dto = new AgendamentoDTO();
        dto.setId(agendamento.getId());

        // Convertendo Paciente para PacienteDTO
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(agendamento.getPaciente().getId());
        pacienteDTO.setNome(agendamento.getPaciente().getNome());
        pacienteDTO.setTelefone(agendamento.getPaciente().getTelefone());
        pacienteDTO.setEmail(agendamento.getPaciente().getEmail());
        dto.setPaciente(pacienteDTO);

        // Convertendo Dentista para DentistaDTO
        DentistaDTO dentistaDTO = new DentistaDTO();
        dentistaDTO.setId(agendamento.getDentista().getId());
        dentistaDTO.setNome(agendamento.getDentista().getNome());
        dentistaDTO.setEspecialidade(agendamento.getDentista().getEspecialidade());
        dto.setDentista(dentistaDTO);

        // Convertendo Sala para SalaDTO
        SalaDTO salaDTO = new SalaDTO();
        salaDTO.setId(agendamento.getSala().getId());
        salaDTO.setNome(agendamento.getSala().getNome());
        dto.setSala(salaDTO);

        dto.setDataHora(agendamento.getDataHora());
        dto.setStatus(agendamento.getStatus());

        return dto;
    }
    private Agendamento convertToEntity(AgendamentoDTO dto) {
        Agendamento agendamento = new Agendamento();
        agendamento.setId(dto.getId());

        // Verifica se o paciente existe
        Paciente paciente = pacienteRepository.findById(dto.getPaciente().getId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));
        agendamento.setPaciente(paciente);

        // Verifica se o dentista existe
        Dentista dentista = dentistaRepository.findById(dto.getDentista().getId())
                .orElseThrow(() -> new RuntimeException("Dentista não encontrado"));
        agendamento.setDentista(dentista);

        // Verifica se a sala existe
        Sala sala = salaRepository.findById(dto.getSala().getId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));
        agendamento.setSala(sala);

        agendamento.setDataHora(dto.getDataHora());
        agendamento.setStatus(dto.getStatus());

        return agendamento;
    }
    public void updateStatus(Long id, String status) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Agendamento não encontrado com ID: " + id));
        agendamento.setStatus(status);
        agendamentoRepository.save(agendamento);
    }
}

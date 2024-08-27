package api.agenda.odontologia.controller;

import api.agenda.odontologia.dto.AgendamentoDTO;import api.agenda.odontologia.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
@CrossOrigin(origins = "http://localhost:53385")  // Endereço da porta do Flutter
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public List<AgendamentoDTO> getAllAgendamentos() {
        return agendamentoService.findAllAgendamentos();
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> getAgendamentoById(@PathVariable Long id) {
        AgendamentoDTO agendamento = agendamentoService.findById(id);
        return ResponseEntity.ok(agendamento);
    }

    @PostMapping
    public ResponseEntity<String> createAgendamento(@RequestBody AgendamentoDTO agendamentoDTO) {
        String result = agendamentoService.save(agendamentoDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAgendamento(@PathVariable Long id, @RequestBody AgendamentoDTO agendamentoDTO) {
        String result = agendamentoService.updateAgendamento(id, agendamentoDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAgendamento(@PathVariable Long id) {
        agendamentoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}/confirmar")
    public ResponseEntity<String> confirmarAgendamento(@PathVariable Long id) {
        agendamentoService.updateStatus(id, "Confirmado");
        return ResponseEntity.ok("Agendamento confirmado");
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<String> cancelarAgendamento(@PathVariable Long id) {
        agendamentoService.updateStatus(id, "Cancelado");
        return ResponseEntity.ok("Agendamento cancelado");
    }

}
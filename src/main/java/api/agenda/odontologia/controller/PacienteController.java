package api.agenda.odontologia.controller;

import api.agenda.odontologia.dto.*;
import api.agenda.odontologia.model.Paciente;
import api.agenda.odontologia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<PacienteDTO> getAllPacientes() {
        return pacienteService.findAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.findById(id);
        return ResponseEntity.ok(paciente);
    }

    @PostMapping
    public ResponseEntity<String> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        String result = pacienteService.save(pacienteDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(
            @PathVariable Long id,
            @RequestBody PacienteDTO pacienteDTO
    ) {
        PacienteDTO pacienteAtualizado = pacienteService.update(id, pacienteDTO);
        return ResponseEntity.ok(pacienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePaciente(@PathVariable Long id) {
        String response = pacienteService.deleteById(id);
        if (response.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
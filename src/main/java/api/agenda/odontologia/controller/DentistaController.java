package api.agenda.odontologia.controller;

import api.agenda.odontologia.dto.DentistaDTO;
import api.agenda.odontologia.model.Dentista;
import api.agenda.odontologia.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentistas")
public class DentistaController {

    @Autowired
    private DentistaService dentistaService;

    @GetMapping
    public ResponseEntity<List<DentistaDTO>> getAllDentistas() {
        List<DentistaDTO> dentistas = dentistaService.findAllDentistas();
        return ResponseEntity.ok(dentistas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> getDentistaById(@PathVariable Long id) {
        DentistaDTO dentista = dentistaService.findById(id);
        return ResponseEntity.ok(dentista);
    }

    @PostMapping
    public ResponseEntity<DentistaDTO> createDentista(@RequestBody DentistaDTO dentistaDTO) {
        DentistaDTO savedDentista = dentistaService.save(dentistaDTO);
        return ResponseEntity.ok(savedDentista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaDTO> updateDentista(@PathVariable Long id, @RequestBody DentistaDTO dentistaDTO) {
        dentistaDTO.setId(id);
        DentistaDTO updatedDentista = dentistaService.save(dentistaDTO);
        return ResponseEntity.ok(updatedDentista);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentista(@PathVariable Long id) {
        dentistaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
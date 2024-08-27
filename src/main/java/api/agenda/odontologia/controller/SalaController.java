package api.agenda.odontologia.controller;

import api.agenda.odontologia.dto.SalaDTO;
import api.agenda.odontologia.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salas")
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    public List<SalaDTO> getAllSalas() {
        return salaService.findAll();
    }

    @PostMapping
    public SalaDTO createSala(@RequestBody SalaDTO salaDTO) {
        return salaService.save(salaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSala(@PathVariable Long id) {
        salaService.delete(id);
    }
}

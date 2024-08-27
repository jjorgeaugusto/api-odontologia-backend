package api.agenda.odontologia.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AgendamentoDTO {

    private Long id;
    private PacienteDTO paciente;
    private DentistaDTO dentista;
    private SalaDTO sala;
    private LocalDateTime dataHora;
    private String status;

}


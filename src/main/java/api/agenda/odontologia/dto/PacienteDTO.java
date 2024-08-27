package api.agenda.odontologia.dto;

import api.agenda.odontologia.model.Paciente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;

    public PacienteDTO() {
    }

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.telefone = paciente.getTelefone();
        this.email = paciente.getEmail();
    }

    public Paciente toEntity() {
        Paciente paciente = new Paciente();
        paciente.setId(this.id);
        paciente.setNome(this.nome);
        paciente.setTelefone(this.telefone);
        paciente.setEmail(this.email);
        return paciente;
    }
}

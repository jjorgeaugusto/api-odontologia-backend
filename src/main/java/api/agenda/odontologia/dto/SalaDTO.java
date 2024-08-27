package api.agenda.odontologia.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SalaDTO {
    // Getters e Setters
    private Long id;
    private String nome;

    public SalaDTO() {}

    public SalaDTO(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}

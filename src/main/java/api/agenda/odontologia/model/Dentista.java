package api.agenda.odontologia.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Dentista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String especialidade;

    @OneToMany(mappedBy = "dentista")
    private List<Agendamento> agendamentos;
}

package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "area")
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtipoarea;
    @Column(name = "descripcion")
    private String descripcion;
}

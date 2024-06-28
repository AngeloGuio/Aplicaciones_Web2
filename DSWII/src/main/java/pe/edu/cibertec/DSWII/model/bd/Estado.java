package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "estado")
public class Estado {
    @Id
    private Boolean codestado;
    @Column(name = "nombreestado")
    private String nombreestado;
}

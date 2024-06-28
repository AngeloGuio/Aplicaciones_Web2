package pe.edu.cibertec.DSWII.model.bd;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipoproducto")
public class TipoProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer idtipopro;

    @Column(name = "desctipopro")
    private  String desctipopro;

}

package pe.edu.cibertec.DSWII.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegistroVentaDto {
    private VentaDto ventaDto;
    private ClienteDto clienteDto;
    private DetallePagoDto detallePagoDto;
}


package pe.edu.cibertec.DSWII.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.DSWII.model.dto.ClienteDto;
import pe.edu.cibertec.DSWII.model.dto.RegistroVentaDto;
import pe.edu.cibertec.DSWII.model.dto.VentaDto;
import pe.edu.cibertec.DSWII.model.response.ApiResponse;
import pe.edu.cibertec.DSWII.service.VentaService;

@AllArgsConstructor
@RestController
@RequestMapping(path = "api/v1/venta")
public class VentaController {

    private final VentaService ventaService;

    @PostMapping("/registrar")
    public ResponseEntity<ApiResponse> registrarVenta(@RequestBody RegistroVentaDto registroVentaDto) {
        try {
            VentaDto nuevaVenta = ventaService.registrarVentaConClienteNuevo(
                    registroVentaDto.getVentaDto(),
                    registroVentaDto.getClienteDto(),
                    registroVentaDto.getDetallePagoDto() // Asegúrate de que RegistroVentaDto contenga este campo
            );
            return ResponseEntity.ok(new ApiResponse(true, "Venta registrada exitosamente"));
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, "Error al registrar la venta: " + e.getMessage()));
        }
    }


}

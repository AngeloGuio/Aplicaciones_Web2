package pe.edu.cibertec.DSWII.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pe.edu.cibertec.DSWII.Model.Dto.MensajeErrorDto;

import java.util.Date;

@RestControllerAdvice
public class ControllerExceptionHandler  {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public MensajeErrorDto resourceNotFoundException(ResourceNotFoundException ex, WebRequest webRequest){
        return  MensajeErrorDto.builder().mensaje(ex.getMessage()).codigoestado(HttpStatus.NOT_FOUND.value()).fechaerror(new Date())
                .descripcion(webRequest.getDescription(true)).build();


    }
}

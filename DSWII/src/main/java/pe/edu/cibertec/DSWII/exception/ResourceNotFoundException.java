package pe.edu.cibertec.DSWII.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}

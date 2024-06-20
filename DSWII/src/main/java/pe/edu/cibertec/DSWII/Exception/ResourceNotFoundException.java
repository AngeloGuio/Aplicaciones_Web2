package pe.edu.cibertec.DSWII.Exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}

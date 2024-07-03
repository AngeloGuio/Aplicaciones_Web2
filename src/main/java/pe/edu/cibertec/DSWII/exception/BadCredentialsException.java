package pe.edu.cibertec.DSWII.exception;

public class BadCredentialsException extends RuntimeException  {
    public BadCredentialsException(String message){
        super(message);
    }
}

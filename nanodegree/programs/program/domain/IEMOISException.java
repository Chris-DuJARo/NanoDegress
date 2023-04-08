package domain;

public class IEMOISException extends Exception{   
    public static String WEEKS_EMPTY;
    public static String WEEKS_ERROR;
    public static String NANO_EMPTY;
    public static String IMPOSSIBLE;
    
    public IEMOISException(String mensaje){
        super(mensaje);
    }
}

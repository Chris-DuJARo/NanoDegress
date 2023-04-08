package domain;

public abstract class Program{
    
    protected String name;
    
    /**
     * Return the name
     * @return
     */
    public String name(){
        return name;
    }

    
    /**
     * Return weeks
     * @return
     * @throws IEMOISException, if the time is not available or has an error
     */
    public abstract int weeks() throws IEMOISException;
    
    
    /**
     * Return the representation as string
     * @return
     * @throws IEMOISException, if the data is not complete
     */    
    public abstract String data() throws IEMOISException;

}

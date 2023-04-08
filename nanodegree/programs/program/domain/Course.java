package domain;  

public class Course extends Program{
    
    protected Integer weeks;
    
    public Course(String name, Integer weeks){
        this.name=name;
        this.weeks=weeks;
    }    

    /**
     * Get the number of weeks that has the course.
     * @throws IEMOISException WEEKS_EMPTY, if the week cannot be known. WEEKS_ERROR, if the weeks is less than one.
     * @return weeks.
     */
    @Override
    public int weeks() throws IEMOISException{
       if (weeks == null) throw new IEMOISException(IEMOISException.WEEKS_EMPTY);
       if (weeks < 1) throw new IEMOISException(IEMOISException.WEEKS_ERROR);
       return weeks;
    }
    
    /**
     * Get the weeks of the course 
     * No matter if does not has weeks
     * @return weeks 
     */
    public int getWeeks(){
        return weeks;
    }
    
    
    /**
     * Get the number of weeks at the course.
     * If it is null then return the prom give it
     * @param type. If the week is null we assumed like the max, min or avg.
     * @return weeks.
     */
    public int weeks(int type){
        if (weeks.equals(null)){
            return type;
        }else{
            return weeks;
        }
    }
    
    @Override
    public String data(){
        return name+". Semanas:" +weeks;
    }
}

package domain;  
 
import java.util.ArrayList;

public class Nanodegree extends Program{
   
    private int projectWeeks;    
    private ArrayList<Course> courses;
    
    /**
     * Constructs a new nanodegree
     * @param name 
     * @param discount 
     */
    public Nanodegree(String name, int projectWeeks){
        this.name=name;
        this.projectWeeks=projectWeeks;
        courses= new ArrayList<Course>();
    }


     /**
     * Add a new course
     * @param c
     */   
    public void addCourse(Course c){
        courses.add(c);
    }


    /**
     * Get the numbers of weeks of the proyect and the courses
     * @throws IEMOISException WEEKS_EMPTY, if the week cannot be known. WEEKS_ERROR, if the weeks is less than one.
     * @return wks. The number of weeks that a proyect and his courses have
     */
    @Override
    public int weeks() throws IEMOISException{
        int wks = projectWeeks;
        
        if (courses.size() == 0) throw new IEMOISException(IEMOISException.WEEKS_EMPTY);
        
        for (Course e: courses){
            wks += e.weeks();
        }
        return wks;
    }
    
    
     /**
     * Calculates an estimate of weeks
     * For courses where the weeks cannot be known or has error, the max, min or avg  of the known courses is assumed
     * @param type (max,min,avg) 
     * @return wks. The number of weeks that a proyect and his courses have
     * @throws IEMOISException NANO_EMPTY, if it don't have courses. IMPOSSIBLE, if it can't be calculated
     */
    public int weeks(String type) throws IEMOISException{
        int wks = projectWeeks;
        
        try{
            
            weeks();
            
        }catch(IEMOISException e){
            
            if (courses.size() == 0) throw new IEMOISException(IEMOISException.NANO_EMPTY);
            
            int TypeC;
            if(type.equals("max")){
                TypeC = getMax();
                
            }else if(type.equals("min")){
                TypeC = getMin();
                
            }else{
                TypeC = getAvg();
            }
            
            for(Course crs : courses){
                wks += crs.weeks(TypeC);
            }
        }
        return wks;
    }
    
    
    /**
     * Calculates the min week at the courses that are registered in Nanodegree.
     * @throws IEMOISExceptionIMPOSSIBLE, if all the courses cannot return the weeks. 
     * @min, the minimum value.
     */
    public int getMin() throws IEMOISException{
        int min = 1000000;
        for (Course e: courses){
            if (e.weeks != null){
                if(e.getWeeks() < min){
                    min = e.getWeeks();
                }
            }
        }
        
        if(min == 1000000) throw new IEMOISException(IEMOISException.IMPOSSIBLE);
        
        return min;
    }

    /**
     * Calculates the max week at the courses that are registered in Nanodegree.
     * @throws IEMOISExceptionIMPOSSIBLE, if all the courses cannot return the weeks. 
     * @min, the max value.
     */
    public int getMax() throws IEMOISException{
        int max = 0;
        for (Course e: courses){
            if (e.weeks != null){
                if(e.getWeeks() > max){
                    max = e.getWeeks();
                }
            }
        }
        
        if(max == 0) throw new IEMOISException(IEMOISException.IMPOSSIBLE);
        return max;
    }

     /**
     * Calculates the avg of the weeks at the courses that are registered in Nanodegree.
     * @throws IEMOISExceptionIMPOSSIBLE, if all the courses cannot return the weeks. 
     * @avg, the avg of the weeks.
     */
    public int getAvg() throws IEMOISException{
        int avg = 0;
        for (Course e: courses){
            
            if (e.weeks != null){
                avg += e.getWeeks();
            }
        }
        
        if(avg == 0) throw new IEMOISException(IEMOISException.IMPOSSIBLE);
        avg /= courses.size();
        return avg;
    }
    
     /**
     * Calculates an estimate of weeks
     * For courses where the weeks cannot be known, unknown is assumed
     * @param unknown 
     * @return 
     * @throws IEMOISException NANO_EMPTY, if it don't have courses. WEEKS_ERROR, if some course has error
     */
    public int weeks(int unknown) throws IEMOISException{
        int wks = 0;
        if (courses.size() == 0){
            throw new IEMOISException(IEMOISException.NANO_EMPTY);
        }
        for (Course e: courses) {
            if (e.getWeeks() < 1){
                throw new IEMOISException(IEMOISException.WEEKS_ERROR);
            }
            
            if(e.weeks == null){
                wks += unknown;
            }else{
                wks += e.getWeeks();
            }
        }
        return wks;
    } 
    
    @Override
    public String data() throws IEMOISException{
        StringBuffer answer=new StringBuffer();
        answer.append(name+". Proyecto: "+ projectWeeks);
        for(Course c: courses) {
            answer.append("\n\t"+c.data());
        }
        return answer.toString();
    } 

}

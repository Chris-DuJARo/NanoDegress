package domain; 

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;


/**
 * IEMOIS
 * @author POOB  
 * @version ECI 2022
 */

public class IEMOIS{
    private LinkedList<Program> programs;
    private HashMap<String,Course> courses;

    /**
     * Create a IEMOIS
     */
    public IEMOIS(){
        programs = new LinkedList<Program>();
        courses = new HashMap<String,Course>();
        try
        {
            addSome();
        }
        catch (domain.IEMOISException iemoise)
        {
            iemoise.printStackTrace();
        }
    }

    private void addSome() throws IEMOISException {
        String [][] courses = {{"Aprendiendo a Aprender. MacMaster-California. Coursera","4"},
                               {"Introduction to Computer Science and Programming Using Python","8"},
                               {"Databases: Modeling and Theory","5"},{"Databases: Relational Databases and SQL","5"}, 
                               {"Databases: Advances Topics in SQL","5"},{"Databases: Semistructured Data", "5"},
                               {"Machine Learning","9"}};
                               
        for (String [] c: courses){
            addCourse(c[0],c[1]);
        }
        
        String [][] Nanodegrees = {{"Developing Databases. Stanford Online. Edx.", "2", "Databases: Modeling and Theory\nDatabases: Relational Databases and SQL"},
                                       {"Advanced Topics of Databases. Standford Online. Edx.", "2", "Databases: Advances Topics in SQL\nDatabases: Semistructured Data"}};
        for (String [] s: Nanodegrees){
            addNanodegree(s[0],s[1],s[2]);
        }
    }

    /**
     * Consult a program
     * @param name
     * @return 
     */
    public Program consult(String name){
        Program p=null;
        for(int i=0;i<programs.size() && p == null;i++){
            if (programs.get(i).name().compareToIgnoreCase(name)==0) 
               p=programs.get(i);
        }
        return p;
    }

    
    /**
     * Add a new course
     * @param name 
     * @param price
    */
    public void addCourse(String name, String price) throws IEMOISException{ 
        Course nc = new Course(name,Integer.parseInt(price));
        
        for(Course c: courses.values()){
            if(nc.name().equals(c.name())) throw new IEMOISException(IEMOISException.IMPOSSIBLE);
        }
            
        programs.add(nc);
        courses.put(name.toUpperCase(),nc);
    }
    
    /**
     * Add a new Nanodegree
     * @param name 
     * @param projectWeeks
     * @param courses
    */
    public void addNanodegree(String name, String projectWeeks, String courses) throws IEMOISException{ 
        Nanodegree s = new Nanodegree(name,Integer.parseInt(projectWeeks));
        String [] aCourses= courses.split("\n");
        
        for (String p : aCourses){
            if(this.courses.containsKey(p.toUpperCase() )){
                s.addCourse(this.courses.get(p.toUpperCase()));
            }else throw new IEMOISException(IEMOISException.IMPOSSIBLE);
        }
        
        programs.add(s);
    }

    /**
     * Consults the programs that start with a prefix
     * @param  
     * @return 
     */
    public LinkedList<Program> select(String prefix){
        LinkedList <Program> answers = new LinkedList<Program>();
        prefix=prefix.toUpperCase();
        
        for(int i=0;i<programs.size();i++){
            try{
                if(programs.get(i).name().toUpperCase().startsWith(prefix)){
                    answers.add(programs.get(i));
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "No fue posible hallar un curso/nanodegree\n en los registros con el prefijo dado");
                Log.record(e);
            }
        }
        return answers;
    }

    
    /**
     * Consult select programs
     * @param selected
     * @return  
     */
    public String data(LinkedList<Program> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(selected.size()+ " programas encontrados: \n");
        
        for(Program p : selected) {
            try{
                answer.append(p.data());
                answer.append("\n");
            }catch(IEMOISException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
   
    /**
     * Consult the programs
     * @param 
     * @return  
     */
    public String data(){
        StringBuffer answer=new StringBuffer();
        answer.append(programs.size()+ " programas\n");
        
        for(Program p : programs) {
            try{
                answer.append(p.data());
                answer.append("\n");
            }catch(IEMOISException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    } 
    
     /**
     * Return the data of programs with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all programs
     * @return  
     */    
    public String toString(){
        return data();
    }
    
    /**
     * Consult the number of Programs
     * @return 
     */
    public int numberPrograms(){
        return programs.size();
    }

}

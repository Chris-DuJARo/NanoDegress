package test;
import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NanodegreeTest{
   
    
    public NanodegreeTest(){
    }
    
    
    @Before
    public void setUp(){    
    }
    
    @After
    public void tearDown(){
    }
    
    
    @Test
    public void shouldCalculateTheCostOfANanodegree(){
        Nanodegree s = new Nanodegree("FRONT END DEVELOPER", 1);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 2));
        s.addCourse(new Course("INTRO TO JAVASCRIPT", 3));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", 2));
        
        try {
           assertEquals(8,s.weeks());
        } catch (IEMOISException e){
            fail("Threw a exception");
            e.getMessage();
        }    
    }    
    
    @Test
    public void shouldThrowExceptionIfNanodegreeHasNoCourses(){
        Nanodegree s = new Nanodegree("FRONT END DEVELOPER", 10);
        try { 
           int weeks=s.weeks();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.NANO_EMPTY,e.getMessage());
        }    
    }    
    
    
    @Test
    public void shouldThrowExceptionIfThereIsErrorInweeks(){
        Nanodegree s = new Nanodegree("FRONT END DEVELOPER", 1);
        s.addCourse(new Course("INTRO TO HTML AND CSS", 2));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",3));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", -2));
        try { 
           int weeks=s.weeks();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.WEEKS_ERROR,e.getMessage());
        }    
    }     
    
    @Test
    public void shouldThrowExceptionIfweeksIsNotKnown(){
        Nanodegree s = new Nanodegree("FRONT END DEVELOPER", 1);
        
        s.addCourse(new Course("INTRO TO HTML AND CSS", 2));
        s.addCourse(new Course("INTRO TO JAVASCRIPT",null));
        s.addCourse(new Course("JAVASCRIPT AND THE DOM", -2));
        try { 
           int weeks=s.weeks();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.WEEKS_EMPTY,e.getMessage());
        }    
    }
    
    @Test
    public void shoulThrownExceptionIfCourseDontHaveweeks(){
        Course s = new Course("FRONT END DEVELOPER",null);
        try { 
           int weeks=s.weeks();
           fail("Did not throw exception");
        } catch (IEMOISException e) {
            assertEquals(IEMOISException.WEEKS_EMPTY,e.getMessage());
        } 
    
    }
    
    @Test
    public void shouldThrownExceptionWhenAcourseAlreadyExists(){       
       try {
           IEMOIS s = new IEMOIS();
           
           s.addCourse("FRONT END DEVELOPER","5");
           s.addCourse("FRONT END DEVELOPER","6");
           
           fail("Did not throw exception");
       } catch (IEMOISException e){
            assertEquals(IEMOISException.IMPOSSIBLE,e.getMessage());
       }
    }
    
    @Test
    public void shouldThrownExceptionWhenANanoDegreeAlreadyExists(){
       try {
           IEMOIS s = new IEMOIS();
           
           s.addNanodegree("FRONT END DEVELOPER","5","Programacion 1\nProgramacion 2");
           s.addNanodegree("FRONT END DEVELOPER","6", "Arquitectura 1\nArquitectura 2");
           
           fail("Did not throw exception");
       } catch (IEMOISException e){
            assertEquals(IEMOISException.IMPOSSIBLE,e.getMessage());
       }
    }
    
    @Test
    public void shouldThrownExceptionIfSomeCourseInNanoDegreeDoesNotExists(){
       try {
           IEMOIS s = new IEMOIS();
           s.addCourse("Curso1","2");
           s.addCourse("Curso2","3");
           
           s.addNanodegree("Mi Lista","5","Curso1\nCurso2\nPOOB");
           fail("Did not throw exception");
       } catch (IEMOISException e){
            assertEquals(IEMOISException.IMPOSSIBLE,e.getMessage());
       }
    }
    
    @Test
    public void shouldSearch(){
        IEMOIS s = new IEMOIS();
        try
        {
            s.addCourse("MiCursoIngenieria","1");
            s.addCourse("MiCursoProgramacion","2");
            
            s.search("MiCurso");
            fail("Did not throw exception");
        }
        catch (IEMOISException iemoise)
        {
            
        }
        
    }
}
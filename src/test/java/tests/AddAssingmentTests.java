package tests;

import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.*;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static org.junit.Assert.assertTrue;

public class AddAssingmentTests {

    @Test
    public void testAssignments(){
        testAddTemaSuccessful();
        testAddTemaUnsuccessful();
    }

    @Test
    public void testAddTemaSuccessful(){

        TemaValidator tv = new TemaValidator();
        StudentValidator sv = new StudentValidator();
        NotaValidator nv = new NotaValidator();
        TemaXMLRepository temaRepository = new TemaXMLRepository(tv,"teme.xml");
        StudentXMLRepository studentRepository = new StudentXMLRepository(sv,"studenti.xml");
        NotaXMLRepository notaRepository = new NotaXMLRepository(nv,"note.xml");
        Service service = new Service(studentRepository,temaRepository,notaRepository);

        try{
            service.deleteTema("4");
        }
        catch (ValidationException ex){
            ex.printStackTrace();
        }
        int result = service.saveTema("4","Mihai",8,7);

        assertTrue(result==1);

        service.deleteTema("4");
    }

    @Test
    public void testAddTemaUnsuccessful(){
        TemaValidator tv = new TemaValidator();
        StudentValidator sv = new StudentValidator();
        NotaValidator nv = new NotaValidator();
        TemaXMLRepository temaRepository = new TemaXMLRepository(tv,"teme.xml");
        StudentXMLRepository studentRepository = new StudentXMLRepository(sv,"studenti.xml");
        NotaXMLRepository notaRepository = new NotaXMLRepository(nv,"note.xml");
        Service service = new Service(studentRepository,temaRepository,notaRepository);

        try{
            int result = service.saveTema("4","Mihai",8,10);
            assertTrue(result==1);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Entitatea nu este valida!"));
        }

    }
}

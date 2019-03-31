package tests;

import domain.Student;
import org.junit.Test;
import repository.StudentRepository;
import validation.StudentValidator;
import validation.ValidationException;

import static org.junit.Assert.*;

public class Test1 {

    @Test
    public void testConcatenate(){
        testAddStudentSuccess();
        testSameIdStudentTwice();
        testGroupOk();
        testInvalidGroup();
        testInvalidGroup2();
        testInvalidName();
        testInvalidName2();
        testIDNull();
    }

    @Test
    public void testAddStudentSuccess() {
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student student = new Student("1","Mihai",935);
        studentRepository.save(student);
        assertEquals(1, (int) studentRepository.getSize());
    }

    @Test
    public void testSameIdStudentTwice(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student student = new Student("1","Mihai",935);
        studentRepository.save(student);
        assertEquals(1, (int) studentRepository.getSize());

        Student newStud = new Student("1","Andrei",935);
        try{
            studentRepository.save(newStud);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Student existent sau invalid!"));
        }
    }

    @Test
    public void testInvalidGroup(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student newStud = new Student("1","Andrei",90);
        try{
            studentRepository.save(newStud);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Grupa invalida!"));
        }
        assertEquals(0, (int) studentRepository.getSize());
    }

    @Test
    public void testInvalidGroup2(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student newStud = new Student("1","Marin",1200);
        try{
            studentRepository.save(newStud);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Grupa invalida!"));
        }
        assertEquals(0, (int) studentRepository.getSize());
    }


    @Test
    public void testInvalidName(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student newStud = new Student("1","",1200);
        try{
            studentRepository.save(newStud);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Nume invalid!"));
        }
        assertEquals(0, (int) studentRepository.getSize());
    }

    @Test
    public void testInvalidName2(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student newStud = new Student("1",null,1200);
        try{
            studentRepository.save(newStud);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Nume invalid!"));
        }
        assertEquals(0, (int) studentRepository.getSize());
    }

    @Test
    public void testGroupOk(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student newStud = new Student("1","Mircea",202);

            studentRepository.save(newStud);

        assertEquals(1, (int) studentRepository.getSize());
    }

    @Test
    public void testIDNull(){
        StudentValidator sv = new StudentValidator();
        StudentRepository studentRepository = new StudentRepository(sv);

        Student newStud = new Student(null,"Mircea",202);
        try{
            studentRepository.save(newStud);
        }
        catch(ValidationException ex){
            assertTrue(ex.getMessage().contains("Nume invalid!"));
        }
        assertEquals(0, (int) studentRepository.getSize());
    }


}
package tests;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.io.File;

public class BaseTest {
    protected Service service;
    @Before
    public void setUp() throws Exception {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti_test.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "note_test.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "teme_test.xml");

        this.service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @After
    public void setDown() throws Exception {
        try {
            File studenti_test = new File("studenti_test.xml");
            studenti_test.delete();

            File note_test = new File("note_test.xml");
            note_test.delete();

            File teme_test = new File("teme_test.xml");
            teme_test.delete();
        } catch (Exception ignored) { }
    }
}

package tests;

import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.TemaXMLRepository;
import validation.TemaValidator;
import validation.Validator;

import java.io.File;
import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class AddAssignmentTest {
    TemaXMLRepository temaXMLRepository;

    @Before
    public void setUp() throws Exception {
        Validator<Tema> temaValidator = new TemaValidator();

        temaXMLRepository = new TemaXMLRepository(temaValidator, "note_test.xml");
    }


    @After
    public void setDown() throws Exception {
        try {
            File note_test = new File("note_test.xml");
            note_test.delete();
        } catch (Exception ignored) {
        }
    }

    @Test
    public void test_addAssignment_valid() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test String", 2, 1));
        assertEquals(1, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    @Test
    public void test_addAssignment_nullId() throws Exception {
        this.temaXMLRepository.save(new Tema(null, "Test String", 2, 1));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //tema.getDescriere() == null
    @Test
    public void test_addAssignment_nullDescriere() throws Exception {
        this.temaXMLRepository.save(new Tema("0", null, 2, 1));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //|| tema.getDescriere().equals("")
    @Test
    public void test_addAssignment_emptyDescriere() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "", 2, 1));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //|| tema.getDeadline() < 1
    @Test
    public void test_addAssignment_zeroDeadline() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test Tema", 0, 1));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //|| tema.getDeadline() > 14
    @Test
    public void test_addAssignment_overMaximumDeadline() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test Tema", 100, 1));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //|| tema.getDeadline() < tema.getStartline()
    @Test
    public void test_addAssignment_deadlineBeforeStartline() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test Tema", 5, 6));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //|| tema.getStartline() < 1
    @Test
    public void test_addAssignment_zeroStartLine() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test Tema", 1, 0));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    //|| tema.getStartline() > 14
    @Test
    public void test_addAssignment_overMaximumStartLine() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test Tema", 1, 100));
        assertEquals(0, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }

    @Test
    public void test_addAssignment_startlineEqualsDeadline() throws Exception {
        this.temaXMLRepository.save(new Tema("0", "Test Tema", 5, 5));
        assertEquals(1, StreamSupport.stream(this.temaXMLRepository.findAll().spliterator(), false).count());
    }
}

package tests;
import org.junit.Test;

import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class BigBangTest extends BaseServiceTest {
    @Test
    public void test_saveStudentValid() throws Exception {
        this.service.saveStudent("1", "Mihalache Mihai", 935);
        assertEquals(1, StreamSupport.stream(this.service.findAllStudents().spliterator(), false).count());
    }

    @Test
    public void test_saveTemaValid() throws Exception {
        this.service.saveTema("1", "Some good homework", 10, 2);
        assertEquals(1, StreamSupport.stream(this.service.findAllTeme().spliterator(), false).count());
    }

    @Test
    public void test_saveGradeValid() throws Exception {
        this.service.saveNota("1", "1", 10, 5, "Good");
        assertEquals(0, StreamSupport.stream(this.service.findAllNote().spliterator(), false).count());
    }

    @Test
    public void test_Bang() throws Exception {
        this.test_saveStudentValid();
        this.test_saveTemaValid();
        this.test_saveGradeValid();
    }
}
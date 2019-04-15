package tests;

import org.junit.Test;

import java.util.stream.StreamSupport;

import static org.junit.Assert.assertEquals;

public class AddGradeTest extends BaseServiceTest {
    @Test
    public void test_saveGrade_valid() throws Exception {
        this.service.saveStudent("1", "Krisztian Kristo", 934);
        assertEquals(1, StreamSupport.stream(this.service.findAllStudents().spliterator(), false).count());

        this.service.saveTema("1", "Some good homework", 10, 2);
        assertEquals(1, StreamSupport.stream(this.service.findAllTeme().spliterator(), false).count());

        this.service.saveNota("1", "1", 10, 5, "Good");
        assertEquals(0, StreamSupport.stream(this.service.findAllNote().spliterator(), false).count());
    }
}

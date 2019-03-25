package tests;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test1 {

    @Test
    public void testConcatenate() {
        Test1 myUnit = new Test1();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    private String concatenate(String s1, String s2){
        return s1+s2;
    }
}
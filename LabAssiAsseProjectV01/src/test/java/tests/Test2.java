package tests;

import org.junit.Test;
import static org.junit.Assert.*;

public class Test2 {

    @Test
    public void testConcatenate() {
        Test2 myUnit = new Test2();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    private String concatenate(String s1, String s2){
        return s1+s2;
    }
}

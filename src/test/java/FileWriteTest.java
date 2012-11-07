package test.java;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import e115763.FileWrite;

import java.io.*;

public class FileWriteTest {
    // for stdout test
    private PrintStream _saved;
    private ByteArrayOutputStream _actual;
    private ByteArrayOutputStream _expected;
    private PrintStream _out;
    
    @Before public void setUp() {
        _saved = System.out;
        _actual = new ByteArrayOutputStream();
        _expected = new ByteArrayOutputStream();
        System.setOut(new PrintStream(new BufferedOutputStream(_actual)));
        _out = new PrintStream(new BufferedOutputStream(_expected));
    }

    @After public void tearDown() {
        System.setOut(_saved);
    }
    @Test public void FileWriteRunTest() {
        String filename = "/tmp/test";
        String[] testArg = {"-c", "string", "-s", "3000", "-b", "50", "-n", filename};
        FileWrite.main(testArg);
        File file = new File(filename);
        assert(file.exists());
        file.delete();
    }
    
    // exit and stdout output if no args
    @Test public void ExitTest() {
        // Expected
        _out.println("undefined output strings. please use -c option");
        _out.flush();

        // Actual
        FileWrite.main(new String[0]);
        System.out.flush();

        // Compare
        assertEquals(_expected.toString(), _actual.toString());
    }
    
    // exist file not overwrite
    @Test public void NoOverWriteTest() {
        // create file
        String filename = "/tmp/test";
        String[] testArg = {"-c", "string", "-s", "3000", "-b", "50", "-n", filename};
        FileWrite.main(testArg);

        // Actual
        FileWrite.main(testArg);    // test run (not overwrite message)
        System.out.flush();
        
        // Expected
        _out.println("/tmp/test is already exists.");
        _out.flush();

        // Compare
        assertEquals(_expected.toString(), _actual.toString());
        
        // delete file
        File file = new File(filename);
        file.delete();
    }
}

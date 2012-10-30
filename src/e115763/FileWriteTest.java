package e115763;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileWriteTest {
	
	@Test public void parseArgsTestEmptyArgs() {
		String[] testArg = {""};
		assertEquals(null,   FileWrite.parseArgs(testArg, "-n"));
		assertEquals("1024", FileWrite.parseArgs(testArg, "-b"));
		assertEquals("1024", FileWrite.parseArgs(testArg, "-s"));
	}
}

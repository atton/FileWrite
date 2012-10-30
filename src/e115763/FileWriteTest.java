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
	@Test public void parseArgsTestGetValue() {
		String[] testArg = {"-c", "string", "-s", "3000", "-b", "50", "-n", "filename"};
		assertEquals("filename",   FileWrite.parseArgs(testArg, "-n"));
		assertEquals("50", FileWrite.parseArgs(testArg, "-b"));
		assertEquals("3000", FileWrite.parseArgs(testArg, "-s"));
		assertEquals("string", FileWrite.parseArgs(testArg, "-c"));
	}
}
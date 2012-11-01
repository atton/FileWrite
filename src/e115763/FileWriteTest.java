package e115763;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class FileWriteTest {
	
	@Test public void FileWriteRunTest() {
		String filename = "test";
		String[] testArg = {"-c", "string", "-s", "3000", "-b", "50", "-n", filename};
		FileWrite.main(testArg);
		File file = new File(filename);
		assert(file.exists());
		file.delete();
	}
}
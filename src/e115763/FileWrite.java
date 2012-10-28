package e115763;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class FileWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File f = new File("test");
		try {
			FileOutputStream os = new FileOutputStream(f);
			FileChannel oc = os.getChannel();
			int size = 1000000000;
			int bufsize = 1024;
			for(int remain = size; remain > 0;remain -= bufsize) {
				writeTest(oc,bufsize);
			}
			os.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTest(FileChannel oc, int size){
		ByteBuffer srcs = generateTestData('c',size);
		try {
			oc.write(srcs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ByteBuffer generateTestData(char c, int size) {
		ByteBuffer buf = ByteBuffer.allocate(size);
		for(int i=0; i < size; i++) {
			buf.put((byte)c);
		}
		buf.flip();
		return buf;
	}
}
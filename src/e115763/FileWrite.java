package e115763;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class FileWrite {
	private static int BUFSIZE = 1024;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		writeFile("test", 100, BUFSIZE,"hoge");
	}
	
	private static void writeFile(String fileName,int size, int bufsize, String str){
		File f = new File(fileName);
		try {
			FileOutputStream os = new FileOutputStream(f);
			FileChannel oc = os.getChannel();
			ByteBuffer srcs = generateTestData(str,bufsize);
			for(int remain = size; remain > 0;remain -= bufsize) {
				writeTest(oc, srcs);
			}
			os.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTest(FileChannel oc, ByteBuffer srcs){
		try {
			oc.write(srcs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ByteBuffer generateTestData(String str, int bufsize) {
		ByteBuffer buf = ByteBuffer.allocate(bufsize);
		for(int i=0; i < bufsize; i++) {
			int index = i % str.length();
			buf.put((byte)str.charAt(index));
		}
		buf.flip();
		return buf;
	}
}
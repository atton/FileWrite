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
			for(int remain = size; remain > 0;remain -= bufsize) {
				writeTest(oc,bufsize);
			}
			os.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void writeTest(FileChannel oc, int bufsize){
		ByteBuffer srcs = generateTestData("bc",bufsize);
		try {
			oc.write(srcs);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static ByteBuffer generateTestData(String str, int size) {
		ByteBuffer buf = ByteBuffer.allocate(size);
		for(int i=0; i < size; i++) {
			int index = i % str.length();
			buf.put((byte)str.charAt(index));
		}
		buf.flip();
		return buf;
	}
}
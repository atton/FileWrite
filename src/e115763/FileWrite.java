package e115763;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class FileWrite {
	private static int BUFSIZE = 1024;

	public static void main(String[] args) {
		String str = null;
		String filename = null;
		long size = BUFSIZE;
		long bufsize = BUFSIZE;

		// parse args
		for(int i =0; i<args.length; i++) {
			switch(args[i]) {
			case "-c" :
				str = args[++i];
				break;
			case "-n" :
				filename = args[++i];
				break;
			case "-s" :
				size = Long.parseLong(args[++i]);
				break;
			case "-b" :
				bufsize = Long.parseLong(args[++i]);
				break;
			default :
				System.out.println("unknown option" + args[i]);
				return;
			}
		}
		
		if(str == null) {
			System.out.println("undefined output strings. please use -c option");
			return;
		}
		
		if (filename != null) {
			File file = new File(filename);
			if(file.exists()) {
				System.out.println(filename + " is already exists.");
				return;
			}
			writeFile(filename, size, bufsize, str);
		} else {
			outputStdout(size, str);
		}
	}

	private static void writeFile(String filename,long size, long bufsize, String str){
		File f =  new File(filename);
		try {
			FileOutputStream os = new FileOutputStream(f);
			FileChannel oc = os.getChannel();
			for(long remain = size; remain > 0;remain -= bufsize) {
				int writesize = (int)((remain < (long)bufsize) ? remain : bufsize);
				ByteBuffer srcs = generateTestData(str,writesize);
				writeTest(oc, srcs);
			}
			os.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	private static void outputStdout(long size, String str) {
		for(int i = 0; i < size; i++) {
			int index = str.length() <= 1 ? 0 : i % (str.length());
			System.out.print(str.charAt(index));
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
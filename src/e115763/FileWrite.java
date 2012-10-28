package e115763;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class FileWrite {
	private static String BUFSIZE = "1024";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = parseArgs(args,"-c");
		String filename = parseArgs(args,"-n");
		
		int size = Integer.parseInt(parseArgs(args,"-s"));
		int bufsize = Integer.parseInt(parseArgs(args,"-b"));
		if (filename != null) {
			writeFile(filename, size, bufsize, str);
		} else {
			outputStdout(size, str);
		}
	}

	private static void writeFile(String filename,int size, int bufsize, String str){
		File f =  new File(filename);
		try {
			FileOutputStream os = new FileOutputStream(f);
			FileChannel oc = os.getChannel();
			for(int remain = size; remain > 0;remain -= bufsize) {
				int writesize = (remain < bufsize) ? remain : bufsize;
				ByteBuffer srcs = generateTestData(str,writesize);
				writeTest(oc, srcs);
			}
			os.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void outputStdout(int size, String str) {
		for(int i = 0; i < size; i++)
			System.out.print(str.charAt(i % (str.length() -1)));
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

	private static String parseArgs(String[] args , String opt) {
		for(int i = 0; i < args.length; i++) {
			if (args[i].equals(opt)) {
				return args[i+1];
			}
		}
		if (opt.equals("-b")) return BUFSIZE;
		if (opt.equals("-s")) return BUFSIZE;
		if (opt.equals("-c")) System.out.println("please input string. use -c option");
		System.out.println(opt + " option not found");
		return null;
	}
}
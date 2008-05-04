package org.soulspace.template.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class FileUtils {

  public static void writeStringToFile(String filename, String content) throws FileNotFoundException {
    File file = new File(filename);
    PrintWriter pw = new PrintWriter(new FileOutputStream(file));
    pw.print(content);
    pw.close();
  }

  public static void writeStringToFile(File file, String content) throws FileNotFoundException {
    PrintWriter pw = new PrintWriter(new FileOutputStream(file));
    pw.print(content);
    pw.close();
  }
	
	public static String loadStringFromFile(String fileName) throws IOException {
		StringBuilder buffer = new StringBuilder(128);
    String line = null;

		File file = new File(fileName);

    BufferedReader in = new BufferedReader(new FileReader(file));
    while((line = in.readLine()) != null) {
      buffer.append(line + "\n");
    }
		in.close();
		return buffer.toString();	
	}

	public static String loadStringFromFile(File file) throws IOException {
		StringBuilder buffer = new StringBuilder(128);
    String line = null;

    BufferedReader in = new BufferedReader(new FileReader(file));
    while((line = in.readLine()) != null) {
      buffer.append(line + "\n");
    }
		in.close();
		return buffer.toString();	
	}

	public static String loadStringFromStream(InputStream is) throws IOException {
		StringBuilder buffer = new StringBuilder(128);
    String line = null;

    BufferedReader in = new BufferedReader(new InputStreamReader(is));
    while((line = in.readLine()) != null) {
      buffer.append(line + "\n");
    }
		in.close();
		return buffer.toString();	
	}


}

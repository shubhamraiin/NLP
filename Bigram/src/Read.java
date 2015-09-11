/*
 * This file reads the content of file by given name i.e corpus.txt and return the content as a string
 * It also writes the content of file by given name, in this case bigram file
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Read {

	protected static String readCorpus(String filename) {
		InputStream in = null;
		BufferedReader br = null;
		String result = null;
		try {
			in = Read.class.getResourceAsStream(filename);
			br = new BufferedReader(new InputStreamReader(in));

			StringBuffer buf = new StringBuffer();
			String line = null;
			while (null != (line = br.readLine())) {
				buf.append(line + '\n');
			}

			result = buf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (null != br) {
				try {
					br.close();
					br = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != in) {
				try {
					in.close();
					in = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	protected static boolean writeFile(String filename, String content) {
		FileOutputStream out = null;
		BufferedWriter writer = null;
		try {
			out = new FileOutputStream(filename);
			writer = new BufferedWriter(new OutputStreamWriter(out));

			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (null != writer) {
				try {
					writer.close();
					writer = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
					out = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return true;
	}

	public static boolean isFileExisted(String path) {
		File file = new File(path);
		return file.exists();
	}
}
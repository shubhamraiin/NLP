/*This class is used to read the input file line by line*/

import java.io.BufferedReader;
import java.io.FileReader;

public class Read {

	public static String readFile(String label) {
		BufferedReader br = null;
		String str = null;
		StringBuilder strbld = new StringBuilder();

		try {
			br = new BufferedReader(new FileReader(label));
			String line = null;
			while ((line = br.readLine()) != null) 
			{
				strbld.append(line);
				strbld.append("");

			}
			str = strbld.toString();
			str = str.replaceAll("[^a-zA-Z0-9\\s\\./-]", "");

			br.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		return (str);
	}

}
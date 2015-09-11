

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class ProcessSummary {

	// LEXICON
	private static String LEXICON = "input/lexicon.txt";
	// Lexicon words
	private ArrayList<String> lexicon;
	// Input separated by sentences
	private String title;
	// Input separated by paraSummaries
	private ArrayList<ParaSummary> paraSummaries;
	// Stanford Tagger
	private MaxentTagger tagger = new MaxentTagger(
			"models/left3words-wsj-0-18.tagger");
	// Classifier
	private String serializedClassifier = "classifier/muc.7class.distsim.crf.ser.gz";
	@SuppressWarnings("rawtypes")
	private AbstractSequenceClassifier classifier;

	// Main Method

	public ProcessSummary() throws IOException, ClassNotFoundException {
		this.paraSummaries = new ArrayList<ParaSummary>();
		this.lexicon = new ArrayList<String>();
		this.classifier = CRFClassifier
				.getClassifierNoExceptions(this.serializedClassifier);
		readLexicon();

	}

	// Reads lexicon.txt

	public void readLexicon() throws IOException, ClassNotFoundException {
		// Open the file that is the first
		// command line parameter
		FileInputStream fstream = new FileInputStream(LEXICON);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			
			this.lexicon.add(strLine);
		}
		// Close the input stream
		in.close();
	}

	// Reads Input from GUI

	public void readInputFromGUI(String input) throws IOException,
			ClassNotFoundException {
		this.paraSummaries = new ArrayList<ParaSummary>();
		BufferedReader br = new BufferedReader(new StringReader(input));
		String strLine;
		int num = 0;
		while ((strLine = br.readLine()) != null) {
			
			if (num == 0) {
				this.title = strLine;
			} else {
				this.paraSummaries.add(new ParaSummary(strLine, this.tagger,
						this.lexicon, this.classifier, this.title));
				
			}
			num++;
		}
	}

	/*
	 * Compiles a String with a report on the different information of the
	 * article text
	 */

	public String returnsReportHTML() {
		int numberOfParagraphs = 0;
		String paragraphsTogeather = "";
		String report = "<html>";
		report += "<font size=+1><b>TITLE: " + this.title + "</b></font><br>";
		for (int i = 0; i < this.paraSummaries.size(); i++) {
			String resumen = this.paraSummaries.get(i).resultSummary();
			paragraphsTogeather += resumen;
			numberOfParagraphs = i + 1;
		}
		report += "Number Of Paragraphs: " + numberOfParagraphs + "<br>";
		report += paragraphsTogeather;

		return report;

	}
}

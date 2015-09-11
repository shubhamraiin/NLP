

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;

public class NERpara {

	/*Stanford Named Entity Recognizer. Named Entity Recognition (NER) labels sequences of words 
	 * in a text which are the names of things, such as person and company names, or gene and protein names.
	 * 
	 */
	private String paragraphItself;
	/*
	 * The Classifier
	 */
	@SuppressWarnings("rawtypes")
	AbstractSequenceClassifier classifier;
	/*
	 * Possible categories of the entities in the sentence
	 */
	public static String LOCATION ="LOCATION";
	public static String TIME = "TIME";
	public static String PERSON = "PERSON";
	public static String ORGANIZATION = "ORGANIZATION";
	public static String MONEY = "MONEY";
	public static String PERCENT = "PERCENT";
	public static String DATE = "DATE";
	
	@SuppressWarnings("rawtypes")
	public NERpara(String paragraph2, AbstractSequenceClassifier classifier2)
	{
		paragraphItself = paragraph2;
		classifier = classifier2;
	}
	
	/*
	 * Returns all the entities given a specific category in the sentence
	 */
	@SuppressWarnings("unused")
	public ArrayList<String> returnEntities(String category)
	{
		ArrayList<String> entities = new ArrayList<String>();
		String s = classifier.classifyWithInlineXML(paragraphItself);
		Pattern p = Pattern.compile("(\\<\\b"+category+"\\b\\>)(.*?)(\\<\\/\\b"+category+"\\b\\>)");
        Matcher m = p.matcher(s);
        ArrayList<String> matchesNER = new ArrayList<String>();
        while (m.find()) {
        	matchesNER.add(m.group(2));
        }
        
        ArrayList<String> matchesNERWeight = new ArrayList<String>();
        String replaceParagraph = paragraphItself.replace(",","");
        String[] splitParagraph = replaceParagraph.split(" ");
        for (int i = 0; i < splitParagraph.length; i++) { 
        	for (int j = 0; j < matchesNER.size(); j++) {
				String[] matchesNERSplit = matchesNER.get(j).split(" ");    
					for (int k = 0; k < matchesNERSplit.length; k++) {
						
						try {
							if (splitParagraph[i].equalsIgnoreCase(matchesNERSplit[k])) {
								String keyWord = matchesNER.get(j);
								keyWord +="/"+i;
								matchesNERWeight.add(keyWord);
								i+=matchesNERSplit.length;
							}
						} catch (Exception e) {
							
						}
						
						}
					}
				}
			
		return matchesNERWeight;
	}
}

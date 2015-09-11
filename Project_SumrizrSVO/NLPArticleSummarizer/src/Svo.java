

import java.util.ArrayList;

/*
 This class finds  SUBJECT, VERB, OBJECT Structure and words surrounding the OBJECT
 */
public class Svo {

	// Array of ACTORS: compound by keywords on Organizations, Words on Lexicon,
	// People etc.
	ArrayList<String> actors;
	// Array of VERBS
	ArrayList<String> verbs;
	// Array of NUMBERS: compound by keywords on Cardinal Numbers, Money and Percent.
	ArrayList<String> numbers;
	// paragraph
	String paragraph;
	// paragraph Numbered
	String paragraphNumbered;
	// Array of paragraphNumberd spited by (space)[ careful with punctuation].
	String[] paragraphNumberedSplit;

	public Svo(ArrayList<String> ACTORS, ArrayList<String> VERBS,
			ArrayList<String> NUMBERS, String P, String PN) {
		this.actors = ACTORS;
		this.verbs = VERBS;
		this.numbers = NUMBERS;
		this.paragraph = P;
		this.paragraphNumbered = PN;
		this.paragraphNumberedSplit = PN.split(" ");
	}

	// Gets the SUBJECT the VERB and OBJECT structure in an array
	// keyword/position value n

	public String[] getSVOStructure() {

		// finding the subject
		String subject = "null/0";
		String[] subjectSplit = subject.split("/");
		try {
			subject = this.actors.get(0);
			subjectSplit = this.actors.get(0).split("/");
		} catch (Exception e) {

		}
		String subjectWeightSplit = subjectSplit[subjectSplit.length - 1];
		int subjectWeight = Integer.parseInt(subjectWeightSplit);

		// finding the verb
		String verb = null;
		int verbWeight = 0;
		for (int i = 0; i < this.verbs.size(); i++) {
			String[] verbSplit = this.verbs.get(i).split("/");
			String verbWeightSplit = verbSplit[verbSplit.length - 1];
			verbWeight = Integer.parseInt(verbWeightSplit);
			if (verbWeight > subjectWeight) {
				// if the verb is : is, are, be
				if (verbSplit[0].equalsIgnoreCase("is")
						|| verbSplit[0].equalsIgnoreCase("are")
						|| verbSplit[0].equalsIgnoreCase("be")) {
					// get the next verb available verb
					verb = this.verbs.get(i + 1);
				} else {
					// verb found
					verb = this.verbs.get(i);
				}
				i = i + 1000;
			}
		}
		// finding the object
		String object = null;
		for (int i = 0; i < this.actors.size(); i++) {
			String[] objSplit = this.actors.get(i).split("/");
			String objWeightSplit = objSplit[objSplit.length - 1];
			int objWeight = Integer.parseInt(objWeightSplit);
			if (objWeight > verbWeight) {
				object = this.actors.get(i);
				i = i + 1000;
			}
		}

		// sentence = subject+" "+verb+" "+object;
		String[] SVO = { subject, verb, object };
		return SVO;
	}

	// Gets the surrounding words of the OBJECT taking threshold range of words
	// to find as parameter

	public ArrayList<String> getObjectSurrownd(int threshold) {
		ArrayList<String> wordsSurrownding = new ArrayList<String>();
		String[] SVO = getSVOStructure();
		System.out.println(SVO);
		// its possible that we don't find an object
		String[] verbSplit = SVO[SVO.length - 2].split("/");
		int verbWeight = Integer.parseInt(verbSplit[verbSplit.length - 1]);
		int objectWeight = verbWeight;
		// try to find a Object
		try {
			String[] objectSplit = SVO[SVO.length - 1].split("/");
			objectWeight = Integer
					.parseInt(objectSplit[objectSplit.length - 1]);

		} catch (Exception e) {

		}

		for (int i = 0; i < this.paragraphNumberedSplit.length; i++) {
			String[] spp = this.paragraphNumberedSplit[i].split("/");
			String spp_weight = spp[spp.length - 1];
			int wrd = Integer.parseInt(spp_weight);
			if (wrd >= objectWeight - threshold
					&& wrd <= objectWeight + threshold) {
				String w = this.paragraphNumberedSplit[i];
				wordsSurrownding.add(w);
			}
		}

		return wordsSurrownding;

	}

	// Returns the Array NUMBERS

	public ArrayList<String> returnNumbers() {

		return this.numbers;
	}

	// Returns the Array ACTORS
	
	public ArrayList<String> returnActors() {
		return this.actors;

	}

	//Returns the Array VERBS
	
	public ArrayList<String> returnVerbs() {
		return this.verbs;
	}
}

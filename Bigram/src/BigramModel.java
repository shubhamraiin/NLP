/*
*   This file is used to compute the bigram ,calculate bigram counts, bigram probabilities without
*   smoothing, with add-one smoothing and with good Turing smoothing.
*   This file will also calculate the Total Probability of an input sentence. 
*
*/
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;



public class BigramModel {
	// Good-Turing threshold, that is, c* = c for c > k
	private static final int K = 5;
	private Map<Bigram, Integer> bigramCount = new HashMap<Bigram, Integer>();
	private Map<String, Integer> wordCount = new HashMap<>();
	private final static String pathDirectory = "corpus.txt";
	private String corpus = null;

	public BigramModel() {
		corpus = Read.readCorpus(pathDirectory);
	}
	
	public void trainModel() {
		
		if (null == corpus) {
			System.err.println("Error in reading corpus file. ");
			return;
		}

		corpus = corpus.replaceAll("\n", " ");
		corpus = corpus.toLowerCase().trim();
		String[] words = corpus.split("\\ +");

		for (int i = 0; i < words.length - 1; i++) {
			Bigram bigram = new Bigram();
			bigram.setWord1(words[i]);
			bigram.setWord2(words[i + 1]);

			// count bigrams
			if (!bigramCount.containsKey(bigram)) {
				bigramCount.put(bigram, 1);
			} else {
				bigramCount.put(bigram, bigramCount.get(bigram) + 1);
			}

			// count words
			if (!wordCount.containsKey(words[i])) {
				wordCount.put(words[i], 1);
			} else {
				wordCount.put(words[i], wordCount.get(words[i]) + 1);
			}
		}
	}

	public Map<Bigram, Integer> getBigrams() {
		return bigramCount;
	}

	// to count frequency of single word.P(w1)
	public Integer wordCountCalc(String word) {
		return wordCount.get(word);
	}

	// to compute the bigram count of the input sentence
	public Object[][] count(String input) {
		
		input = input.trim().toLowerCase();
		String[] inputs = input.split("\\ +");
		Set<String> temp = new HashSet<>(Arrays.asList(inputs));
		String[] words = new String[temp.size()];
		System.arraycopy(temp.toArray(), 0, words, 0, temp.size());

		Object[][] table = new Object[words.length + 1][words.length + 1];
		
		for (int i = 0; i < words.length; i++) {
			table[0][i + 1] = words[i];
			table[i + 1][0] = words[i];
		}
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				Integer count = bigramCount.get(new Bigram(words[i]
						.toLowerCase(), words[j].toLowerCase()));
				//use 0, if there exists no such kind of bigram 
				table[i + 1][j + 1] = null == count ? 0 : count;
			}
		}

		return table;
	}

	//to compute the bigram probability without smoothing of the input sentence
	public Object[][] withoutSmoothing(String input) {
		Object[][] countTable = count(input);
		Object[][] fillTable = new Object[countTable.length][countTable[0].length];
		for (int i = 1; i < countTable.length; i++) {
			fillTable[0][i] = countTable[0][i];
			fillTable[i][0] = countTable[i][0];
		}

		for (int i = 1; i < countTable.length; i++) {
			for (int j = 1; j < countTable.length; j++) {
				Integer wordCnt = wordCountCalc((String) countTable[i][0]);
				if (null == wordCnt || wordCnt.intValue() == 0) {
					fillTable[i][j] = 0.0;
				} else {
					// Formula: P(w2|w1) = P(w1w2) / P(w1)
					fillTable[i][j] = (Double) ((Integer) countTable[i][j] / (1.0 * wordCnt));
				}
			}
		}

		return fillTable;
	}

	//to compute the bigram probability with add one smoothing of the input sentence
	public Object[][] addoneSmoothing(String input) {
		Object[][] countTable = count(input);
		Object[][] fillTable = new Object[countTable.length][countTable[0].length];

		for (int i = 1; i < countTable.length; i++) {
			fillTable[0][i] = countTable[0][i];
			fillTable[i][0] = countTable[i][0];
		}

		// Vocabulary V
		Integer V = wordCount.keySet().size();
		for (int i = 1; i < countTable.length; i++) {
			for (int j = 1; j < countTable.length; j++) {
				Integer wordCnt = wordCountCalc((String) countTable[i][0]);
				if (null == wordCnt || wordCnt.intValue() == 0) {
					fillTable[i][j] = 0.0;
				} else {
					//  add-one smooth formula: C* = (c + 1) N / (N + V)
					fillTable[i][j] = ((Integer) countTable[i][j] + 1)
							/ (1.0 * (wordCountCalc((String) countTable[i][0]) + V));
				}
			}
		}

		return fillTable;
	}
	
	//to compute the bigram probability with good Turing smoothing of the input sentence

	public Object[][] goodturingSmoothing(String input) {
		Object[][] countTable = count(input);
		Object[][] fillTable = new Object[countTable.length][countTable[0].length];

		for (int i = 1; i < countTable.length; i++) {
			fillTable[0][i] = countTable[0][i];
			fillTable[i][0] = countTable[i][0];
		}

		// total frequency N
		Integer totalFreq = 0;
		Integer maxFreq = 0;
		for (Integer freq : bigramCount.values()) {
			totalFreq += freq;
			if (freq > maxFreq) {
				maxFreq = freq;
			}
		}

		// Nc List, the number of bigrams that occur c times;
		int N[] = new int[maxFreq + 1];
		for (Integer freq : bigramCount.values()) {
			N[freq] += 1;
		}

		for (int i = 1; i < countTable.length; i++) {
			for (int j = 1; j < countTable.length; j++) {
				Integer c = (Integer) countTable[i][j];

				// use N1 for unseen bigrams
				if (c.intValue() == 0) {
					fillTable[i][j] = 1.0 * N[1] / totalFreq;
				} else if (c > K) {
					// keep the old count to avoid N[c + 1] == 0
					fillTable[i][j] = c / (1.0 * totalFreq);
				} else {
					fillTable[i][j] = 1.0 * ((Integer) countTable[i][j] + 1)
							* N[c + 1] / N[c] / totalFreq;
				}
			}
		}

		return fillTable;
	}

	public Double getTotal(Object[][] table, String[] words) {
		Double totalProp = 1.0;
		for (int i = 0; i < words.length - 1; i++) {
			int j = 1;
			while (j < table.length && !((String) table[j][0]).equals(words[i])) {
				j++;
			}

			if (j >= table.length) {
				System.err.println("invalid sentence!");
				return null;
			}

			
			int k = 1;
			while (k < table.length
					&& !((String) table[0][k]).equals(words[i + 1])) {
				k++;
			}

			if (k >= table.length) {
				System.err.println("invalid sentence!");
				return null;
			}

			// to calculate the total bigram probability .
			totalProp *= ((Double) table[j][k]);
		}

		return totalProp;
	}

	@Override
	public String toString() {
		// output bigram in a descendant order by frequency.
		StringBuffer buf = new StringBuffer();
		ValueComparator comp = new ValueComparator(bigramCount);
		TreeMap<Bigram, Integer> sortedBiCount = new TreeMap<Bigram, Integer>(
				comp);
		sortedBiCount.putAll(bigramCount);
		for (Bigram bigram : sortedBiCount.keySet()) {
			buf.append("[" + bigram.getWord1() + ", " + bigram.getWord2()
					+ "]: " + bigramCount.get(bigram) + '\n');
		}
		return buf.toString();
	}

	private class ValueComparator implements Comparator<Bigram> {

		Map<Bigram, Integer> base;

		public ValueComparator(Map<Bigram, Integer> base) {
			this.base = base;
		}

		public int compare(Bigram a, Bigram b) {
			if (base.get(a) >= base.get(b)) {
				return -1;
			} else {
				return 1;
			} 
		}
	}
}
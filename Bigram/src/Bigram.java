public class Bigram {
	private String word1;

	private String word2;

	public Bigram() {
	}

	public Bigram(String word_1, String word_2) {
		this.setWord1(word_1);
		this.setWord2(word_2);
	}

	public String getWord1() {
		return word1;
	}

	public void setWord1(String word1) {
		this.word1 = word1;
	}

	public String getWord2() {
		return word2;
	}

	public void setWord2(String word2) {
		this.word2 = word2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word1 == null) ? 0 : word1.hashCode());
		result = prime * result + ((word2 == null) ? 0 : word2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object bmw) {
		if (this == bmw) {
			return true;
		}
		if (bmw == null) {
			return false;
		}
		if (!(bmw instanceof Bigram)) {
			return false;
		}
		Bigram otherwd = (Bigram) bmw;
		if (word1 == null) {
			if (otherwd.word1 != null) {
				return false;
			}
		} else if (!word1.equals(otherwd.word1)) {
			return false;
		}
		if (word2 == null) {
			if (otherwd.word2 != null) {
				return false;
			}
		} else if (!word2.equals(otherwd.word2)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Bigram [word1=" + word1 + ", word2=" + word2 + "]";
	}
}
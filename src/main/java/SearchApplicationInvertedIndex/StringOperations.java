package SearchApplicationInvertedIndex;

public class StringOperations {

	/**
	 * Removes the punctuation marks and converts the term to lower case.
	 * 
	 * @param term - The term to remove punctuation marks and convert to lower case.
	 * @return - The processed term. 
	 */
	public static String processTerm(String term) {
		return term = term.replaceAll("[\\p{Punct}]","").toLowerCase();
	}

	/**
	 * Combine arrays of string to a single array of string.
	 * 
	 * @param question - The first arrays of string.
	 * @param answer - The first arrays of string.
	 * @return - The combined array of string.
	 */
	public static String[] combineStrings(String[] question, String[] answer) {
		String[] questionAnswer = new String[question.length + answer.length];
		System.arraycopy(question, 0, questionAnswer, 0, question.length);
		System.arraycopy(answer, 0, questionAnswer, question.length, answer.length);
		return questionAnswer;
	}

	/**
	 * Splits the line by one or more spaces.
	 * 
	 * @param line - The line to be split
	 * @return - The array of string split by one or more spaces.
	 */
	public static String[] split(String line) {
		String[] splitLine = line.split("\\s+");
		return splitLine;
	}
}

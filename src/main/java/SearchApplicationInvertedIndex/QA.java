package SearchApplicationInvertedIndex;

/**
 *
 * Contains fields for storing relevant information when reading the json file for Question & Answer.
 */

public class QA {
	public static long counter = 1;
	private long id;
	private String asin;
	private String question;
	private String answer;

	/**
	 * Constructor
	 */
	public QA(){
		this.id = counter++;
		this.asin = "";
		this.question = "";
		this.answer = "";
	}

	public long getId() {
		return id;
	}

	public String getAsin() {
		return asin;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}

	public String toString()
	{
		return "\nQA id: "+this.id+"\nAsin: "+this.asin+"\nQuestion: "+this.question+"\nAnswer: "+this.answer;
	}
}
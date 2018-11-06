package SearchApplicationInvertedIndex;

/**
 *
 * Contains fields for storing relevant information when reading the Json file for Reviews.
 */

public class Review {
	public static long counter = 1;
	private long id;
	private String reviewerID;
	private String asin;
	private String reviewText;

	/**
	 * Constructor
	 */
	public Review(){
		this.id = counter++;
		this.reviewerID = "";
		this.asin = "";
		this.reviewText = "";
	}

	public String getReviewerID() {
		return reviewerID;
	}


	public String getAsin() {
		return asin;
	}

	public String getReviewText() {
		return reviewText;
	}

	public String toString()
	{
		return "Review ID: "+this.id+"\nReviewerID: "+this.reviewerID+"\nAsin: "+this.asin+"\nReviewText: "+this.reviewText;
	}
}

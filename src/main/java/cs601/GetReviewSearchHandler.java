package cs601;

import java.util.ArrayList;

import SearchApplicationInvertedIndex.InvertedIndex;
import SearchApplicationInvertedIndex.Review;
import SearchApplicationInvertedIndex.StringOperations;

public class GetReviewSearchHandler {

	private InvertedIndex index;
	private ArrayList<Review> reviews;

	public GetReviewSearchHandler(InvertedIndex index ) {
		this.index = index;
	}

	
	public String htmlBuilder() {
//		System.out.println("**************************");
//		System.out.println("\nIn GetReviewSearchHandler's htmlBuilder() method...........");
		StringBuilder htmlBuilder = new StringBuilder();
		if(this.reviews != null) {
			System.out.println("Reviews not null");
			htmlBuilder.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
			htmlBuilder.append("<head><title>TEST</title></head>\n");
			htmlBuilder.append("<body>");
			htmlBuilder.append("<h2>Review Search Result</h2>");
			htmlBuilder.append("<table id=\"t01\">");
			htmlBuilder.append("<tr>");
			htmlBuilder.append("<th>");
			htmlBuilder.append("Review ID");
			htmlBuilder.append("</th>");
			htmlBuilder.append("<th>");
			htmlBuilder.append("Review Text:");
			htmlBuilder.append("</th>");
			htmlBuilder.append("<th>");
			htmlBuilder.append("Asin:");
			htmlBuilder.append("</th>");
			htmlBuilder.append("</tr>");
			for(Review review : this.reviews) {
				if(review != null) {
					htmlBuilder.append("<tr>");
					htmlBuilder.append("<td>");
					htmlBuilder.append(review.getReviewerID());
					htmlBuilder.append("</td>");
					htmlBuilder.append("<td>");
					htmlBuilder.append(review.getReviewText());
					htmlBuilder.append("</td>");
					htmlBuilder.append("<td>");
					htmlBuilder.append(review.getAsin());
					htmlBuilder.append("</td>");
					htmlBuilder.append("</tr>");
				}
			}
			htmlBuilder.append("</table>");
			htmlBuilder.append("</body>");
			htmlBuilder.append("</html>");

		}
		else {
			htmlBuilder.append("Review not found please try again..");
		}
		String html = htmlBuilder.toString();
		return html;
	}

	
	public void handle() {
		// TODO Auto-generated method stub
	}

	
	public String handle(String query) {
//		System.out.println("**************************");
//		System.out.println("Query is: "+query);
//		System.out.println("**************************");
		String html = "";
		if(!query.trim().equals("")) {
			String[] splitLine = query.split("=");
			String term = splitLine[1];
			this.reviews = index.search(StringOperations.processTerm(term));
			html = htmlBuilder();
		}
		return html;
	}
}
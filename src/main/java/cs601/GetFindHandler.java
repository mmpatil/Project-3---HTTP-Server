package cs601;

import java.util.ArrayList;

public class GetFindHandler{

//	private InvertedIndex index;
//	private ArrayList<Review> reviews;
//
//	public GetFindHandler(InvertedIndex index ) {
//		this.index = index;
//	}
//	
//	@Override
//	public void handle() {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public String handle(String query) {
//		String html = "";
//		if(!query.trim().equals("")) {
//			String[] splitLine = query.split("=");
//			String term = splitLine[1];
//			this.reviews = index.getAsinDetails(term.toUpperCase());
//			html = htmlBuilder();
//		}
//		return html;
//	}
//
//	@Override
//	public String htmlBuilder() {
//		System.out.println("!!!!!!!!!!!!!!!");
//		System.out.println("\nIn GetFindHandler's htmlBuilder() method...........");
//		StringBuilder htmlBuilder = new StringBuilder();
//		htmlBuilder.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
//		htmlBuilder.append("<head><title>TEST</title></head>\n");
//		htmlBuilder.append("<body>");
//		if(this.reviews != null) {
//			htmlBuilder.append("<h2>Asin Find Result:</h2>");
//			htmlBuilder.append("<table id=\"t01\">");
//			htmlBuilder.append("<tr>");
//			htmlBuilder.append("<th>");
//			htmlBuilder.append("Asin:");
//			htmlBuilder.append("</th>");
//			htmlBuilder.append("<th>");
//			htmlBuilder.append("Review Text:");
//			htmlBuilder.append("</th>");
//			htmlBuilder.append("<th>");
//			htmlBuilder.append("Reviewer ID:");
//			htmlBuilder.append("</th>");
//			htmlBuilder.append("</tr>");
//			for(Review review : this.reviews) {
//				if(review != null) {
//					htmlBuilder.append("<tr>");
//					htmlBuilder.append("<td>");
//					htmlBuilder.append(review.getAsin());
//					htmlBuilder.append("</td>");
//					htmlBuilder.append("<td>");
//					htmlBuilder.append(review.getReviewText());
//					htmlBuilder.append("</td>");
//					htmlBuilder.append("<td>");
//					htmlBuilder.append(review.getReviewerID());
//					htmlBuilder.append("</td>");
//					htmlBuilder.append("</tr>");
//				}
//			}
//			htmlBuilder.append("</table>");
//		}
//		else {
//			htmlBuilder.append("<h2>Asin not found. please try again....<h2>");
//		}
//		htmlBuilder.append("</body>");
//		htmlBuilder.append("</html>");
//		String html = htmlBuilder.toString();
//		return html;
//	}
}

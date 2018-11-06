package cs601;

import java.util.ArrayList;

import SearchApplicationInvertedIndex.QA;
import SearchApplicationInvertedIndex.Review;

public class HtmlBuilder {

	private String title;
	private String labelName;
	private String action;
	private String method;
	private String id;
	private ArrayList<Review> reviews;
	private ArrayList<QA> qas;


	public HtmlBuilder() {
		this.title = "";
		this.labelName = "";
		this.action = "";
		this.method = "";
		this.id = "";
		this.reviews = null;
	}

	public void setQas(ArrayList<QA> qas) {
		this.qas = qas;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setReviews(ArrayList<Review> reviews) {
		this.reviews = reviews;
	}

	public String getTitle() {
		return title;
	}

	public String getLabelName() {
		return labelName;
	}

	public String getAction() {
		return action;
	}

	public String getMethod() {
		return method;
	}

	public String getId() {
		return id;
	}
	public ArrayList<QA> getQas() {
		return qas;
	}

	public ArrayList<Review> getReviews() {
		return reviews;
	}

	public StringBuilder head() {
		StringBuilder html = new StringBuilder();

		html.append("<html lang=\"en\">");
		html.append("<head>");
		html.append("<meta charset=\"utf-8\"/>");
		html.append("<title>"+title+"</title>");
		html.append("</head>");

		return html;
	}

	public StringBuilder startBody(StringBuilder html) {
		html.append("<body>");
		return html;
	}
	public StringBuilder createForm(StringBuilder html) {
		
		html.append("<form action=" + action + " method=" + method +">");
		html.append("<label for = \""+labelName+"\">"+ labelName +"</label>");
		html.append("<input name=\""+id+"\" id=\""+id+"\" />");
		html.append("<input type=\"submit\"></input>");
		html.append("</form>");
		return html;
	}

	public StringBuilder createTableForReview(StringBuilder html) {
		html.append("<body>");
		if(this.reviews != null) {
			html.append("<table style=\"border-top: 5px solid red;\" id=\""+ id +"\">");
			html.append("<tr>");
			html.append("<tr>");
			html.append("<th>");
			html.append("Review ID");
			html.append("</th>");
			html.append("<th>");
			html.append("Review Text:");
			html.append("</th>");
			html.append("<th>");
			html.append("Asin:");
			html.append("</th>");
			html.append("</tr>");
			for(Review review : this.reviews) {
				if(review != null) {
					html.append("<tr>");
					html.append("<td>");
					html.append(review.getReviewerID());
					html.append("</td>");
					html.append("<td>");
					html.append(review.getReviewText());
					html.append("</td>");
					html.append("<td>");
					html.append(review.getAsin());
					html.append("</td>");
					html.append("</tr>");
				}
			}
			html.append("</table>");
		}
		else {
			html.append("<h3>Search not found</h3>");
		}
		//		html.append("</body>");
		//		html.append("</html>");
		return html;
	}
	
	public StringBuilder createTableForQAs(StringBuilder html) {
		html.append("<br/>");
		html.append("<br/>");
		html.append("<br/>");
		if(this.qas != null) {
			html.append("<table style=\"border-top: 5px solid red;\" id=\""+ id +"\">");
			html.append("<tr>");
			html.append("<tr>");
			html.append("<th>");
			html.append("Question:");
			html.append("</th>");
			html.append("<th>");
			html.append("Answer:");
			html.append("</th>");
			html.append("<th>");
			html.append("Asin:");
			html.append("</th>");
			html.append("</tr>");
			for(QA qa : this.qas) {
				if(qa != null) {
					html.append("<tr>");
					html.append("<td>");
					html.append(qa.getQuestion());
					html.append("</td>");
					html.append("<td>");
					html.append(qa.getAnswer());
					html.append("</td>");
					html.append("<td>");
					html.append(qa.getAsin());
					html.append("</td>");
					html.append("</tr>");
				}
			}
			html.append("</table>");
		}
		else {
			html.append("<h3>Search not found</h3>");
		}
		//		html.append("</body>");
		//		html.append("</html>");
		return html;
	}

	public StringBuilder closeHtml(StringBuilder html){
		html.append("</body>");
		html.append("</html>");
		return html;
	}

	public StringBuilder methodNotFoundPage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Method Not Found</h3>");
		html.append("</body>");
		html.append("</html>");
		return html;
	}

	public StringBuilder slackResponsePage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Message is sent</h3>");
		html.append("<br/>");
		html.append("<br/>");

		return html;
	}

	public StringBuilder slackInvalidResponsePage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Message not sent</h3>");
		html.append("<br/>");
		html.append("<br/>");

		return html;
	}

	public StringBuilder pageNotFoundPage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Page Not Found</h3>");
		html.append("</body>");
		html.append("</html>");
		return html;
	}
	
	public StringBuilder badRequestPage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Bad Request</h3>");
		html.append("</body>");
		html.append("</html>");
		return html;
	}
}
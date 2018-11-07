package cs601;

import java.util.ArrayList;

import org.apache.commons.lang3.StringEscapeUtils;

import SearchApplicationInvertedIndex.QA;
import SearchApplicationInvertedIndex.Review;

/**
 * @author manalipatil
 *
 */
public class HtmlBuilder {

	private String title;
	private String labelName;
	private String action;
	private String method;
	private String id;
	private ArrayList<Review> reviews;
	private ArrayList<QA> qas;

	/**
	 * Constructor
	 */
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

	
	/**
	 * Generates head of the response html
	 * @return StringBuilder to be updated
	 */
	public StringBuilder head() {
		StringBuilder html = new StringBuilder();

		html.append("<html lang=\"en\">");
		html.append("<head>");
		html.append("<meta charset=\"utf-8\"/>");
		html.append("<title>"+title+"</title>");
		html.append("</head>");

		return html;
	}

	/**
	 * Appends <body> to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return updated StringBuilder  
	 */
	public StringBuilder startBody(StringBuilder html) {
		html.append("<body>");
		return html;
	}
	
	/**
	 * Appends form to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return updated StringBuilder  
	 */
	public StringBuilder createForm(StringBuilder html) {
		
		html.append("<form action=\"" + action +"\" method=\"" + method +"\">");
		html.append("<label for = \""+labelName+"\">"+ labelName +"</label>");
		html.append("<input name=\""+id+"\" id=\""+id+"\"></input>");
		html.append("<input type=\"submit\"></input>");
		html.append("</form>");
		return html;
	}

	/**
	 * Appends table for Review Search to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return updated StringBuilder  
	 */
	public StringBuilder createTableForReview(StringBuilder html) {
		html.append("<body>");
		if(this.reviews != null) {
			html.append("<table id=\""+ id +"\">");
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
					html.append(StringEscapeUtils.escapeHtml4(review.getReviewText()));
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
		return html;
	}
	
	/**
	 * Appends Table for QA's to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return updated StringBuilder  
	 */
	public StringBuilder createTableForQAs(StringBuilder html) {
		html.append("<br/>");
		html.append("<br/>");
		html.append("<br/>");
		if(this.qas != null) {
			html.append("<table id=\""+ id +"\">");
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
					html.append(StringEscapeUtils.escapeHtml4(qa.getQuestion()));
					html.append("</td>");
					html.append("<td>");
					html.append(StringEscapeUtils.escapeHtml4(qa.getAnswer()));
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
		return html;
	}

	/**
	 * Appends close html to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return complete html  
	 */
	public StringBuilder closeHtml(StringBuilder html){
		html.append("</body>");
		html.append("</html>");
		return html;
	}

	/**
	 * Appends method not found html to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return complete html  
	 */
	public StringBuilder methodNotFoundPage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Method Not Found</h3>");
		html.append("</body>");
		html.append("</html>");
		return html;
	}

	/**
	 * Appends valid slack response html to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return complete html  
	 */
	public StringBuilder slackResponsePage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Message is sent</h3>");
		html.append("<br/>");
		html.append("<br/>");

		return html;
	}

	/**
	 * Appends invalid slack response html to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return complete html  
	 */
	public StringBuilder slackInvalidResponsePage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Message not sent</h3>");
		html.append("<br/>");
		html.append("<br/>");

		return html;
	}

	/**
	 * Appends page not found html to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return complete html  
	 */
	public StringBuilder pageNotFoundPage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Page Not Found</h3>");
		html.append("</body>");
		html.append("</html>");
		return html;
	}
	
	/**
	 * Appends bad request html to StringBuilder
	 * @param html StringBuilder to be updated
	 * @return complete html  
	 */
	public StringBuilder badRequestPage(StringBuilder html) {
		html.append("<body>");
		html.append("<h3>Bad Request</h3>");
		html.append("</body>");
		html.append("</html>");
		return html;
	}
}
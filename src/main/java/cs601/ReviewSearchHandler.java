package cs601;

import java.util.logging.Level;

import SearchApplicationInvertedIndex.InvertedIndex;
import SearchApplicationInvertedIndex.StringOperations;

public class ReviewSearchHandler implements Handler{

	private InvertedIndex index;

	public ReviewSearchHandler(InvertedIndex index ) {
		this.index = index;
	}

	/*
	 * (non-Javadoc)
	 * @see cs601.Handler#handle(cs601.HTTPRequest, cs601.HTTPResponse)
	 */
	@Override
	public void handle(HTTPRequest request,HTTPResponse response) {
		if(request.getMethod().equals(HttpConstants.GET)) {
			this.doGet(request,response);
		}
		else if(request.getMethod().equals(HttpConstants.POST)) {
			this.doPost(request,response);
		}
		else {
			ErrorPages.methodNotFoundHtml(response);
		}
	}

	/**
	 * Builds the form if the HTTPMethod is GET
	 * 
	 * @param request Object containing information
	 * @param response {@link HTTPResponse} response to be updated
	 */
	public void doGet(HTTPRequest request,HTTPResponse response) {
		(MyLogger.getLogger()).log(Level.INFO, "This is get method");

		HtmlBuilder html = new HtmlBuilder();
		html.setAction(request.getPath());
		html.setMethod(HttpConstants.POST);
		html.setTitle("Review Search");
		html.setLabelName("Review Search");
		html.setId("query");
		
		StringBuilder formBuilder = html.head();
		formBuilder = html.startBody(formBuilder);
		formBuilder = html.createForm(formBuilder);
		formBuilder = html.closeHtml(formBuilder);
		response.setHtmlPage(formBuilder.toString());
		response.setHeader(HttpConstants.OK_HEADER + "Content-Lenght: " + ErrorPages.getContentLenght(formBuilder.toString())+"\n\r\n");
	}

	/**
	 * Builds the table if the HTTPMethod is POST with the searched asin result
	 * 
	 * @param request Object containing information
	 * @param response {@link HTTPResponse} response to be updated
	 */
	public void doPost(HTTPRequest request,HTTPResponse response) {
		(MyLogger.getLogger()).log(Level.INFO, "This is post method");
		HtmlBuilder html = new HtmlBuilder();
		html.setId("table01");
		html.setTitle("Review search Response");
		if(!request.getQuery().trim().equals("")) {
			html.setReviews(index.searchAll(request.getQuery().trim()));
			StringBuilder tableBuilder = html.head();
			tableBuilder = html.createTableForReview(tableBuilder);
			tableBuilder = html.closeHtml(tableBuilder);
			response.setHtmlPage(tableBuilder.toString());
			response.setHeader(HttpConstants.OK_HEADER + "Content-Lenght: " + ErrorPages.getContentLenght(tableBuilder.toString())+"\n\r\n");
		}
		else {
			(MyLogger.getLogger()).log(Level.INFO, "This is post method bad request");
			ErrorPages.badRequestHtml(response);
		}
		
	}
}
package cs601;

import java.util.logging.Level;

import SearchApplicationInvertedIndex.InvertedIndex;
import SearchApplicationInvertedIndex.StringOperations;

public class ReviewSearchHandler implements Handler{

	private InvertedIndex index;

	public ReviewSearchHandler(InvertedIndex index ) {
		this.index = index;
	}

	@Override
	public void handle(HTTPRequest request,HTTPResponse response) {
		if(request.getMethod().equals(HttpConstants.GET)) {
			this.doGet(request,response);
		}
		else if(request.getMethod().equals(HttpConstants.POST)) {
			this.doPost(request,response);
		}
		else {
			this.doMethodNotFound(response);
		}
	}

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
		response.setHeader(HttpConstants.OK_HEADER);
	}

	public void doPost(HTTPRequest request,HTTPResponse response) {
		(MyLogger.getLogger()).log(Level.INFO, "This is post method");
		HtmlBuilder html = new HtmlBuilder();
		html.setId("table01");
		html.setTitle("Review search Response");
		if(!request.getQuery().trim().equals("")) {
			html.setReviews(index.searchAll(request.getQuery().trim()));
		}
		else {
			(MyLogger.getLogger()).log(Level.INFO, "This is post method bad request");
			badRequestHtml(response);
		}
		StringBuilder tableBuilder = html.head();
		tableBuilder = html.createTableForReview(tableBuilder);
		response.setHtmlPage(tableBuilder.toString());
		response.setHeader(HttpConstants.OK_HEADER);
	}

	public void doMethodNotFound(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Method Not Found");
		StringBuilder methodNotFoundBuilder = html.head();
		methodNotFoundBuilder = html.methodNotFoundPage(methodNotFoundBuilder);
		response.setHtmlPage(methodNotFoundBuilder.toString());
		response.setHeader(HttpConstants.METHOD_NOT_FOUND_HEADER);
	}
	
	public void badRequestHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Bad Request");
		StringBuilder badRequestBuilder = html.head();
		badRequestBuilder = html.badRequestPage(badRequestBuilder);
		response.setHtmlPage(badRequestBuilder.toString());
		response.setHeader(HttpConstants.BAD_REQUEST);
	}
}
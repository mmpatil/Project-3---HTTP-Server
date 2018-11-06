package cs601;

import java.util.logging.Level;

import SearchApplicationInvertedIndex.InvertedIndex;

public class FindHandler implements Handler{

	private InvertedIndex index;
	
	public FindHandler(InvertedIndex index ) {
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
		html.setTitle("Asin Search");
		html.setLabelName("Asin Search");
		html.setId("asin");
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
		html.setTitle("Asin search Response");
		if(!request.getQuery().trim().equals("")) {
			html.setReviews(index.getReviewsAndQAsForReviews((request.getQuery().trim()).toUpperCase()));
			html.setQas(index.getAsinDetailsForQA((request.getQuery().trim()).toUpperCase()));
		}
		else {
			(MyLogger.getLogger()).log(Level.INFO, "This is post method bad request");
			badRequestHtml(response);
		}
		StringBuilder tableBuilder = html.head();
		tableBuilder = html.createTableForReview(tableBuilder);
		tableBuilder = html.createTableForQAs(tableBuilder);
		tableBuilder = html.closeHtml(tableBuilder);
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

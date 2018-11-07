package cs601;
import cs601.HTTPResponse;
import cs601.HtmlBuilder;
import cs601.HttpConstants;

public class ErrorPages {
	
	/**
	 * Builds a form for Page Not Found
	 * @param response the object to be updated 
	 */
	public static void pageNotFoundHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Page Not Found");
		StringBuilder pageNotFound = html.head();
		pageNotFound = html.pageNotFoundPage(pageNotFound);
		response.setHtmlPage(pageNotFound.toString());
		response.setHeader(HttpConstants.PAGE_NOT_FOUND_HEADER + "Content-Lenght: "+ ErrorPages.getContentLenght(pageNotFound.toString())+"\n\r\n");
	}

	/**
	 * Builds a form for Method Not Found
	 * @param response the object to be updated 
	 */
	public static void MethodNotFoundHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Method Not Found");
		StringBuilder methodNotFoundBuilder = html.head();
		methodNotFoundBuilder = html.methodNotFoundPage(methodNotFoundBuilder);
		
		response.setHtmlPage(methodNotFoundBuilder.toString());
		response.setHeader(HttpConstants.METHOD_NOT_FOUND_HEADER + "Content-Lenght: "+ ErrorPages.getContentLenght(methodNotFoundBuilder.toString())+"\n\r\n");
	}

	/**
	 * Builds a form for Bad Request
	 * @param response the object to be updated 
	 */
	public static void BadRequestHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Bad Request");
		StringBuilder badRequestBuilder = html.head();
		badRequestBuilder = html.badRequestPage(badRequestBuilder);
		response.setHtmlPage(badRequestBuilder.toString());
		response.setHeader(HttpConstants.BAD_REQUEST+ "Content-Lenght: " + ErrorPages.getContentLenght(badRequestBuilder.toString())+"\n\r\n");
	}
	
	
	/**
	 * Get content lenght for the response object
	 * @param html the response html
	 * @return
	 */
	public static int getContentLenght(String html) {
		int contentLenght = 0;
		contentLenght = html.getBytes().length;
		return contentLenght;
	}
}

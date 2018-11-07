package cs601;

public interface Handler {

	
	/**
	 * Tells the handler how to deal with the specific request methods
	 * @param request Request object with details
	 * @param response Response object to be updated
	 */
	public void handle(HTTPRequest request,HTTPResponse response);
}

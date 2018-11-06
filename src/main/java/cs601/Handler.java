package cs601;

public interface Handler {

//	public void handle();
	public void handle(HTTPRequest request,HTTPResponse response);
}

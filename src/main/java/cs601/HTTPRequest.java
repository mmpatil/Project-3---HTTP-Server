package cs601;

public class HTTPRequest {

	private String requestLine;
	private String method;
	private String path;
	private String query;
	private String body;
	private String httpVersion;
	private String headers;
	private boolean badRequest;
	private int contentLenght;	

	public HTTPRequest() {
		this.requestLine = "";
		this.method = "";
		this.path = "";
		this.query = "";
		this.httpVersion = "";
		this.headers = "";
		this.contentLenght = 0;
		this.badRequest = false;
		this.body = "";
	}

	public boolean isBadRequest() {
		return badRequest;
	}

	public void setBadRequest(boolean badRequest) {
		this.badRequest = badRequest;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setRequestLine(String requestLine) {
		this.requestLine = requestLine;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setHttpVersion(String httpVersion) {
		this.httpVersion = httpVersion;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public void setContentLenght(int contentLenght) {
		this.contentLenght = contentLenght;
	}
	public String getRequestLine() {
		return requestLine;
	}

	public String getBody() {
		return body;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getQuery() {
		return query;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public String getHeaders() {
		return headers;
	}

	public int getContentLenght() {
		return contentLenght;
	}

}
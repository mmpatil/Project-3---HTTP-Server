package cs601;

public class RequestParser {

	private String method;
	private String header;
	private String query;
//	private String type;
	private String connection;

	public RequestParser(String request) {
		String[] splitLine = request.split("/");
		this.method = splitLine[0];
		this.header = splitLine[1];
		this.query = splitLine[2];
	}
	
	public String getMethod() {
		return this.method;
	}

	public String getHeader() {
		return this.header;
	}

	public String getQuery() {
		return this.query;
	}

	public String getConnection() {
		return this.connection;
	}
}

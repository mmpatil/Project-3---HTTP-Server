package cs601;

import java.util.HashMap;
import java.util.logging.Level;

public class RequestParser {

	private String[] requestLineParts;
	private String[] pathAndQueries;
	private String[] queries;

	private HashMap<String,String> queriesHashmap = new HashMap<String,String>();

	public RequestParser() {

	}

	/**
	 * Constructor parsing the request
	 * @param httpRequest the request object to be parsed
	 */
	public RequestParser(HTTPRequest httpRequest) {

		if(isValidRequestLine(httpRequest.getRequestLine())) {
			this.requestLineParts = split(httpRequest.getRequestLine()," ");

			//set request method
			if(!this.requestLineParts[0].trim().equals("")) {
				httpRequest.setMethod(this.requestLineParts[0]);
			}
			//check if request line contains queries
			if(checkifContains(this.requestLineParts[1], "?") == true){
				if(isValidQuery(this.requestLineParts[1])) {
					this.pathAndQueries = split(this.requestLineParts[1],"\\?");
					if(!this.pathAndQueries[0].trim().equals("")) {
						httpRequest.setPath(this.pathAndQueries[0]);
					}
					if(checkifContains(this.pathAndQueries[1],"&")) {
						parseMultipleQueries(this.pathAndQueries[1],httpRequest);
					}
					else if(checkifContains(this.pathAndQueries[1], "=")) {
						parseSingleQueries(this.pathAndQueries[1],httpRequest);
					}
				}
			}
			else {
				if(!this.requestLineParts[1].trim().equals("")) {
					httpRequest.setPath(this.requestLineParts[1]);
				}
			}
			if(!this.requestLineParts[2].trim().equals("")) {
				checkHTTPVersion(requestLineParts[2],httpRequest);
			}
			
			//parse the body if the request contains body
			parseBody(httpRequest);

			setQueryforRequest(httpRequest);

		}else {
			httpRequest.setBadRequest(true);
		}
	}

	/**
	 * 
	 * @param httpRequest to be set
	 */
	private void setQueryforRequest(HTTPRequest httpRequest) {
		if(httpRequest.getPath().equals(SearchApplicationConstants.REVIEW_SEARCH_PATH)) {
			if(queriesHashmap.containsKey(SearchApplicationConstants.QUERY)) {
				httpRequest.setQuery(queriesHashmap.get(SearchApplicationConstants.QUERY));
			}
		}
		else if(httpRequest.getPath().equals(SearchApplicationConstants.ASIN_PATH)) {
			if(queriesHashmap.containsKey(SearchApplicationConstants.ASIN)) {
				httpRequest.setQuery(queriesHashmap.get(SearchApplicationConstants.ASIN));
			}
		}
		else if(httpRequest.getPath().equals(SearchApplicationConstants.SLACK_BOT_PATH)) {
			if(queriesHashmap.containsKey(SearchApplicationConstants.MESSAGE)) {
				httpRequest.setQuery(queriesHashmap.get(SearchApplicationConstants.MESSAGE));
			}
		}
	}

	/**
	 * Checks the HTTP version
	 * @param line containg the HTTPVersion
	 * @param httpRequest the request to be updated
	 * @return
	 */
	public boolean checkHTTPVersion(String line,HTTPRequest httpRequest) {
		boolean result = false;
		if(line.equals("HTTP/1.0\r") || line.equals("HTTP/1.1\r") ||line.equals("HTTP/1.0") || line.equals("HTTP/1.1") ) {
			httpRequest.setHttpVersion(line);
			result = true;
		}
		return result;
	}

	/**
	 * Parses the body
	 * @param httpRequest the request to be updated
	 */
	public void parseBody(HTTPRequest httpRequest) {
		if(!httpRequest.getBody().trim().equals("")) {
			String postBody = httpRequest.getBody();
			if(postBody.contains("&")) {
				this.queries = split(postBody,"&");
				if(this.queries!=null) {
					for(String query:queries) {
						if(checkifContains(query, "=")){
							String[] keyValue = split(query,"=");
							if(keyValue.length == 2) {
								this.queriesHashmap.put(keyValue[0], keyValue[1]);
							}
						}
					}
				}
			}else if(checkifContains(postBody, "=")){
				String[] keyValue = split(postBody,"=");
				if(keyValue.length == 2) {
					if(keyValue[0].equals("query")) {
						this.queriesHashmap.put(keyValue[0], keyValue[1]);
					}else if(keyValue[0].equals("asin")){
						this.queriesHashmap.put(keyValue[0], keyValue[1]);
					}else if(keyValue[0].equals("message")) {
						this.queriesHashmap.put(keyValue[0], keyValue[1]);
					}
				}
			}
		}
	}

	/**
	 * Parses multiple queries
	 * @param line containing multiple queries
	 * @param httpRequest to be updated
	 * @return
	 */
	public boolean parseMultipleQueries(String line,HTTPRequest httpRequest) {
		boolean result = false;
		if(checkifContains(line,"&")) {
			this.queries = split(line,"&");
			if(this.queries!=null) {
				for(String query:queries) {
					if(checkifContains(query, "=")){
						String[] keyValue = split(query,"=");
						if(keyValue.length == 2) {
							this.queriesHashmap.put(keyValue[0], keyValue[1]);
							result = true;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Parses single query
	 * @param line containing single queries
	 * @param httpRequest to be updated
	 * @return
	 */
	public boolean parseSingleQueries(String line,HTTPRequest httpRequest) {
		boolean result = false;
		if(checkifContains(line,"=")) {
			String[] keyValue = split(line,"=");
			if(keyValue.length == 2) {
				this.queriesHashmap.put(keyValue[0], keyValue[1]);
				result = true;
			}
		}
		return result;
	}

	/**
	 * check if request Line is valid
	 * @param requestLine to be verified
	 * @return
	 */
	public boolean isValidRequestLine(String requestLine) {
		boolean result = false;
		if((requestLine.split(" ")).length == 3) {
			result = true;
		}
		return result;
	}

	/**
	 * Split the line for the regex
	 * @param line line to be split
	 * @param regex the split parameter
	 * @return
	 */
	public String[] split (String line, String regex) {
		return line.split(regex);	
	}

	/**
	 * Check if the line contains the string
	 * @param line line to check
	 * @param regex the split parameter
	 * @return
	 */
	public boolean checkifContains(String line,String regex) {
		boolean result = false;
		if(line.contains(regex)) {
			result = true;
		}
		return result;
	}

	/**
	 * check if request Query is valid
	 * @param line to be verified
	 * @return
	 */
	public boolean isValidQuery(String line) {
		boolean result = false;
		if((line.split("\\?")).length == 2) {
			result = true;
		}
		return result;
	}
}
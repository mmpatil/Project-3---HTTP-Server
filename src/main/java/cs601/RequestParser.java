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

	public RequestParser(HTTPRequest httpRequest) {

		//		if(isValidRequestLine(httpRequest.getRequestLine())) {
		//			this.requestLineParts = split(httpRequest.getRequestLine()," ");
		//
		//			//set request method
		//			httpRequest.setMethod(this.requestLineParts[0]);
		//
		//			//check if request line contains queries
		//			if(checkifContains(this.requestLineParts[1], "\\?") == true){
		//				if(isValidQuery(this.requestLineParts[1])) {
		//					this.pathAndQueries = split(this.requestLineParts[1],"?");
		//					httpRequest.setPath(this.pathAndQueries[0]);
		//					if(checkifContains(this.pathAndQueries[1],"&")) {
		//						parseMultipleQueries(this.pathAndQueries[1],httpRequest);
		//					}
		//					else 
		//					{
		//						if(checkifContains(this.pathAndQueries[1],"=")) {
		//							this.queries = split(this.pathAndQueries[1],"=");
		//							if(queries[0].equals(SearchApplicationConstants.QUERY)) {
		//								httpRequest.setQuery(queries[0]);
		//							}
		//						}
		//					}
		//				}
		//			}
		//			else {
		//				httpRequest.setPath(this.requestLineParts[1]);
		//			}
		//		}

		//check if contains queries
		//check if contains multiple queries
		//check if contains if valid query


		String[] splitLine = httpRequest.getRequestLine().split(" ");
		if(splitLine.length == 3) {
			httpRequest.setMethod(splitLine[0]);
			if(splitLine[1].contains("?")) {
				String[] pathAndQuery = splitLine[1].split("\\?");
				if(pathAndQuery.length == 2) {
					httpRequest.setPath(pathAndQuery[0]);
					if(pathAndQuery[1].contains("&")) {
						String[] queries = pathAndQuery[1].split("&");
						for(String query:queries) {
							if(query.contains("=")) {
								String[] queryValue = query.split("=");
								if(!this.queriesHashmap.containsKey(queryValue[0])) {
									this.queriesHashmap.put(queryValue[0], queryValue[1]);
								}
							}
						}
					}
				}
			}
			else {
				httpRequest.setPath(splitLine[1]);
			}
			
			if(splitLine[2].equals("HTTP/1.0\r") || splitLine[2].equals("HTTP/1.1\r") ||splitLine[2].equals("HTTP/1.0") || splitLine[2].equals("HTTP/1.1") ) {
				httpRequest.setHttpVersion(splitLine[2]);
			}else {
				(MyLogger.getLogger()).log(Level.INFO, "HTTP version not valid");
			}

			if(!httpRequest.getBody().trim().equals("")) {
				parseBody(httpRequest);
			}

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
		}else {
			(MyLogger.getLogger()).log(Level.INFO, "In parser set bad request");
			httpRequest.setBadRequest(true);
		}
	}

	private void parseBody(HTTPRequest httpRequest) {
		String postBody = httpRequest.getBody();
		if(postBody.contains("&")) {
			this.queries = split(postBody,"&");
			for(String query:queries) {
				if(checkifContains(query, "=")){
					String[] keyValue = split(query,"=");
					this.queriesHashmap.put(keyValue[0], keyValue[1]);
				}
			}
		}else if(checkifContains(postBody, "=")){
			String[] keyValue = split(postBody,"=");
			this.queriesHashmap.put(keyValue[0], keyValue[1]);
		}
	}

	public void parseMultipleQueries(String line,HTTPRequest httpRequest) {
		this.queries = split(line,"&");
		for(String query:queries) {
			if(checkifContains(query, "=")){
				String[] keyValue = split(query,"=");
				this.queriesHashmap.put(keyValue[0], keyValue[1]);
			}
		}
	}

	public void parseSingleQueries(String line,HTTPRequest httpRequest) {
		if(checkifContains(line,"=")) {
			String[] keyValue = split(line,"=");
			this.queriesHashmap.put(keyValue[0], keyValue[1]);
		}
	}

	public boolean isValidRequestLine(String requestLine) {
		boolean result = false;
		if((requestLine.split(" ")).length == 3) {
			result = true;
		}
		return result;
	}

	public String[] split (String line, String regex) {
		return line.split(regex);	
	}

	public boolean checkifContains(String line,String regex) {
		boolean result = false;
		if(line.contains(regex)) {
			result = true;
		}
		return result;
	}

	public boolean isValidQuery(String line) {
		boolean result = false;
		if((line.split("?")).length == 2) {
			result = true;
		}
		return result;
	}
}
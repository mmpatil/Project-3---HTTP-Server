package cs601;
import cs601.HTTPRequest;

public class MockObjects {

	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest getHttpRequest() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch HTTP/1.1");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest getHttpRequestWithQuery() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch?query=abc HTTP/1.1");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest getHttpRequestWithMultipleQueries() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch?query=abc&query2=dfg HTTP/1.1");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest checkIfGetHttpRequestValid() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("GET");
		request.setPath("/reviewsearch");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest postHttpRequest() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch HTTP/1.1");
		request.setBody("query=abc");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfPostHttpRequestValid() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("POST");
		request.setQuery("abc");
		request.setPath("/reviewsearch");
		request.setBody("query=abc");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}

	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest postHttpRequestWithQuery() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query=abc HTTP/1.1");
		request.setBody("query2=nono");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest postHttpRequestWithMultipleQueries() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query1=abc&query3=def HTTP/1.1");
		request.setBody("query=nono");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest postHttpRequestWithMultipleInvalidQueriesAndBody() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query1=abc&query3=def HTTP/1.1");
		request.setBody("query2=nono&qee=567");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest postHttpRequestWithInvalidVersion() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query1=abc&query3=def HTTP/2.1");
		request.setBody("query2=nono&qee=567");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest getHttpRequestWithInvalidVersion() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch?query1=abc&query3=def HTTP/2.1");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to parse
	 */
	public static HTTPRequest httpRequestWithInvalidRequestLine() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET   /reviewsearch?query1=abc&query3=def HTTP/2.1");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfHttpRequestWithInvalidRequestLine() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET   /reviewsearch?query1=abc&query3=def HTTP/2.1");
		request.setHttpVersion("");
		request.setBadRequest(true);
		request.setMethod("");
		request.setQuery("");
		request.setPath("");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfgetHttpRequestWithInvalidVersion() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch?query1=abc&query3=def HTTP/2.1");
		request.setHttpVersion("");
		request.setMethod("GET");
		request.setQuery("");
		request.setPath("/reviewsearch");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfPostHttpRequestWithInvalidVersion() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query1=abc&query3=def HTTP/2.1");
		request.setHttpVersion("");
		request.setBadRequest(true);
		request.setMethod("POST");
		request.setQuery("");
		request.setPath("/reviewsearch");
		request.setBody("query2=nono&qee=567");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfPostRequestWithInvalidMultipleQueriesAndBody() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query1=abc&query3=def HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("POST");
		request.setQuery("");
		request.setPath("/reviewsearch");
		request.setBody("query2=nono&qee=567");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfPostRequestWithMultipleQueriesValid() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query1=abc&query3=def HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("POST");
		request.setQuery("nono");
		request.setPath("/reviewsearch");
		request.setBody("query=nono");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfPostRequestWithQueryValid() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("POST /reviewsearch?query=abc HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("POST");
		request.setQuery("abc");
		request.setPath("/reviewsearch");
		request.setBody("query2=nono");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfGetHttpRequestWithQueryValid() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch?query=abc HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("GET");
		request.setQuery("abc");
		request.setContentLenght(10);
		request.setPath("/reviewsearch");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
	
	/**
	 * 
	 * @return mock HTTPRequest object to check the result of parsed request
	 */
	public static HTTPRequest checkIfGetHttpRequestWithMultipleQueryValid() {
		HTTPRequest request = new HTTPRequest();
		request.setRequestLine("GET /reviewsearch?query=abc&query2=dfg HTTP/1.1");
		request.setHttpVersion("HTTP/1.1");
		request.setMethod("GET");
		request.setQuery("abc");
		request.setPath("/reviewsearch");
		request.setHeaders("Host: localhost:9000\n" + 
				"Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\n" + 
				"Upgrade-Insecure-Requests: 1\n" + 
				"Cookie: username-localhost-8888=\"2|1:0|10:1541119866|23:username-localhost-8888|44:YmE4YTMxNzU0YjhkNDZjZDlhYTM3ZTc4OTZiNzVmYjg=|efc33a35a57f9a9145774460538a45fcc9fd0027c4d8adbf185e5d516ded0b09\"; _xsrf=2|8360c6e6|6531f539c8d8fda6e5393e3a57f01d1a|1541119705\n" + 
				"User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/11.1 Safari/605.1.15\n" + 
				"Accept-Language: en-us\n" + 
				"Accept-Encoding: gzip, deflate\n" + 
				"Connection: keep-alive");
		return request;
	}
}

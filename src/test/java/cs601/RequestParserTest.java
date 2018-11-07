package cs601;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RequestParserTest {

	//Unit Test check valid request line
	@Test
	public void testInValidRequestLine() {
		RequestParser parser = new RequestParser();
		assertFalse(parser.isValidRequestLine("GET    /reviewsearch HTTP1.0"));		
	}

	//Unit Test check request line
	@Test
	public void testValidRequestLine() {
		RequestParser parser = new RequestParser();
		assertTrue(parser.isValidRequestLine("GET /reviewsearch HTTP1.0"));		
	}

	//Unit Test check split line
	@Test
	public void testSplitValid() {
		RequestParser parser = new RequestParser();
		assertEquals(parser.split("Request Line"," ").length,2);		
	}

	//Unit Test check request line
	@Test
	public void testSplitNotValid() {
		RequestParser parser = new RequestParser();
		assertNotEquals(parser.split("Request Line"," ").length,3);		
	}

	//Unit Test valid http version
	@Test
	public void testIfValidHttpVersion() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertTrue(parser.checkHTTPVersion("HTTP/1.1", request));
	}

	//Unit Test http version
	@Test
	public void testIfInvalidHttpVersion() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertFalse(parser.checkHTTPVersion("HTTP/2.1", request));
	}

	//Unit Test check for multiple queries
	@Test
	public void testIfValidMultipleQueriesExist() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertTrue(parser.parseMultipleQueries("query=acb&query2=efg", request));
	}

	//Unit Test check for multiple queries
	@Test
	public void testIfInvalidMultipleQueriesExist() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertFalse(parser.parseMultipleQueries("query=ac", request));
	}

	//Unit Test check for single query
	@Test
	public void testIfSingleValidQueryExist() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertTrue(parser.parseSingleQueries("query=abc", request));
	}

	//Unit Test check for single query
	@Test
	public void testIfSingleInValidQueryExist() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertFalse(parser.parseSingleQueries("query=", request));
	}

	//Unit Test check for single query
	@Test
	public void testIfInvalidSingleQueryExist() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser();
		assertFalse(parser.parseSingleQueries("query", request));
	}

	//Unit Test:check if content length gives right value
	@Test
	public void testGetContentLenght() {
		int contentLenght = ErrorPages.getContentLenght("this is my page");
		assertEquals(Integer.toUnsignedLong(15), contentLenght);
	}

	//Unit Test:check if content length gives right value negative test case
	@Test
	public void testGetContentLenghtNegativeCase() {
		int contentLenght = ErrorPages.getContentLenght("this is my page");
		assertNotEquals(Integer.toUnsignedLong(10), contentLenght);
	}

	//Integration Test
	@Test
	public void testValidRequestParserGetWithoutQuery() {
		HTTPRequest request = MockObjects.getHttpRequest();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfGetHttpRequestValid();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserGetWithQuery() {
		HTTPRequest request = MockObjects.getHttpRequestWithQuery();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfGetHttpRequestWithQueryValid();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserGetWithMultipleQueries() {
		HTTPRequest request = MockObjects.getHttpRequestWithMultipleQueries();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfGetHttpRequestWithMultipleQueryValid();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserPost() {
		HTTPRequest request = MockObjects.postHttpRequest();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfPostHttpRequestValid();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserPostWithQuery() {
		HTTPRequest request = MockObjects.postHttpRequestWithQuery();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfPostRequestWithQueryValid();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserPostWithMultipleQueries() {
		HTTPRequest request = MockObjects.postHttpRequestWithMultipleQueries();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfPostRequestWithMultipleQueriesValid();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserPostWithQueriesAndBodyNoValidQuery() {
		HTTPRequest request = MockObjects.postHttpRequestWithMultipleInvalidQueriesAndBody();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfPostRequestWithInvalidMultipleQueriesAndBody();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test : isBadRequest is set if HTTP Version is not valid
	@Test
	public void testValidRequestParserPostWithInvalidHttp() {
		HTTPRequest request = MockObjects.postHttpRequestWithInvalidVersion();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfPostHttpRequestWithInvalidVersion();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testValidRequestParserGetWithInvalidHttp() {
		HTTPRequest request = MockObjects.getHttpRequestWithInvalidVersion();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfgetHttpRequestWithInvalidVersion();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
	}

	//Integration Test
	@Test
	public void testInvalidRequestLine() {
		HTTPRequest request = MockObjects.httpRequestWithInvalidRequestLine();
		RequestParser parser = new RequestParser(request);
		HTTPRequest expected = MockObjects.checkIfHttpRequestWithInvalidRequestLine();
		assertEquals(expected.getBody(),request.getBody());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.getHttpVersion(), request.getHttpVersion());
		assertEquals(expected.getPath(), request.getPath());
		assertEquals(expected.getQuery(), request.getQuery());
		assertEquals(expected.isBadRequest(), request.isBadRequest());
	}
}
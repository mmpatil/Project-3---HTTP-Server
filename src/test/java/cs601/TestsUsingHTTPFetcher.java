package cs601;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestsUsingHTTPFetcher {

	@Test
	public void testGetReviewSearchValid() {
		HTTPFetcher.download("GET",8080, "localhost", "/reviewsearch");
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}
	
	//Validate the GET returns Review Search Get form 
	@Test
	public void testGetReviewSearchValidContentLength() {
		HTTPFetcher.download("GET",8080, "localhost", "/reviewsearch");
		assertEquals(Long.parseLong(HTTPFetcher.getContentLenght().split(":")[1].trim()), Integer.toUnsignedLong(268));
	}

	@Test
	public void testGetFindValid() {
		HTTPFetcher.download("GET",8080, "localhost", "/find");
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}
	
	//Validate the GET returns Find Get form 
	@Test
	public void testGetFindValidContentLength() {
		HTTPFetcher.download("GET",8080, "localhost", "/find");
		assertEquals(Long.parseLong(HTTPFetcher.getContentLenght().split(":")[1].trim()), Integer.toUnsignedLong(252));
	}

	//Valid GET slackbot return form
	@Test
	public void testGetSlackValid() {
		HTTPFetcher.download("GET",9090, "localhost", "/slackbot");
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}
	
	//Validate the GET /slackbot returns slack message form 
		@Test
		public void testSlackFindValidContentLength() {
			HTTPFetcher.download("GET",9090, "localhost", "/slackbot");
			assertEquals(Long.parseLong(HTTPFetcher.getContentLenght().split(":")[1].trim()), Integer.toUnsignedLong(266));
		}

	@Test
	public void testGetReviewSearchInvalidPath() {
		HTTPFetcher.download("GET",8080, "localhost", "/reviewsear");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	//Invalid path for Slack 404
	@Test
	public void testGetSlackInvalidPath() {
		HTTPFetcher.download("GET",9090, "localhost", "/slack");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	@Test
	public void testGetFindInvalidPath() {
		HTTPFetcher.download("GET",8080, "localhost", "/fin");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	//GET with query string will return the form
	@Test
	public void testGetReviewSearchWithQueryString() {
		HTTPFetcher.download("GET",8080, "localhost", "/reviewsearch?query=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//GET with query string will return the form
	@Test
	public void testGetSlackWithQueryString() {
		HTTPFetcher.download("GET",9090, "localhost", "/slackbot?message=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//Gets a Find form
	@Test
	public void testGetFindWithQueryString() {
		HTTPFetcher.download("GET",8080, "localhost", "/find?asin=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//POST with valid Query String
	@Test
	public void testPostReviewSearchWithQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsearch?query=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}
	
	//POST with valid Query String for slack
		@Test
		public void testPostSlackbotWithQueryString() {
			HTTPFetcher.download("POST",9090, "localhost", "/slackbot?message=help");
			System.out.println(HTTPFetcher.getRequestLine());
			assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
		}

	//POST with valid Query String for Find
	@Test
	public void testPostFindWithQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/find?asin=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//POST with invalid query string 400
	@Test
	public void testPostReviewSearchWithInvalidQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsearch?quer=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}
	
	//POST with invalid query string 400 for slack
		@Test
		public void testPostSlackWithInvalidQueryString() {
			HTTPFetcher.download("POST",9090, "localhost", "/slackbot?messa=help");
			System.out.println(HTTPFetcher.getRequestLine());
			assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
		}

	//POST with invalid query string 400 for Find
	@Test
	public void testPostFindWithInvalidQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/find?asi=help");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}

	//POST with invalid query string 400
	@Test
	public void testPostReviewSearchWithInvalidMultipleQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsearch?quer=help&que=they");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}

	//POST with invalid query string 400 for Find
	@Test
	public void testPostFindWithInvalidMultipleQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/find?quer=help&que=they");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}

	//Post with no body or query string 400
	@Test
	public void testPostReviewSearchWithNoQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsearch");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}

	//Post with no body or query string 400
	@Test
	public void testPostFindWithNoQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsearch");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}

	//POST for multiple requests with valid query
	@Test
	public void testPostReviewSearchWithValidMultipleQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsearch?quer=he&query=they");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//POST for multiple requests with valid query for Find
	@Test
	public void testPostFindWithValidMultipleQueryString() {
		HTTPFetcher.download("POST",8080, "localhost", "/find?quer=he&asin=they");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//POST 404
	@Test
	public void testPostReviewSearchWithInvalidPath() {
		HTTPFetcher.download("POST",8080, "localhost", "/reviewsea");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	//POST review search with no path
	@Test
	public void testPostReviewSearchWithNoPath() {
		HTTPFetcher.download("POST",8080, "localhost", "/");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	//POST with no path for Find
	@Test
	public void testPostFindWithNoPath() {
		HTTPFetcher.download("POST",8080, "localhost", "/");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	@Test
	public void testGetReviewSearchWithMultipleQueries() {
		HTTPFetcher.download("GET",8080, "localhost", "/reviewsearch?query=help&query2=they");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	@Test
	public void testGetReviewSearchWithoutPath() {
		HTTPFetcher.download("GET",8080, "localhost", "/");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	//Invalid Method 405
	@Test
	public void testGetReviewSearchValidMethodPUT() {
		HTTPFetcher.download("PUT",8080, "localhost", "/reviewsearch");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.METHOD_NOT_FOUND_HEADER.trim()));
	}

	//Invalid Method 405
	@Test
	public void testGetReviewSearchValidMethodDELETE() {
		HTTPFetcher.download("DELETE",8080, "localhost", "/reviewsearch");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.METHOD_NOT_FOUND_HEADER.trim()));
	}

	//Invalid Method 405
	@Test
	public void testGetFindValidMethodDELETE() {
		HTTPFetcher.download("DELETE",8080, "localhost", "/find");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.METHOD_NOT_FOUND_HEADER.trim()));
	}

	//Invalid Method 405
	@Test
	public void testGetReviewSearchValidMethodHEAD() {
		HTTPFetcher.download("HEAD",8080, "localhost", "/reviewsearch");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.METHOD_NOT_FOUND_HEADER.trim()));
	}

	//Invalid Method 405
	@Test
	public void testGetFindValidMethodHEAD() {
		HTTPFetcher.download("HEAD",8080, "localhost", "/find");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.METHOD_NOT_FOUND_HEADER.trim()));
	}

	@Test
	public void testPostReviewSearch() {
		HTTPFetcher.download(8080, "localhost", "/reviewsearch","query=harness");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//POST with invalid path 404
	@Test
	public void testPostReviewSearchWithoutPath() {
		HTTPFetcher.download(8080, "localhost", "/","query=harness");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.PAGE_NOT_FOUND_HEADER.trim()));
	}

	//POST with valid body
	@Test
	public void testPostReviewSearchNoResultQuery() {
		HTTPFetcher.download(8080, "localhost", "/reviewsearch","query=123");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	//POST with valid body for Slack
	@Test
	public void testPostSlackWithValidBody() {
		HTTPFetcher.download(9090, "localhost", "/slackbot","message=123 321");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}
	
	@Test
	public void testPostReviewSearchMultipleQueries() {
		HTTPFetcher.download(8080, "localhost", "/reviewsearch","query=123&abc=nono");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	@Test
	public void testPostReviewSearchMultipleQueriesInQueryStringandBody() {
		HTTPFetcher.download(8080, "localhost", "/reviewsearch?query=they","query=123&abc=nono");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.OK_HEADER.trim()));
	}

	@Test
	public void testPostReviewSearchWithInvalidMultipleQueriesInQueryStringandBody() {
		HTTPFetcher.download(8080, "localhost", "/reviewsearch?quer2=they","quer3=123&abc=nono");
		System.out.println(HTTPFetcher.getRequestLine());
		assertTrue(HTTPFetcher.getRequestLine().equals(HttpConstants.BAD_REQUEST.trim()));
	}
}

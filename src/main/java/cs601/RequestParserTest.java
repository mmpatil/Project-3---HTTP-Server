package cs601;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RequestParserTest {

	@Test
	public void testValidRequestLine() {
		RequestParser parser = new RequestParser();
		assertFalse(parser.isValidRequestLine("GET    /reviewsearch HTTP1.0"));		
	}
}

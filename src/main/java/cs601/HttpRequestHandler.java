package cs601;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;

public class HttpRequestHandler implements Runnable{

	private Socket connect;
	private HashMap<String,Handler> mappings = new HashMap<String,Handler>();
	private int contentLenghtCount = 0;

	public HttpRequestHandler(Socket c) {
		connect = c;
	}

	/**
	 * Adds the method to handler mappings in the server 
	 * @param method passed in the request
	 * @param handle the corresponding handler
	 */
	public void addMapping(String method,Handler handle) {
		mappings.put(method, handle);
	}
	
	/**
	 * Sets the parameters for the HttpRequest object.
	 * @param instream 	input stream of the socket
	 * @param request The HttpRequest object to be created
	 * @return
	 * @throws IOException
	 */
	private void setRequestObject(InputStream instream, HTTPRequest request) throws IOException {
		String header = "";
		//reads requestLine
		request.setRequestLine(oneLine(instream));
		String input = oneLine(instream);
		//reads headers
		while(input!=null && !input.trim().isEmpty()) {
			header += input +"\n";
			input = oneLine(instream);
			if(input.startsWith("Content-Length:")) {
				if(input.contains(":") && contentLenghtCount==0) {
					request.setContentLenght(Integer.parseInt(input.split(":")[1].trim()));
					contentLenghtCount+=1;
				}
			}
		}
		request.setHeaders(header);
		//get content length
		int read = 0;
		byte[] bytes = null; 
		if(contentLenghtCount == 1) {
			bytes = new byte[request.getContentLenght()];
			read = connect.getInputStream().read(bytes);

			while(read < request.getContentLenght()) {
				read += connect.getInputStream().read(bytes, read, (bytes.length-read));
			}
			if(read == request.getContentLenght()) {
				request.setBody((new String(bytes)));
			}
			(MyLogger.getLogger()).log(Level.INFO, "In server read body");
		}
	}
	
	/*
	 * (non-Javadoc) Creates a client socket for a single connection 
	 * @see java.lang.Runnable#run()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try(InputStream instream = connect.getInputStream();
				PrintWriter writer = new PrintWriter(connect.getOutputStream())){

			HTTPRequest request = new HTTPRequest();

			HTTPResponse response = new HTTPResponse();
			
			setRequestObject(instream,request);

			//sent the request to parser
			RequestParser requestParse = new RequestParser(request);

			(MyLogger.getLogger()).log(Level.INFO, "\n RequestLine:" +request.getRequestLine() +"\n Headers:"+request.getHeaders() + "\n Body:"+request.getBody());

			verifyRequestAndSetResponse(request, response);
			
			writeResponse(response,writer);

		}
		catch(IOException e) {
			(MyLogger.getLogger()).log(Level.SEVERE, "Problem in server using socket :"+e.getMessage());
		}
		finally {
			try {
				connect.close();
			} catch (IOException e) {
				(MyLogger.getLogger()).log(Level.SEVERE, "Problem in server closing accepting socket :"+e.getMessage());
			}
		}
	}

	/**
	 * Writes the response.
	 * @param response The response object
	 * @param writer The PrintWriter to write the response
	 * @return
	 */
	private void writeResponse(HTTPResponse response, PrintWriter writer) {
		if(!response.getHtmlPage().trim().equals("")) {
			writer.write(response.getHeader());
			writer.write(response.getHtmlPage());
			writer.flush();
		}
	}

	/**
	 * Verifies if the request sent is of the proper format and valid and sets the response accordingly
	 * @param request the HTTPRequest object to be validated
	 * @param response the HTTPResponse object to be set
	 * @return
	 */
	private void verifyRequestAndSetResponse(HTTPRequest request, HTTPResponse response) {
		if(request.getMethod().equals(HttpConstants.GET) || request.getMethod().equals(HttpConstants.POST)) {
			if(!request.getHttpVersion().trim().equals("")) {
				if(mappings.containsKey(request.getPath())) {
					(MyLogger.getLogger()).log(Level.INFO, "Mapping contains path");
					(mappings.get(request.getPath())).handle(request,response);
				}
				else {
					(MyLogger.getLogger()).log(Level.INFO, "Page not found in server");
					ErrorPages.pageNotFoundHtml(response);
				}
			}else {
				ErrorPages.badRequestHtml(response);
			}
		}else {
			(MyLogger.getLogger()).log(Level.INFO, "Method not found in server");
			ErrorPages.methodNotFoundHtml(response);
		}
	}

	/**
	 * Read a line of bytes until \n character.
	 * @param instream
	 * @return
	 * @throws IOException
	 */
	private static String oneLine(InputStream instream) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		byte b = (byte) instream.read();
		while(b != '\n' && b != -1) {
			bout.write(b);
			b = (byte) instream.read();
		}
		return new String(bout.toByteArray());
	}
}
package cs601;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.logging.Level;

public class HttpServer implements Runnable{

	private Socket connect;

	private HashMap<String,Handler> mappings = new HashMap<String,Handler>();
	private int contentLenghtCount = 0;

	public HttpServer(Socket c) {
		connect = c;
	}

	public void addMapping(String method,Handler handle) {
		mappings.put(method, handle);
	}

	public void getMapping() {
		System.out.println("\n Mapping is : " +mappings);
	}

	@Override
	public void run() {
		try(InputStream instream = connect.getInputStream();
				PrintWriter writer = new PrintWriter(connect.getOutputStream())){

			HTTPRequest request = new HTTPRequest();

			HTTPResponse response = new HTTPResponse();

			request.setRequestLine(oneLine(instream));

			String input = oneLine(instream);

			String header = "";

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
			(MyLogger.getLogger()).log(Level.INFO, "In server read headers");

			request.setHeaders(header);

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

			RequestParser requestParse = new RequestParser(request);

			(MyLogger.getLogger()).log(Level.INFO, "\n RequestLine:" +request.getRequestLine() +"\n Headers:"+request.getHeaders() + "\n Body:"+request.getBody());


			if(request.getMethod().equals(HttpConstants.GET) || request.getMethod().equals(HttpConstants.POST)) {
				if(!request.getHttpVersion().trim().equals("")) {
					if(mappings.containsKey(request.getPath())) {
						(MyLogger.getLogger()).log(Level.INFO, "Mapping contains path");
						(mappings.get(request.getPath())).handle(request,response);
					}
					else {
						(MyLogger.getLogger()).log(Level.INFO, "Page not found in server");
						response.setHeader(HttpConstants.PAGE_NOT_FOUND_HEADER);
						pageNotFoundHtml(response);
					}
				}else {
					response.setHeader(HttpConstants.BAD_REQUEST);
					BadRequestHtml(response);
				}
			}else {
				(MyLogger.getLogger()).log(Level.INFO, "Method not found in server");
				response.setHeader(HttpConstants.METHOD_NOT_FOUND_HEADER);
				MethodNotFoundHtml(response);
			}
			if(!response.getHtmlPage().trim().equals("")) {
				writer.write(response.getHeader());
				writer.write(response.getHtmlPage());
				writer.flush();
			}
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

	public void pageNotFoundHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Page Not Found");
		StringBuilder pageNotFound = html.head();
		pageNotFound = html.pageNotFoundPage(pageNotFound);
		response.setHtmlPage(pageNotFound.toString());
	}

	public void MethodNotFoundHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Method Not Found");
		StringBuilder methodNotFoundBuilder = html.head();
		methodNotFoundBuilder = html.methodNotFoundPage(methodNotFoundBuilder);
		response.setHtmlPage(methodNotFoundBuilder.toString());
		response.setHeader(HttpConstants.METHOD_NOT_FOUND_HEADER);
	}

	public void BadRequestHtml(HTTPResponse response) {
		HtmlBuilder html = new HtmlBuilder();
		html.setTitle("Bad Request");
		StringBuilder badRequestBuilder = html.head();
		badRequestBuilder = html.badRequestPage(badRequestBuilder);
		response.setHtmlPage(badRequestBuilder.toString());
		response.setHeader(HttpConstants.BAD_REQUEST);
	}
}
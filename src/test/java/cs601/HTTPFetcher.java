package cs601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class HTTPFetcher {

	private static String requestLine;
	private static String contentLenght;
	private static String html;

	public static String getRequestLine() {
		return requestLine;
	}

	public static String getContentLenght() {
		return contentLenght;
	}

	public static String getHtml() {
		return html;
	}

	/**
	 * Generates a GET/POST request with query String parameters
	 * @param method method to be tested
	 * @param PORT port of the application
	 * @param host hostname on which the application is running
	 * @param path the valid path
	 */
	public static void download(String method, int PORT, String host, String path) {

		StringBuffer buf = new StringBuffer();

		try (
				Socket sock = new Socket(host, PORT); //create a connection to the web server
				OutputStream out = sock.getOutputStream(); //get the output stream from socket
				InputStream instream = sock.getInputStream(); //get the input stream from socket
				//wrap the input stream to make it easier to read from
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream))
				) { 

			//send request
			String request = getRequest(method,host, path);
			out.write(request.getBytes());
			out.flush();

			//receive response
			//note: a better approach would be to first read headers, determine content length
			//then read the remaining bytes as a byte stream
			requestLine = reader.readLine();
			contentLenght = reader.readLine();
			String line = reader.readLine();
			while(line != null) {       
				buf.append(line + "\n"); //append the newline stripped by readline
				line = reader.readLine();
			}

			html = buf.toString();

		} catch (IOException e) {
			System.out.println("HTTPFetcher::download " + e.getMessage());
		}
	}

	/**
	 * Generate a request in a valid format
	 * @param method method to be tested
	 * @param host hostname on which the application is running
	 * @param path the valid path
	 * @return
	 */
	private static String getRequest(String method,String host, String path) {
		String request = method+" " + path + " HTTP/1.1" + "\n" //GET request
				+ "Host: " + host + "\n" //Host header required for HTTP/1.1
				+ "Connection: close\n" //make sure the server closes the connection after we fetch one page
				+ "\r\n";               
		return request;
	}

	/**
	 * Generates a GET/POST request with query String parameters
	 * @param PORT port of the application
	 * @param host hostname on which the application is running
	 * @param path the valid path
	 * @param query The BODY of POST
	 */
	public static void download(int PORT, String host, String path, String query) {

		StringBuffer buf = new StringBuffer();

		try (
				Socket sock = new Socket(host, PORT); //create a connection to the web server
				OutputStream out = sock.getOutputStream(); //get the output stream from socket
				InputStream instream = sock.getInputStream(); //get the input stream from socket
				//wrap the input stream to make it easier to read from
				BufferedReader reader = new BufferedReader(new InputStreamReader(instream))
				) { 

			//send request
			String request = postRequestWithBody(host, path, query);
			out.write(request.getBytes());
			out.flush();

			//receive response
			//note: a better approach would be to first read headers, determine content length
			//then read the remaining bytes as a byte stream
			requestLine = reader.readLine();
			contentLenght = reader.readLine();
			String line = reader.readLine();
			while(line != null) {       
				buf.append(line + "\n"); //append the newline stripped by readline
				line = reader.readLine();
			}
			html = buf.toString();
		} catch (IOException e) {
			System.out.println("HTTPFetcher::download " + e.getMessage());
		}
	}

	/**
	 * Generate a request in a valid format
	 * @param host hostname on which the application is running
	 * @param path the valid path
	 * @param query body of post
	 * @return valid request
	 */
	private static String postRequestWithBody(String host, String path, String query) {
		String request = "POST " + path + " HTTP/1.1" + "\n" //POST request
				+ "Host: " + host + "\n" //Host header required for HTTP/1.1
				+"Content-Length: " + query.getBytes().length +"\n"
				+ "Connection: close\n" //make sure the server closes the connection after we fetch one page
				+ "\r\n"
				+query;
		return request;
	}
}
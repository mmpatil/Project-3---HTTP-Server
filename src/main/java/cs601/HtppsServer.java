package cs601;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HtppsServer implements Runnable{
	
	static final String DEFAULT_FILE = "index.html";
	static final String FILE_NOT_FOUND = "404.html";
	static final String METHOD_NOT_SUPPORTED = "not_support.html";
	private BufferedReader in = null;
	private PrintWriter out = null;
	private String fileRequested = null;

	
	static final int PORT = 8080;
	
	private Socket connect;
	
	public HtppsServer(Socket c) {
		connect = c;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			in = new BufferedReader(new InputStreamReader(connect.getInputStream()));
			
			out = new PrintWriter(connect.getOutputStream());
			
			String input = in.readLine();
			
			RequestParser requestParser = new RequestParser(input);
			
			if(requestParser.getHeader().trim().equals("/reviewSearch")) {
				String query = requestParser.getQuery();
				System.out.println("Query for reviewsearch is: "+query);
			}
			
			if(requestParser.getHeader().trim().equals("/find")) {
				String query = requestParser.getQuery();
				System.out.println("Query for find is: "+query);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

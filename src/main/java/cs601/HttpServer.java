/**
 * 
 */
package cs601;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

/**
 * @author manalipatil
 *
 */
public class HttpServer {

	private HttpRequestHandler httpRequestHandler;

	private ExecutorService executor;
	
	private HashMap<String,Handler> mappings = new HashMap<String,Handler>();

	/**
	 * Starts the server socket for the application
	 * @param port port on which the server is started
	 * @param poolsize the poolsize of the server
	 */
	public void startServer(int port,int poolSize) {
		try {
			ServerSocket serverSocket = new ServerSocket(port);

			(MyLogger.getLogger()).log(Level.INFO, "Server started...");

			this.executor = Executors.newFixedThreadPool(poolSize);

			//Starts the listening socket for this server
			while(true) {
				Socket socket = serverSocket.accept();
				
				this.httpRequestHandler = new HttpRequestHandler(socket); 
				
				//adds mapping to httpRequestHandler
				addMapping();
				
				//starts the httpRequestHandler
				startHttpRequestHandler();
			}
		} catch (IOException e) {
			(MyLogger.getLogger()).log(Level.SEVERE, "Problem in server socket :"+e.getMessage());
		}
	}
	
	/**
	 * Adds the method to handler mappings in the server 
	 * @param method passed in the request
	 * @param handle the corresponding handler
	 */
	public void addMappingtoRequestHandler(String method,Handler handle) {
		this.mappings.put(method, handle);
	}
	
	/**
	 * Adds the method to handler mappings in the httpRequestHandler 
	 * @param method passed in the request
	 * @param handle the corresponding handler
	 */
	public void addMapping()
	{
		for(String key:this.mappings.keySet()) {
			this.httpRequestHandler.addMapping(key, this.mappings.get(key));
		}
	}
	
	/**
	 * starts the httpRequestHandler
	 */
	public void startHttpRequestHandler() {
		this.executor.execute(this.httpRequestHandler);
	}
}
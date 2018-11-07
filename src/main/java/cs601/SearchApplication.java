package cs601;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import SearchApplicationInvertedIndex.AmazonSearch;
import SearchApplicationInvertedIndex.InvertedIndex;

public class SearchApplication {

	private static InvertedIndex index ;
	private static ConfigurationFileForSearch configuration ;
	
	/**
	 * Constructor
	 * @param path Path of Configuration file
	 */
	public SearchApplication(Path path) {
		configuration = ConfigurationFileForSearch.getConfigurations(path);
		index = AmazonSearch.createInvertedIndex(configuration.getReviewInputFiles(),configuration.getQaInputFiles());
//		this.index = AmazonSearch.createInvertedIndex("Cell_Phones_and_Accessories_5.json","qa_Cell_Phones_and_Accessories.json");
	}

	public static void main(String[] args) {
		
		if(checkCommandLineArgument(args)) {
			SearchApplication application = new SearchApplication(Paths.get(args[0]));
			MyLogger.setLogger(configuration.getLoggerFile());
			Logger logger = MyLogger.getLogger();
			application.startApplication(configuration);
		}
		else {
			MyLogger.setLogger(configuration.getLoggerFile());
			Logger logger = MyLogger.getLogger();
			(MyLogger.getLogger()).log(Level.INFO, "Command Line arguments not valid");
		}
	}

	/**
	 * Starts the listening socket for this application
	 * @param configuration Configurations for this application
	 */
	public void startApplication(ConfigurationFileForSearch configuration) {
		try {
			ServerSocket serverSocket = new ServerSocket(configuration.getPort());

			(MyLogger.getLogger()).log(Level.INFO, "Server started...");

			ExecutorService executor = Executors.newFixedThreadPool(configuration.getPoolSize());

			while(true) {
				Socket socket = serverSocket.accept();
				HttpServer httpServer = new HttpServer(socket); 
				httpServer.addMapping("/reviewsearch", new ReviewSearchHandler(this.index));
				httpServer.addMapping("/find", new FindHandler(this.index));

				executor.execute(httpServer);
			}
		} catch (IOException e) {
			(MyLogger.getLogger()).log(Level.SEVERE, "Problem in server socket :"+e.getMessage());
		}
	}

	/**
	 * Checks if the command Line arguments are correct
	 * @param args
	 * @return true is correct false if not
	 */
	public static boolean checkCommandLineArgument(String[] args) {
		boolean valid = false;
		if(args.length == 1 && args[0].endsWith(".json")) {
			if(Files.exists((Paths.get(args[0])))) {
				valid = true;
			}
		}
		return valid;
	}
}
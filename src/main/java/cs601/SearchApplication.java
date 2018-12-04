package cs601;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import SearchApplicationInvertedIndex.AmazonSearch;
import SearchApplicationInvertedIndex.InvertedIndex;

public class SearchApplication {

	private static InvertedIndex index ;
	private static ConfigurationFileForSearch configuration ;
	private static int port;
	private static int poolSize;

	/**
	 * Setting the port from the configuration file
	 */
	public static void setPort() {
		SearchApplication.port = configuration.getPort();
	}

	/**
	 * Setting the poolSize from the configuration file
	 */
	public static void setPoolSize() {
		SearchApplication.poolSize = configuration.getPoolSize();
	}
	
	/**
	 * Constructor
	 * @param path Path of Configuration file
	 */
	public SearchApplication(Path path) {
		configuration = ConfigurationFileForSearch.getConfigurations(path);
		index = AmazonSearch.createInvertedIndex(configuration.getReviewInputFiles(),configuration.getQaInputFiles());
		setPort();
		setPoolSize();
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
	 * Starts the application server
	 * @param configuration Configurations for this application
	 */
	public void startApplication(ConfigurationFileForSearch configuration) {
		
		HttpServer server = new HttpServer();
		
		//add mappings
		server.addMappingtoRequestHandler("/reviewsearch", new ReviewSearchHandler(this.index));
		server.addMappingtoRequestHandler("/find", new FindHandler(this.index));
		
		//start server
		server.startServer(port,poolSize);
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
package cs601;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatApplication {

	private static ConfigurationFileForChatApplication configuration ;
	private static int port;
	private static int poolSize;

	/**
	 * Setting the port from the configuration file
	 */
	public static void setPort() {
		ChatApplication.port = configuration.getPort();
	}

	/**
	 * Setting the poolSize from the configuration file
	 */
	public static void setPoolSize() {
		ChatApplication.poolSize = configuration.getPoolSize();
	}
	/**
	 * Getting the configuration file
	 */
	public static ConfigurationFileForChatApplication getConfiguration() {
		return configuration;
	}

	/**
	 * Constructor
	 * @param path Path of Configuration file
	 */
	public ChatApplication(Path path) {
		configuration = ConfigurationFileForChatApplication.getConfigurations(path);
		setPort();
		setPoolSize();
		
	}

	public static void main(String[] args) {
		if(checkCommandLineArgument(args)) {
			ChatApplication application = new ChatApplication(Paths.get(args[0]));
			MyLogger.setLogger(configuration.getLoggerFile());
			Logger logger = MyLogger.getLogger();
			application.startApplication();
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
	public void startApplication() {

		HttpServer server = new HttpServer();
		
		//add mappings 
		server.addMappingtoRequestHandler("/slackbot", new ChatHandler());
		
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

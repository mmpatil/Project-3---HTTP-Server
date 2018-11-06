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

public class ChatApplication {

	private static ConfigurationFileForChatApplication configuration ;

	public static ConfigurationFileForChatApplication getConfiguration() {
		return configuration;
	}

	public ChatApplication(Path path) {
		configuration = ConfigurationFileForChatApplication.getConfigurations(path);
	}

	public static void main(String[] args) {
		if(checkCommandLineArgument(args)) {
			ChatApplication application = new ChatApplication(Paths.get(args[0]));
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

	public void startApplication(ConfigurationFileForChatApplication configuration) {
//		MyLogger.setLogger(configuration.getLoggerFile());
//		Logger logger = MyLogger.getLogger();
		try {
			ServerSocket serverSocket = new ServerSocket(configuration.getPort());

			(MyLogger.getLogger()).log(Level.INFO, "Server started..\n",0);

			ExecutorService executor = Executors.newFixedThreadPool(configuration.getPoolSize());

			while(true) {
				//				System.out.println("\n\n\nThread :"+Thread.currentThread().getName());
				Socket socket = serverSocket.accept();
				HttpServer httpServer = new HttpServer(socket); 
				httpServer.addMapping("/slackbot", new ChatHandler());

				executor.execute(httpServer);
			}
		} catch (IOException e) {
			(MyLogger.getLogger()).log(Level.SEVERE, "Problem in server socket :"+e.getMessage());
		}
	}

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

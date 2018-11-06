package cs601;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

	private static Logger logger ;
	private static FileHandler fileTxt;
	private static SimpleFormatter formatterTxt;

	/**
	 * initializes the Logger
	 * @param fileName the name of the logger file 
	 */
	public static void setLogger(String fileName) {
		logger = Logger.getLogger("MyLogger");
		try {
			fileTxt = new FileHandler(fileName,true);
			formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the logger instance
	 */
	public static Logger getLogger() {
		return logger;
	}
}

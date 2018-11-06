package cs601;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import com.google.gson.Gson;

public class ConfigurationFileForChatApplication {
	
	private int port;
	private String loggerFile;
	private String channel;
	private String token;
	private int poolSize;
	
	public int getPort() {
		return port;
	}
	public String getLoggerFile() {
		return loggerFile;
	}
	public String getChannel() {
		return channel;
	}
	public String getToken() {
		return token;
	}
	public int getPoolSize() {
		return poolSize;
	}
	
	/**
	 * @param path: the path of the configuration file.
	 * @return an object containing the Configurations.
	 */
	public static ConfigurationFileForChatApplication getConfigurations(Path path) {
		Gson gson = new Gson();
		ConfigurationFileForChatApplication configuration = new ConfigurationFileForChatApplication();
		try(BufferedReader reader = Files.newBufferedReader(path,Charset.forName("ISO-8859-1"))){
			String line = reader.readLine();
				configuration = gson.fromJson(line, ConfigurationFileForChatApplication.class);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return configuration;
	}
}

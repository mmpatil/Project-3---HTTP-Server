package cs601;

import java.io.IOException;
import java.net.ServerSocket;

public class SearchApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket serverSocket = new ServerSocket(8080);
			
			System.out.println("Server started...");
			
			while(true) {
				HtppsServer server = new HtppsServer(serverSocket.accept());
				Thread thread = new Thread(server);
				thread.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

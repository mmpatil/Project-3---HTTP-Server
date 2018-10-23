package cs601;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private Socket socket = null;
//	private DataInputStream input = null;
	private PrintWriter out = null;

	public  Client(String address, int port, String path) {

		try {
			socket = new Socket(address,port);

			System.out.println("Connected!!");
			
//			input = new DataInputStream(new FileInputStream(path));
				
			out = new PrintWriter(socket.getOutputStream(),true);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			out.println("hi there!!");
			out.println("How are u?");
			out.println("Over");
		try {
//			input.close();
			out.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Client client = new Client("127.0.0.1",5000,"ReviewOld.json");
	}
}
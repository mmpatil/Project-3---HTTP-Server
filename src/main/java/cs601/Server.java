package cs601;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket = null;
	private Socket socket =  null;
	private BufferedReader input = null;
//	private DataOutputStream out = null;

	public Server(int port) {

		try {
			serverSocket = new ServerSocket(port);

			System.out.println("Server started!");

			socket = serverSocket.accept();

			System.out.println("Client accepted");

			input = new BufferedReader(new InputStreamReader(socket.getInputStream()));	
			String line = " ";
			while(!line.equals("Over")) {
				line = input.readLine();
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Closing connection");

		try {
			socket.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server(5000);
	}
}
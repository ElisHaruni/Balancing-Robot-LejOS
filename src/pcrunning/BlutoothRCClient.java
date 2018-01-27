package pcrunning;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class BlutoothRCClient {

	static final String IP = "10.0.1.1";
	static final int PORT = 1111;
	
	public void startBalnancig() throws IOException{
		
		Socket socket = new Socket(IP, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(1);
		socket.close();
	}
	public void moveForward() throws IOException {
		
		Socket socket = new Socket(IP, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(2);
		socket.close();
	}
public void moveBackward() throws IOException {
		
		Socket socket = new Socket(IP, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(3);
		socket.close();
	}
public void turnRight() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(4);
	socket.close();
}
public void turnLeft() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(5);
	socket.close();
}
public void piruet() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(7);
	socket.close();
}
public void stop() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(6);
	socket.close();
}
}

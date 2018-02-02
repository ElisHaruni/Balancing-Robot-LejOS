package pcrunning;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
/**
 * 
 * @author Elis Haruni
 * 
 * This class will be the client to send commands to a Robot
 * This is the part of application that runs on a computer.
 *
 */
public class BlutoothRCClient {

	static final String IP = "10.0.1.1";
	static final int PORT = 1111;
	
	/**
	 * this send the command to make robot stand balanced 
	 * @throws IOException
	 */
	public void startBalnancig() throws IOException{
		
		Socket socket = new Socket(IP, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(1);
		socket.close();
	}
	/**
	 * 
	 * @throws IOException
	 */
	public void moveForward() throws IOException {
		
		Socket socket = new Socket(IP, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(2);
		socket.close();
	}
	/**
	 * 
	 * @throws IOException
	 */
public void moveBackward() throws IOException {
		
		Socket socket = new Socket(IP, PORT);
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		out.writeInt(3);
		socket.close();
	}
/**
 * 
 * @throws IOException
 */
public void turnRight() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(4);
	socket.close();
}
/**
 * this will turn the robot with a very low speed to the left 
 * no way to stop this robot will continue to turn while we terminate the entire process
 * @throws IOException
 */
public void turnLeft() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(5);
	socket.close();
}
/**
 *  This will make robot rotate or turn with a high speed and ther is no 
 *  method implemented to stop this if it starts will end if we terminate program by 
 *  pushing Escape button in EV3
 * @throws IOException
 */
public void piruet() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(7);
	socket.close();
}
/**
 *  This method will not stop the entire process it only set speed to 0 that 
 *  means robot will stop from moving forward or backward, also this 
 *  will not stop robot from turning 
 * @throws IOException
 */
public void stop() throws IOException {
	
	Socket socket = new Socket(IP, PORT);
	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
	out.writeInt(6);
	socket.close();
}
}

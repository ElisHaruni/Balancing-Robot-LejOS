package com;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * 
 * @author Elis Haruni
 * @version 1.0
 * 
 * This class will be run on EV3 and is the server  for bluetooth  connection
 * 
 * @throws IOException
 *
 */
public class BluetoothRCServer {
	public static void main(String args[]) throws IOException {
		
		//Creating various resources to be used in the class.
		System.out.print("I am waitng for comands");
		int input;
		ServerSocket server = new ServerSocket(1111);
	
		Balancing balancingThread = new Balancing();
		 
		//Setting up the IsEscapeDownChecker thread, and passing it the ServerSocket to be used.
		IsEscapeDownChecker isEscapeDown = new IsEscapeDownChecker(server);
		isEscapeDown.setDaemon(true);
		isEscapeDown.start();
		//Main loop of the program. The EV3 checks incoming data from the socket in the form of integers. 
		//Depending on the incoming integer the EV3 executes a action.
		while (true) {
			Socket socket;
			try {
				socket = server.accept();
			} catch (IOException e) {
				break;
			}
			DataInputStream in = new DataInputStream(socket.getInputStream());
			input = in.readInt();
			
			if (input == 1)            // start the balancing act
			{
				balancingThread.start();
				balancingThread.setSpeed(0);
			} 
			if (input == 2) 	      //moving forward with 8 speed
			{
				balancingThread.increaseSpeed(8);
				
			}			
			if (input == 3)            //moving backwards with 8 speed
			{
				balancingThread.increaseSpeed(-8);
				
			}
			if (input == 4)         //turn right with 5 speed
			{
				balancingThread.turn(5);
				
			}
			if (input == 5)  		//turn left with 5 speed   
			{
				balancingThread.turn(-5);
				
			}
			if (input == 6)			//set speed 0 or stop
			{
				balancingThread.setSpeed(0);
				balancingThread.turn(0);
				
				
			}			
			if (input == 7)			// piruette with 40 speed
			{ 
				balancingThread.turn(40);
				
			}
			
			
			
		}
		
		//If IsEscapeDown instance of IsEscapeDownChecker closes the socket connection, an error occurs and
		//the main program loop is broken out of to arrive at this point. Thus ending the program.
		
		Sound.setVolume(100);
		Sound.buzz();
		server.close();
		System.exit(0);
	}
}

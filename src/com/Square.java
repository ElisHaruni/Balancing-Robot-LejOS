package com;

/**
 * @author k.rajendran
 * 
 * This class will move the robot in a square
 * 
 * */

public class Square extends Thread {

	private static Boolean SQUARE_COMPLETED = false;
	Balancing balancingThread;

	public Square(Balancing balancingThread) {
		
		this.balancingThread = balancingThread;
		this.start();
	}

	public void run() {

		//This loop will move the robot in a square. 
		//Starts with 0 speed, then move forward, turn right in loop
		int count=0;
		while (!SQUARE_COMPLETED) {
			try {
			    fullStop();
				moveForward();
				turnRight();
				count++;
				if(count>=4)
				{
					SQUARE_COMPLETED=true;
					fullStop();
					
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
	/**
	 * @author k.rajendran
	 * 
	 * This will turn the robot right and is given with +ve value
	 * 
	 * */
	public void turnRight() throws InterruptedException
	{
		balancingThread.setSpeed(0);
		balancingThread.turn(5);
		Thread.sleep(1000); //not sure about this time needs to be calculated with the device
	}
	
	/**
	 * @author k.rajendran
	 * 
	 * This will move the robot in forward direction
	 * 
	 * */
	
	public void moveForward() throws InterruptedException
	{
		balancingThread.turn(0);
		balancingThread.setSpeed(8);
		Thread.sleep(1000);
	}
	
	/**
	 * @author k.rajendran
	 * 
	 * This will stop the robot, speed=0
	 * 
	 * */
	
	public void fullStop()
	{
		balancingThread.setSpeed(0);
		balancingThread.turn(0);
	}
	
}

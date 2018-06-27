package com;

public class Square extends Thread {

	private static Boolean SQUARE_COMPLETED = false;
	Balancing balancingThread;

	public Square(Balancing balancingThread) {
		
		this.balancingThread = balancingThread;
		this.start();
	}

	public void run() {

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
	 * */
	public void turnRight() throws InterruptedException
	{
		balancingThread.setSpeed(0);
		balancingThread.turn(5);
		Thread.sleep(1000); //not sure about this time need to be calculated
	}
	public void moveForward() throws InterruptedException
	{
		balancingThread.turn(0);
		balancingThread.setSpeed(8);
		Thread.sleep(1000);
	}
	public void fullStop()
	{
		balancingThread.setSpeed(0);
		balancingThread.turn(0);
	}
	
}

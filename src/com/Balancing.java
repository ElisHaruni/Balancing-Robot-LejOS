package com;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.UnregulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

/**
 * 
 * @author Elis Haruni
 * 
 * This class will keep the balance of the robot. It is done in the run() method.
 * It also provides methods to set the speed and direction of the robot.
 * 
 */
public class Balancing extends Thread {


	private UnregulatedMotor rightMotor = new UnregulatedMotor(MotorPort.A);
	private UnregulatedMotor leftMotor = new UnregulatedMotor(MotorPort.D);
	private EV3GyroSensor gyroSensor = new EV3GyroSensor(SensorPort.S2);
	
	private double speed = 0; 		
	private double direction = 0; 
	
	/**
	 * Move robot forward,backward or stop with speed=0.
	 * @param s - double speed we want to set to motors[5(forward),-5(backward)] 
	 * Speed can not be bigger than 10 because the robot will fall
	 */
	public void setSpeed(double s) {
		if (s>10) s=10;			// Limit speed
		if (s<-10) s=-10;
		speed=s;
	}
	
	/**
	 *  Move Robot forward o backward after he is balanced 
	 *  
	 * @param i - speed value [10(forward),-10(backward)] 
	 */
	public void increaseSpeed(double i){
		setSpeed(speed+i);
	}
	
	
	/**
	 * Turn Robot right or left while moving or standing still.
	 * @param d - Direction of robot [-50(left),50(right)]
	 * This is NOT how much the robot turns, but how fast is turns.
	 */
	
	public void turn(double d) {
		if (d>50) d=50;
		if (d<-50)d=-50;
		direction=d;
	}
public void run () {
		
		double gAng = 0; // gyro angle in degrees
		double gSpd = 0; // gyro angle speed in degrees/sec
		double mPos = 0; // Rotation angle of motor in degrees
		double mSpd = 0; // Rotation speed of motor in degrees/sec
		double mSum = 0;
		double mD = 0;
		double mDP1 = 0;
		double mDP2 = 0;
		double mDP3 = 0;
		double pwr = 0;        // motor power in [-100,100]
		int loopCount=0;	   // postpone activation of the motors until dt in the loop is stable
		boolean ready=false;   // for piruette we need to be balanced before
		
		rightMotor.resetTachoCount();
		leftMotor.resetTachoCount();
		gyroSensor.reset();
		
		SampleProvider gyroReader = gyroSensor.getRateMode(); //get angle velocity
		float[] sample = new float[gyroReader.sampleSize()];
		long lastTimeStep = System.nanoTime();
		
		
		
		gAng=-0.25;
		Sound.beepSequenceUp();
		Thread.currentThread().setPriority(MAX_PRIORITY);
		
		while(!Button.ESCAPE.isDown())
		{
		
			long now = System.nanoTime();
			double dt = (now - lastTimeStep) / 1000000000.0;	// Time step in seconds
			lastTimeStep = now;
			
			// Get gyro angle and speed
			gyroSensor.fetchSample(sample, 0);
			gSpd = -sample[0]; // invert sign to undo negation in class EV3GyroSensor
			gAng = gAng + (gSpd * dt); // integrate angle speed to get angle

			// Get motor rotation angle and rotational angle speed
			double mSumOld = mSum;
			double rightTacho = rightMotor.getTachoCount();
			double leftTacho = leftMotor.getTachoCount();
			mSum = rightTacho + leftTacho;
			mD = mSum - mSumOld;
			mPos = mPos + mD;
			mSpd = ((mD + mDP1 + mDP2 + mDP3) / 4.0) / dt; // motor rotational speed
			mDP3 = mDP2;
			mDP2 = mDP1;
			mDP1 = mD;
			
			
			// Compute new motor power
			mPos -= speed;	       // make Robot go forward or backward
			pwr = 0.08 * mSpd + 0.12 * mPos + 0.8 * gSpd + 15 * gAng;
			if (pwr > 100) pwr = 100;
			if (pwr < -100) pwr = -100;
			if (ready){
				rightMotor.setPower((int) (pwr - direction));
				leftMotor.setPower((int) (pwr + direction));
			}

			Delay.msDelay(10);
			loopCount++;
			if (loopCount==10) ready=true;
						
		
		}
		
		//releasing the resources
		rightMotor.close();
		leftMotor.close();
		gyroSensor.close();
	}


/*
public static void main(String[] args) {
	
	 System.out.println("I will be balanced soon!!");
	 
	 Balancing boy = new Balancing();
	 boy.start();
	 boy.setSpeed(2);
	 Delay.msDelay(9000);
	 
	 boy.setSpeed(5);
	 
	 Delay.msDelay(9000);
	 boy.increaseSpeed(10);
	 Delay.msDelay(5000);
	 boy.setSpeed(0);

	 Delay.msDelay(9000);
	 
	 boy.turn(50);
	 
	 
        //Button.waitForAnyPress();
}
*/

}

package assignment2;

import java.io.*;
import lejos.nxt.*;
import lejos.robotics.localization.OdometryPoseProvider;

public class DataThread extends Thread {
	/* Instance vars */
	private boolean fin;
	private OdometryPoseProvider odom;
	private FileOutputStream FOSOut;
	private File data;
	private DataOutputStream DOSOut;

	/* Constant */
	private static int DELTA_T = 250; 
	
	/* Constructor */
	public DataThread(OdometryPoseProvider odom){
		this.fin = false;
		this.data = new File("log.csv");
		
		try {
			this.FOSOut = new FileOutputStream(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
		
		this.odom = odom;
		this.DOSOut = new DataOutputStream(this.FOSOut);
	};
	
	/* Setters and getters */
	public boolean isFin() {
		return fin;
	};

	public void setFin(boolean fin) {
		this.fin = fin;
	};

	/* The core of the thread */
	public void run(){
		while(!fin){
			// waiting: so that we don't end up with the mythical 1gb csv file that breaks excel
			try {
				Thread.sleep(DELTA_T);
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
			
			// writing current coordinates and other details
			try {
				this.DOSOut.writeChars(odom.getPose().getX() + ", " + odom.getPose().getY() + ", " + odom.getPose().getHeading() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			};
			
			// for the drawing of the trajectory to the lcd
			// from exercise 1
			LCD.setPixel(((int) odom.getPose().getX() / 3) + 10, ((int) odom.getPose().getY() / 3) + 10, 1);
			
			if(this.isFin()){
				break;
			};
		};
		
		// closing the file
		try { 
			this.DOSOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		};
	};
};

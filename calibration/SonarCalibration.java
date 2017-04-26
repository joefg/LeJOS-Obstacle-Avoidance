package calibration;

import lejos.nxt.*;

public class SonarCalibration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		UltrasonicSensor thisSonar = new UltrasonicSensor(SensorPort.S1);
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Distance (cm): "+thisSonar.getDistance());
		}

	}

}

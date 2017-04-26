package calibration;

import lejos.nxt.*;

public class CSCalibration {
	
	public static void main(String[] args){
		ColorSensor cs = new ColorSensor(SensorPort.S2);
		//cs.setFloodlight(true);
		while(true){
			System.out.println(cs.getColorID());
			if(cs.getColorID()==ColorSensor.Color.RED){
				System.out.println("Red!"); 
			};
		}
	};
	
}

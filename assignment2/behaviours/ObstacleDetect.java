 package assignment2.behaviours;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;

public class ObstacleDetect implements Behavior {

	/* Instance variables */
	private UltrasonicSensor sonar;
	private DifferentialPilot diffp;
	private Navigator nav;
	private boolean suppressed = false;
	
	/* Constants */
	private static int AVOID_DISTANCE = 15; // set to 15cm
	
	/* Constructor */
	public ObstacleDetect(SensorPort s, DifferentialPilot diffp, Navigator nav) {
		this.sonar = new UltrasonicSensor(s);
		this.diffp = diffp;
		this.nav = nav;
	};

	/* Overridden methods */
	@Override
	public void action() {
		suppressed = false;
		if(!suppressed){
			nav.stop();
			// for rationale on this method, see the writeup/notes
			diffp.setTravelSpeed(10);
			diffp.setRotateSpeed(25);
			diffp.rotate(90);
			diffp.travel(1.5*AVOID_DISTANCE);
		};
	};

	@Override
	public void suppress() {
		suppressed = true;
	};

	@Override
	public boolean takeControl() {
		return (sonar.getDistance() <= AVOID_DISTANCE);
	};
};
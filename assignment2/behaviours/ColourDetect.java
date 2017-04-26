package assignment2.behaviours;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;

public class ColourDetect implements Behavior {

	/* Instance variables */
	private ColorSensor cs;
	private boolean suppressed = false;
	private Navigator nav;

	/* Constructor */
	public ColourDetect(SensorPort s, Navigator nav) {
		this.cs = new ColorSensor(s);
		this.nav = nav;
	};

	/* Methods */
	@Override
	public void action() {
		suppressed = false;
		if(!suppressed){
			nav.addWaypoint(0, 0);
			nav.followPath();
		};
	};

	@Override
	public void suppress() {
		suppressed = true;
	};

	@Override
	public boolean takeControl() {
		return (cs.getColorID() == ColorSensor.Color.RED);
	};
};

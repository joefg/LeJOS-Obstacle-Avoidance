package assignment2.behaviours;

import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;
import lejos.nxt.*;

public class AdvanceForward implements Behavior {
	
	private boolean suppressed = false;
	private DifferentialPilot diffp;
	private Navigator nav;
	
	/* Constructor */
	public AdvanceForward(DifferentialPilot diffp, Navigator nav) {
		this.diffp = diffp;
		this.nav = nav;
	};
	
	/* Overridden Methods */
	@Override
	public void action() {
		suppressed = false;
		diffp.setRotateSpeed(30);
		diffp.setTravelSpeed(30);
		while(!suppressed){
			nav.followPath();
		};
	};
	
	@Override
	public void suppress() {
		suppressed = true;
	};

	@Override
	public boolean takeControl() {
		return true;
	};
};

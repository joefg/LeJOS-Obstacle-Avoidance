package assignment2.behaviours;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.subsumption.Behavior;

public class ColourSearch implements Behavior {

	/* Instance variables */
	private boolean suppressed = false;
	private DifferentialPilot diffp;
	private Navigator nav;
	
	/* Constants */
	private static int SEARCH_LENGTH = 30; // 30cm sweep
	
	/* Constructor */
	/**
	 * @param cs
	 * @param diffp
	 * @param nav
	 */
	public ColourSearch(DifferentialPilot diffp, Navigator nav) {
		this.diffp = diffp;
		this.nav = nav;
	};
	
	/* Overridden methods */
	@Override
	public void action() {
		suppressed = false;
		diffp.setTravelSpeed(15);
		diffp.setRotateSpeed(20);
		while(!suppressed){
			/* I don't think ColourDetect is playing terribly
			 * nicely with diffp.
			 */
			diffp.rotate(30);
			if(suppressed){
				break;
			};
			diffp.travel(SEARCH_LENGTH / 3);
			if(suppressed){
				break;
			};
			diffp.travel(SEARCH_LENGTH / 3);
			if(suppressed){
				break;
			};
			diffp.travel(SEARCH_LENGTH / 3);
			if(suppressed){
				break;
			};
			diffp.travel(-SEARCH_LENGTH);
			if(suppressed){
				break;
			};
		};
	};

	@Override
	public void suppress() {
		suppressed = true;
	};

	@Override
	public boolean takeControl() {
		return nav.pathCompleted();
	};
};

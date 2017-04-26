package assignment2;

import lejos.nxt.*;
import lejos.robotics.localization.*;
import lejos.robotics.navigation.*;
import lejos.robotics.subsumption.*;
import assignment2.behaviours.*;

public class NavigatorBot {

	/* Instance variables */
	private DifferentialPilot diffp;
	private OdometryPoseProvider odom;
	private Behavior[] behaviourArray;
	private Arbitrator arb;
	private DataThread dt;
	private Navigator nav;

	/* Constants
	 * These values were acquired from the calibration in
	 * assignment 1.
	 */
	private static float WHEEL_DIAMETER = 3.25f;
	private static float TRACK_WIDTH = 18.0f;
	private static float ROTATE_SPEED = 25;

	/* Constructor */
	public NavigatorBot(){
		/*
		 * Motors: Ports 1 (left) and 3 (right)
		 * Sensors:
		 * 	- Sonar: port S1
		 * 	- Colour: port S2
		 */
		this.diffp = new DifferentialPilot(WHEEL_DIAMETER, TRACK_WIDTH, Motor.A, Motor.C);
		diffp.setRotateSpeed(ROTATE_SPEED);
		this.odom = new OdometryPoseProvider(this.diffp);
		this.nav = new Navigator(this.diffp);
		this.dt = new DataThread(this.getOdom());
		nav.singleStep(true);

		/* Behaviour array: this gets passed into the arbitrator.*/
		behaviourArray = new Behavior[]{
				new AdvanceForward(this.diffp, this.nav),
				new ObstacleDetect(SensorPort.S1, this.diffp, this.nav),
				new ColourSearch(this.diffp, this.nav),
				new ColourDetect(SensorPort.S2, this.nav),
				new FinishedBehaviour(this.dt),
		};

		arb = new Arbitrator(behaviourArray, true);
	};

	/* Methods */
	public void start(){
		dt.start();
		arb.start();
	};

	public void stop(){
		dt.setFin(true);
		this.nav.stop();
	};

	public OdometryPoseProvider getOdom(){
		return odom;
	};

	public void addWaypoint(float x, float y){
		this.nav.addWaypoint(x, y);
	};

	/* Main */
	public static void main(String[] args) throws InterruptedException {
		NavigatorBot nav = new NavigatorBot();
		nav.addWaypoint(150, 150);
		nav.start();
		nav.stop();
	};
};

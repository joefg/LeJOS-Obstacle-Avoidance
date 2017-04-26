package assignment2.behaviours;

import lejos.nxt.*;
import lejos.robotics.subsumption.*;

import assignment2.DataThread;

public class FinishedBehaviour implements Behavior {

	private boolean suppressed = false;
	private DataThread dt;

	/**
	 * @param suppressed
	 * @param dt
	 */
	public FinishedBehaviour(DataThread dt) {
		this.dt = dt;
	}

	@Override
	public void action() {
		suppressed = false;
		dt.setFin(true);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
	};

	@Override
	public void suppress() {
		suppressed = true;
	};

	@Override
	public boolean takeControl() {
		return Button.RIGHT.isDown();
	};
};

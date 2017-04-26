# LeJOS-Obstacle-Avoidance
A simple method of obstacle avoidance in LeJOS, using subsumption.

## Prerequisites
You will need [LeJOS](http://www.lejos.org/), which is a Java runtime and VM for Lego Mindstorms, along with a Java IDE of your choosing with the toolchain needed to build and send the compiled bytecode to the Mindstorms unit.

### Hardware
* A Lego Mindstorms differential drive robot, with the following sensors:
  - Colour sensor, facing down;
  - Sonar sensor, facing the front, for distance detection.

  ![Bot, top down.](/img/bot-topdown.jpg)

## Architecture
We will be using the subsumption method for this (see: Brooks, "A Robust Layered Control System For A Mobile Robot", 1986 [here](http://people.csail.mit.edu/brooks/papers/AIM-864.pdf)).
![Method](/img/subsumption.png)

The methods are in the code, but they can be described like this:

| Behaviour | Trigger | Action |
| --------- | ------- | ------ |
| AdvanceForward | Always On | Move To Next Waypoint |
| Obstacle Detect | ```UltrasonicSensor.getDistance() < 20cm``` | Navigate around object |
| ColourSearch | End Of List Of Waypoints | Search for coloured square |
| ColourDetect | ```ColourSensor.getID() == ColourSensor.COLORS.RED``` | Add waypoint at (0, 0) to go back to point of origin (0,0) |
| FinishedBehaviour | ```Button.RIGHT.isDown()``` | Stops DataThread thread so that the CSV file can close without losing information |

## Trajectories
### On LCD
The trajectory that the robot has taken, _according to the robot_ (the usual caveats about Relative Localisation apply) gets drawn to the screen, using this:

```Java
// summarised from DataThread.java
...
while(1){
  // see the source code for more details
  LCD.setPixel(((int) odom.getPose().getX() / 3) + 10, ((int) odom.getPose().getY() / 3) + 10, 1);
};
...
```

### On CSV File
The coordinates of the robot's position gets written to a CSV file every 250ms, for an effective 4Hz refresh rate, to 'log.csv'.

To interpret this, you can use Excel to draw the trajectory using Charts, or you can use something like R, or matplotlib.

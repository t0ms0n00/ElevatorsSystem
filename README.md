# ElevatorsSystem

## Installation

The project was built with using following Maven command ``` mvn package ```.  

To run app, you should use this command in the root directory by the command prompt.

``` java -cp target/elevatorSystem-1.0-SNAPSHOT.jar Main ```

## Settings

Firstly, we need to fill two input fields, to determine, how many floors and elevators our system should handle. 
The default settings are 5 elevators and 10 floors (ground floor and floors from 1 to 9). 
This can be changed to anything, but more objects - larger app screen, so it might be bounded-up by 20 elevators and floors to contains it on screen.  

![settings](/images/settings.jpg)

When the program parameters are set, you need to click button to submit your choice.

## System

After submitting proper parameters, you should see a interactive screen.

![elevators_init](/images/app_start.jpg)

That simple GUI has floors for every elevator. Colors has different meaning, sometimes there are additional labels describing what is happening with elevator on that floor.  
Colors legend:
- green - elevator position (when it has no task == no target) or target position,
- red - elevator position,
- gray - floor where nothing happens for now.  

Also red buttons on the right can contain labels signaling a request and its direction.
The request is invoked on mouse click:
- left mouse button click handles request in direction up,
- right mouse button click handles request in direction down.

## Example

![animation](/images/animation.gif)

## Algorithm

Algorithm is deciding, which elevator should handle request, by finding instance with the shortest distance to target.
Normally distance is calculated as difference between elevator's floor and target floor number.
But there are some extra conditions when algorithm will give more complex result.

- When elevator is on requested floor while request is appearing, that elevator is ommited in choosing process. I assumed that request should be done by elevator that can drive to that floor from another.
- When elevator is above requested floor and requested direction is "up", elevator cannot be chosen, in critical example, it may never reach that floor so it is ommited for now.
- When elevator is below requested floor and direction is "down" - same solution as previous.
- When elevator is doing task in opposite direction to requested, to distance is added twice distance to actual target - elevator should finish all targets in one direction first.

If there are no elevator that can handle some request, it is rejected. Every elevator has it's own scheduler, which schedule tasks in following order:
- firstly get tasks from direction that you move and are reachable, from closest
- next get tasks from the opposite direction
- some tasks are unreachable, so they are not included in ordering process.

# ElevatorsSystem

## Installation

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
- green - elevator position (when it has no task == no target) or target,
- red - elevator position after doing step,
- gray - floor where nothing happens for now.  

Also red buttons on the right can contain labels signaling a request and its direction.
The request is invoked on mouse click:
- left mouse button click handles request in direction up,
- right mouse button click handles request in direction down.

## Example

![animation](/images/animation.gif)

## Algorithm

package GUI;

import ElevatorSystem.ElevatorSystem;
import Tuples.Triple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimulationScreen {
    JFrame settingsScreen, simulationScreen;
    int elevatorsNumber, floorsNumber;
    ElevatorSystem elevatorSystem;
    Door[][] doors;
    ElevatorButton[] buttons;

    public SimulationScreen(){
        settingsScreen = new JFrame();
        settingsScreen.setSize(600, 500);

        displaySettingsScreen();

        settingsScreen.setLayout(null);
        settingsScreen.setResizable(false);
        settingsScreen.setVisible(true);
        settingsScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void displaySettingsScreen(){
        JLabel labelInputElevatorsNumber = new JLabel("Input number of elevators");
        labelInputElevatorsNumber.setBounds(100, 50, 400, 50);
        settingsScreen.add(labelInputElevatorsNumber);

        JTextField inputElevatorsNumber = new JTextField("5");
        inputElevatorsNumber.setBounds(100, 100, 400, 50);
        settingsScreen.add(inputElevatorsNumber);

        JLabel labelInputFloorsNumber = new JLabel("Input number of floors");
        labelInputFloorsNumber.setBounds(100, 150, 400, 50);
        settingsScreen.add(labelInputFloorsNumber);

        JTextField inputFloorsNumber = new JTextField("10");
        inputFloorsNumber.setBounds(100, 200, 400, 50);
        settingsScreen.add(inputFloorsNumber);

        JButton sumbitButton = new JButton("Submit");
        sumbitButton.setBounds(200, 300, 200, 50);
        sumbitButton.addActionListener(e -> {
            elevatorsNumber = Integer.parseInt(inputElevatorsNumber.getText());
            floorsNumber = Integer.parseInt(inputFloorsNumber.getText());
            startSimulation();
        });
        settingsScreen.add(sumbitButton);
    }

    public void startSimulation(){
        settingsScreen.setVisible(false);
        settingsScreen.dispose();

        elevatorSystem = new ElevatorSystem(elevatorsNumber, floorsNumber);
        doors = new Door[elevatorsNumber][floorsNumber];
        buttons = new ElevatorButton[floorsNumber];

        simulationScreen = new JFrame();
        simulationScreen.setSize(elevatorsNumber*100 + 100 + 100, floorsNumber * 50 + 150);
        simulationScreen.setVisible(true);
        simulationScreen.setLayout(null);
        simulationScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        simulationScreen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getX() > elevatorsNumber * 100 + 110  && e.getX() < elevatorsNumber * 100 + 160){
                    int floor = floorsNumber - (e.getY())/50;
                    if(SwingUtilities.isLeftMouseButton(e)){
                        buttons[floor].updateButton("UP");
                        elevatorSystem.pickup(floor, 1);
                    }
                    else if(SwingUtilities.isRightMouseButton(e)){
                        buttons[floor].updateButton("DOWN");
                        elevatorSystem.pickup(floor, -1);
                    }
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        for(int f = 0; f < floorsNumber; f++){  /// buttons and doors
            for(int e = 0; e < elevatorsNumber; e++) {
                doors[e][f] = new Door(e,f,floorsNumber);     /// grid element
                simulationScreen.add(doors[e][f].get());
            }
            buttons[f] = new ElevatorButton(f, elevatorsNumber, floorsNumber); /// button column
            simulationScreen.add(buttons[f].get());
        }

        JButton stepButton = new JButton("Make step");
        stepButton.setBounds(simulationScreen.getWidth()/2 - 100, simulationScreen.getHeight() - 100, 200, 50);
        stepButton.addActionListener(e -> {
            elevatorSystem.step();
            drawState();
        });
        simulationScreen.add(stepButton);

        drawState();
    }

    private void drawState(){
        for(int f = 0; f < floorsNumber; f++){
            for(int e = 0; e < elevatorsNumber; e++){
                doors[e][f].resetDoorStyle();
            }
            buttons[f].resetButtonLabel();
        }
        this.updateState();
    }

    private void updateState(){
        Triple[] updates = elevatorSystem.status();
        for(Triple update: updates){
            int elevatorID = update.getElevatorId();
            int actualFloor = update.getActualFloor();
            int targetFloor = update.getTargetFloor();
            doors[elevatorID][actualFloor].updateDoor(Color.red);
            doors[elevatorID][targetFloor].updateDoor(Color.green, "TARGET");
            if(actualFloor == targetFloor)
                doors[elevatorID][targetFloor].openDoor();
        }
    }

}

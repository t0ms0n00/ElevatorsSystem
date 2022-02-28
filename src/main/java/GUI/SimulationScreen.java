package GUI;

import ElevatorSystem.ElevatorSystem;
import Tuples.Triple;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimulationScreen {
    JFrame settingsScreen, simulationScreen;
    int elevatorsNumber, floorsNumber;
    ElevatorSystem elevatorSystem;
    JPanel[][] doors;
    JPanel[] buttons;

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
        sumbitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elevatorsNumber = Integer.parseInt(inputElevatorsNumber.getText());
                floorsNumber = Integer.parseInt(inputFloorsNumber.getText());
                startSimulation();
            }
        });
        settingsScreen.add(sumbitButton);
    }

    public void startSimulation(){
        elevatorSystem = new ElevatorSystem(elevatorsNumber, floorsNumber);
        doors = new JPanel[elevatorsNumber][floorsNumber];
        buttons = new JPanel[floorsNumber];
        settingsScreen.setVisible(false);
        settingsScreen.dispose();
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
                        updateButton(floor, "UP");
                        elevatorSystem.pickup(floor, 1);
                    }
                    else if(SwingUtilities.isRightMouseButton(e)){
                        updateButton(floor, "DOWN");
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

        for(int f = 0; f < floorsNumber; f++){
            for(int e = 0; e < elevatorsNumber; e++) {
                doors[e][f] = new JPanel();     /// grid element
                doors[e][f].setBounds(e*100 + 50, (floorsNumber-f)*50-40, 90, 40);
                doors[e][f].add(new JLabel(""));
                simulationScreen.add(doors[e][f]);
            }
            buttons[f] = new JPanel(); /// button column
            buttons[f].setBounds(elevatorsNumber*100 + 100, (floorsNumber-f)*50-40, 50, 40);
            buttons[f].setBackground(Color.red);
            buttons[f].add(new JLabel(""));
            simulationScreen.add(buttons[f]);
        }

        JButton stepButton = new JButton("Make step");
        stepButton.setBounds(simulationScreen.getWidth()/2 - 100, simulationScreen.getHeight() - 100, 200, 50);
        stepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elevatorSystem.step();
                drawState();
            }
        });
        simulationScreen.add(stepButton);

        drawState();
    }

    private void drawState(){
        for(int f = 0; f < floorsNumber; f++){
            for(int e = 0; e < elevatorsNumber; e++){
                doors[e][f].setBackground(Color.gray);
                doors[e][f].setBorder(new LineBorder(Color.black));
                JLabel label = (JLabel)doors[e][f].getComponent(0);
                label.setText("");
            }
            JLabel label = (JLabel)buttons[f].getComponent(0);
            label.setText("");
        }
        this.updateState();
    }

    private void updateState(){
        Triple[] updates = elevatorSystem.status();
        for(Triple update: updates){
            updateDoor(update.getElevatorId(), update.getActualFloor(), Color.red);
            updateDoor(update.getElevatorId(), update.getTargetFloor(), Color.green, "TARGET");
            if(update.getActualFloor() == update.getTargetFloor()) openDoor(update.getElevatorId(), update.getActualFloor());
        }
    }

    private void updateDoor(int elevatorNumber, int floor, Color color){
        doors[elevatorNumber][floor].setBackground(color);
    }

    private void updateDoor(int elevatorNumber, int floor, Color color, String text){
        doors[elevatorNumber][floor].setBackground(color);
        JLabel label = (JLabel)doors[elevatorNumber][floor].getComponent(0);
        label.setText(text);
    }

    private void updateButton(int floor, String text){
        JLabel label = (JLabel)buttons[floor].getComponent(0);
        label.setText(text);
    }

    private void openDoor(int elevatorNumber, int floor){
        JLabel label = (JLabel)doors[elevatorNumber][floor].getComponent(0);
        label.setText("OPEN");
    }
}
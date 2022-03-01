package GUI;

import javax.swing.*;
import java.awt.*;

public class ElevatorButton extends JPanel {

    public ElevatorButton(int floor, int elevatorsNumber, int floorsNumber){
        this.setBounds(elevatorsNumber*100 + 100, (floorsNumber-floor)*50-40, 50, 40);
        this.setBackground(Color.red);
        this.add(new JLabel(""));
    }

}

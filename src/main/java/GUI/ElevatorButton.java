package GUI;

import javax.swing.*;
import java.awt.*;

public class ElevatorButton {

    private JPanel style;

    public ElevatorButton(int floor, int elevatorsNumber, int floorsNumber){
        style = new JPanel();
        this.style.setBounds(elevatorsNumber*100 + 100, (floorsNumber-floor)*50-40, 50, 40);
        this.style.setBackground(Color.red);
        this.style.add(new JLabel(""));
    }

    public JPanel get(){
        return this.style;
    }

    public void resetButtonLabel(){
        JLabel label = (JLabel)this.style.getComponent(0);
        label.setText("");
    }

    public void updateButton(String text){
        JLabel label = (JLabel)this.style.getComponent(0);
        label.setText(text);
    }

}

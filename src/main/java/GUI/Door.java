package GUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Door{

    JPanel style;

    public Door(int elevator, int floor, int floorsNumber){
        style = new JPanel();
        this.style.setBounds(elevator*100 + 50, (floorsNumber-floor)*50-40, 90, 40);
        this.style.add(new JLabel(""));
        this.style.setBackground(Color.gray);
        this.style.setBorder(new LineBorder(Color.black));
    }

    public JPanel get(){
        return this.style;
    }

    public void resetDoorStyle(){
        this.style.setBackground(Color.gray);
        this.style.setBorder(new LineBorder(Color.black));
        JLabel label = (JLabel)this.style.getComponent(0);
        label.setText("");
    }

    public void updateDoor(Color color){
        this.style.setBackground(color);
    }

    public void updateDoor(Color color, String text){
        this.style.setBackground(color);
        JLabel label = (JLabel)this.style.getComponent(0);
        label.setText(text);
    }

    public void openDoor(){
        JLabel label = (JLabel)this.style.getComponent(0);
        label.setText("OPEN");
    }
}

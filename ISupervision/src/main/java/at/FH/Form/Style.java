package at.FH.Form;

import at.FH.GUI.ButtonUI;
import at.FH.GUI.PanelUI;

import javax.swing.*;
import java.awt.*;

public class Style {

    private JPanel mainPanel;

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        useStyle(mainPanel);
        getAllElements(mainPanel);
    }

    private void getAllElements(JPanel panel){
        if(panel != null){
            for (Component myComp : panel.getComponents()) {
                useStyle(myComp);
                if (myComp instanceof JPanel p) {
                    getAllElements(p);
                }
            }
        }
    }

    private void useStyle(Component myComp){
        if(myComp instanceof JPanel panel){
            panel.setUI(new PanelUI());
        } else if(myComp instanceof JButton button){
            button.setUI(new ButtonUI());
        } else if(myComp instanceof JLabel label){
            label.setForeground(Color.WHITE);
        }
    }





}

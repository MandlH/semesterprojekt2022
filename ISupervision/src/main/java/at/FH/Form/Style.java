package at.FH.Form;

import at.FH.GUI.ButtonUI;
import at.FH.GUI.LabelUI;
import at.FH.GUI.PanelUI;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Style {

    private boolean flag = false;
    Border border = BorderFactory.createLineBorder(Color.red, 1);

    public void setMainPanel(JPanel mainPanel) {
        useStyle(mainPanel);
        getAllElements(mainPanel);
        flag = true;
    }

    public void setTabbedPane(JTabbedPane pane){
        getAllElements(pane);
    }

    /**
     * Recursive method that formats all objects in a panel
     * @param panel
     */
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

    /**
     * Recursive method that formats all objects in a pane
     * @param panel
     */
    private void getAllElements(JTabbedPane panel){
        if(panel != null){
            for (Component myComp : panel.getComponents()) {
                useStyle(myComp);
                if (myComp instanceof JPanel p) {
                    getAllElements(p);
                }
            }
        }
    }

    /**
     * format the single comps
     * @param myComp
     */
    private void useStyle(Component myComp){
        if(myComp instanceof JPanel panel){
            panel.setUI(new PanelUI());
        } else if(myComp instanceof JButton button){
            button.setUI(new ButtonUI());
        } else if(myComp instanceof JLabel label){
            label.setUI(new LabelUI());
        } else if(myComp instanceof JTextField textField) {
            if (textField.getText().isEmpty() && flag) {
                textField.setBorder(border);
            } else {
                textField.setBorder(null);
            }
        }
    }
}

package at.FH.GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.*;

public class LabelUI extends BasicLabelUI {

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setForeground(Color.white);
        c.setFont(c.getFont().deriveFont(15f));
        super.paint(g, c);
    }
}

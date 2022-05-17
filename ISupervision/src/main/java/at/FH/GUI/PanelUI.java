package at.FH.GUI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;


public class PanelUI extends BasicPanelUI {

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        c.setBackground(new Color(42,42,42));
        super.paint(g, c);
    }
}

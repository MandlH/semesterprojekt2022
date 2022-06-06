package at.FH.Form;

import at.FH.Database.Connection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Message extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lbl_msg;
    private static final Style style = new Style();
    private String msg;

    /**
     * Constructor set all Listener and default settings
     */
    public Message() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
    }

    /**
     * close the message form
     */
    private void onOK() {
        // add your code here
        dispose();
    }

    /**
     * open the message form
     */
    public void open(String msg){
        this.msg = msg;
        pack();
        set();
        setVisible(true);
    }

    /**
     * set all properties of the message form
     */
    private void set(){
        setTitle("Login");

        lbl_msg.setText(msg);

        style.setMainPanel(contentPane);

        setAlwaysOnTop(true);

        setResizable(false);
        setSize(400, 200);
        setLocationRelativeTo(null);
    }
}

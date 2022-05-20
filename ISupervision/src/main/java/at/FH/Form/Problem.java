package at.FH.Form;

import at.FH.User.Registration;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Problem extends JDialog {
    private JPanel contentPane;
    private JButton buttonSend;
    private JButton buttonCancel;
    private JPanel jp4;
    private JTextField txt_firstname;
    private JTextField txt_secondname;
    private JTextField txt_email;
    private JTextArea ta_area;
    private JComboBox cb_problem;


    private static final Style style = new Style();

    public Problem() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSend);

        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        if(!txt_firstname.getText().isEmpty() && !txt_secondname.getText().isEmpty() && !txt_email.getText().isEmpty()){
            Registration reg = new Registration(
                    txt_email.getText(), cb_problem.getSelectedItem().toString(), txt_firstname.getText(), txt_secondname.getText(), ta_area.getText()
            );
        } else {
            style.setMainPanel(contentPane);
            repaint();
        }
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void open(){
        pack();
        set();
        setVisible(true);
    }

    private void set(){
        setTitle("Login");

        style.setMainPanel(contentPane);

        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);

    }

}

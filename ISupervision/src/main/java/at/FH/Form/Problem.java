package at.FH.Form;

import javax.swing.*;
import java.awt.event.*;

public class Problem extends JDialog {
    private JPanel contentPane;
    private JButton buttonSend;
    private JButton buttonCancel;
    private JPanel jp4;
    private JTextField firstname;
    private JTextField secondname;
    private JTextField email;
    private JTextArea textArea1;
    private JComboBox comboBox1;

    public Problem() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonSend);

        buttonSend.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!secondname.getText().equals("") && !firstname.getText().equals("") && !email.getText().equals(""))
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
        dispose();
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

        Style style = new Style();
        style.setMainPanel(contentPane);

        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);

    }

}

package at.FH.Form;

import at.FH.Database.Connection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JButton buttonCancel;
    private JButton canTLoginButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JLabel con;

    public Login() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonLogin);

        canTLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Problem p = new Problem();
                p.open();
            }
        });

        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLogin();
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

    private void onLogin() {
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

        if(Connection.hostAvailable()){
            con.setText("Connection: Online");
            con.setForeground(Color.GREEN);
        }
        else{
            con.setText("Connection: Offline");
            con.setForeground(Color.red);
        }

        setResizable(false);
        setSize(400, 200);
        setLocationRelativeTo(null);

    }

}

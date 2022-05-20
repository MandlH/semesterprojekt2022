package at.FH.Form;

import at.FH.Database.Connection;
import at.FH.Database.HibernateSupport;
import at.FH.Exception.ObjectNotFound;
import at.FH.General.Check;
import at.FH.User.Admin;
import at.FH.User.Assistant;
import at.FH.User.LoggedInUser;
import at.FH.User.Student;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JDialog {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JButton buttonCancel;
    private JButton canTLoginButton;
    private JTextField txt_email;
    private JPasswordField txt_password;
    private JLabel con;

    private LoggedInUser user;

    private static final Check checker = new Check();

    private static final Style style = new Style();

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
                try {
                    onLogin();
                } catch (ObjectNotFound ex) {
                    ex.printStackTrace();
                }
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

    private void onLogin() throws ObjectNotFound {
        //TODO

        if(!txt_email.getText().isEmpty() && txt_password.getPassword().length != 0){

            Student student;
            Admin admin;
            Assistant assistant;

            try{
                admin = HibernateSupport.readOneObjectByID(Admin.class, txt_email.getText());
                if(checker.checkPassword(txt_password.getPassword(), admin.getPassword())){
                    user = new LoggedInUser(admin);
                    dispose();
                }
            }catch (NullPointerException ignore){}

            try{
                assistant = HibernateSupport.readOneObjectByID(Assistant.class, txt_email.getText());
                if(checker.checkPassword(txt_password.getPassword(), assistant.getPassword())){
                    user = new LoggedInUser(assistant);
                    dispose();
                }
            }catch (NullPointerException ignore){}

            try {
                student = HibernateSupport.readOneObjectByID(Student.class, txt_email.getText());
                if(checker.checkPassword(txt_password.getPassword(), student.getPassword())){
                    user = new LoggedInUser(student);
                    dispose();
                }
            }catch (NullPointerException ignore){}
        } else {
            set();
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

        if(Connection.hostAvailable()){
            con.setText("Connection: Online");
            con.setForeground(Color.GREEN);
        }
        else{
            con.setText("Connection: Offline");
            con.setForeground(Color.red);
        }

        setAlwaysOnTop(true);

        setResizable(false);
        setSize(400, 200);
        setLocationRelativeTo(null);

    }

}

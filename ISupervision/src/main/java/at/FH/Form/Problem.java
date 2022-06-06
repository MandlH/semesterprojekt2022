package at.FH.Form;

import at.FH.User.Admin;
import at.FH.User.Assistant;
import at.FH.User.Registration;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import at.FH.Database.HibernateSupport;
import at.FH.Form.Message;

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
    private JPasswordField pw_password1;
    private JPasswordField pw_password2;
    private static final Message message = new Message();
    private static final at.FH.General.Check check = new at.FH.General.Check();


    private static final Style style = new Style();

    /**
     * Constructor set all Listener and default settings
     */
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

    /**
     * Checks whether all fields are filled out and whether the new user does not exist yet
     */
    private void onOK() {
        String pw1 = new String(pw_password1.getPassword());
        String pw2 = new String(pw_password2.getPassword());
        if(!txt_firstname.getText().isEmpty() && !txt_secondname.getText().isEmpty() && !txt_email.getText().isEmpty() && pw1.equals(pw2)
        && check.checkEmailRegex(txt_email.getText())){
            if(HibernateSupport.readOneObjectByID(Registration.class, txt_email.getText()) != null ||
                    HibernateSupport.readOneObjectByID(Admin.class, txt_email.getText()) != null ||
                    HibernateSupport.readOneObjectByID(Assistant.class, txt_email.getText()) != null ||
                    HibernateSupport.readOneObjectByID(Student.class, txt_email.getText()) != null){
                message.open("User already exist");
                return;
            }


            Registration reg = new Registration(
                    txt_email.getText(), cb_problem.getSelectedItem().toString(), txt_firstname.getText(), txt_secondname.getText(), ta_area.getText(), pw1);
            HibernateSupport.beginTransaction();
            reg.save();
            HibernateSupport.commitTransaction();
            message.open("Your request got successfully sent");
            dispose();
        } else {
            style.setMainPanel(contentPane);
            repaint();
        }
    }

    /**
     * Close the problem form
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * Open the problem form
     */
    public void open(){
        pack();
        set();
        setVisible(true);
    }

    /**
     * set all properties of the problem form
     */
    private void set(){
        setTitle("Login");

        style.setMainPanel(contentPane);

        setResizable(false);
        setSize(600, 600);
        setLocationRelativeTo(null);

    }

}

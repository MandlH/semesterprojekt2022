package at.FH.Form;

import at.FH.Database.Connection;
import at.FH.Database.HibernateSupport;
import at.FH.Task.Project;
import at.FH.User.LoggedInUser;

import javax.swing.*;
import java.awt.*;

public class Student extends JFrame {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JTextField txt_email;
    private JTextField txt_firstname;
    private JTextField txt_secondname;
    private JTextField txt_password1;
    private JButton updateButton;
    private JCheckBox sureCheckBox;
    private JTextField txt_password2;
    private JLabel lbl_firstAccess;
    private JLabel lbl_lastAccess;
    private JPanel p_profile;
    private static final Style style = new Style();
    private at.FH.User.Student user;

    void Student(){


    }

    public void open(at.FH.User.Student user){
        this.user = user;
        for(Project p : user.getProjects())
            System.out.println(p.getEmail() + " " + p.getDescription());
        add(contentPane);
        pack();
        set();
        setVisible(true);
    }

    private void set(){
        setTitle("Form");

        txt_firstname.setText(user.getFirstname());
        txt_secondname.setText(user.getSecondname());
        txt_email.setText(user.getEmail());
        txt_password1.setText("Enter new password");
        txt_password2.setText("Repeat new password");
        lbl_lastAccess.setText(user.getLastAccess().toString());

        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width / 2,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height / 2);
        Dimension dim = new Dimension(450, 450);
        setMinimumSize(dim);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);

        style.setMainPanel(contentPane);
        style.setTabbedPane(tabbedPane1);
    }
}

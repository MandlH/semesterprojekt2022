package at.FH.Form;

import at.FH.Database.HibernateSupport;
import at.FH.User.Assistant;
import at.FH.User.LoggedInUser;
import at.FH.User.Role;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends JDialog {
    private JPanel contentPane;
    private JButton but_submit;
    private JButton but_close;
    private JTextArea ta_description;
    private JLabel lbl_submit;
    private JScrollPane sb_description;
    private JTextField txt_name;
    private JTextField txt_mark;
    private JTextField txt_assistant;
    private JTextField txt_deadline;
    private JLabel lbl_assistant;
    private JTextField txt_student;
    private JLabel lbl_student;
    private JComboBox cb_assistant;
    private static final Style style = new Style();
    private final Message message = new Message();
    private LoggedInUser user;
    private at.FH.Task.Project p;
    private at.FH.Task.Master m;
    private at.FH.Task.Bachelor b;
    private at.FH.Task.Type type;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private static final String WRONG_DATE = "<html>Wrong Date Format use: dd-MM-yyyy HH:mm:ss <br> 03-06-2022";

    /**
     * Constructor set all Listener and default settings
     */
    public Project() {
        sb_description.setViewportView(ta_description);
        setContentPane(contentPane);
        setModal(true);

        but_close.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        cb_assistant.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                txt_assistant.setText(e.getItem().toString());
            }
        });

        but_submit.addActionListener(e -> {
            if(user.getRole() == Role.STUDENT)
                onSubmit();
            else{
                switch (type){
                    case PROJECT -> onSaveProject();
                    case MASTER -> onSaveMaster();
                    case BACHELOR -> onSaveBachelor();
                }
            }
        });
    }

    /**
     * Mark the task as done and add the due date
     */
    private void onSubmit(){
        HibernateSupport.beginTransaction();
        switch (type){
            case PROJECT ->{
                p.setSubmit(new Date());
                p.save();
            }
            case BACHELOR ->{
                b.setSubmit(new Date());
                b.save();
            }
            case MASTER ->{
                m.setSubmit(new Date());
                m.save();
            }
        }
        HibernateSupport.commitTransaction();

        message.open("You marked your work as done");
    }

    /**
     * Checks if the Task is a new project or just an update
     * @return true: the task is just an update false: it is a new task
     */
    private boolean ifNew(){
        if(txt_student.getText().equals(""))
            return p == null && b == null && m == null;
        return false;
    }

    /**
     * get and set all info for the project, master and bachelor
     */
    private void onSaveProject(){
        Date deadline;
        try {
            deadline = sdf.parse(txt_deadline.getText());
        } catch (ParseException e) {
            message.open(WRONG_DATE);
            return;
        }
        if(ifNew()){
            at.FH.Task.Project project = new at.FH.Task.Project(null, ta_description.getText(), txt_name.getText(),
                    txt_assistant.getText(), deadline);
            if(user.getRole() == Role.ASSISTANT){
                user.getAssistant().addProject(project);
                project.setAssistant(user.getAssistant());
                HibernateSupport.beginTransaction();
                project.save();
                user.getAssistant().save();
                HibernateSupport.commitTransaction();
            } else if(user.getRole() == Role.ADMIN){
                HibernateSupport.beginTransaction();
                project.setAssistant(assistants.get(cb_assistant.getSelectedIndex()));
                project.save();
                assistants.get(cb_assistant.getSelectedIndex()).save();
                HibernateSupport.commitTransaction();
            }


        } else {
            p.setDescription(ta_description.getText());
            p.setName(txt_name.getText());
            p.setAssistant_mail(txt_assistant.getText());
            p.setDeadline(deadline);
            try {
                p.setMark(Integer.parseInt(txt_mark.getText()));
            } catch (NumberFormatException e){
                message.open("<html>Only numbers from 0-5 as mark <br> 0 as not marked");
                return;
            }
            HibernateSupport.beginTransaction();
            p.save();
            if(user.getAssistant() != null)
                user.getAssistant().save();
            else
                assistants.get(cb_assistant.getSelectedIndex()).save();
            HibernateSupport.commitTransaction();
            dispose();
        }


    }

    private void onSaveMaster(){
        Date deadline;
        try {
            deadline = sdf.parse(txt_deadline.getText());
        } catch (ParseException e) {
            message.open(WRONG_DATE);
            return;
        }
        if(ifNew()){
            at.FH.Task.Master master = new at.FH.Task.Master(null, ta_description.getText(), txt_name.getText(),
                    txt_assistant.getText(), deadline);
            if(user.getRole() == Role.ASSISTANT){
                user.getAssistant().addMasters(master);
                master.setAssistant(user.getAssistant());
                HibernateSupport.beginTransaction();
                master.save();
                user.getAssistant().save();
                HibernateSupport.commitTransaction();
            }
            else if(user.getRole() == Role.ADMIN){
                HibernateSupport.beginTransaction();
                master.setAssistant(assistants.get(cb_assistant.getSelectedIndex()));
                master.save();
                assistants.get(cb_assistant.getSelectedIndex()).save();
                HibernateSupport.commitTransaction();
            }

        } else {
            m.setDescription(ta_description.getText());
            m.setName(txt_name.getText());
            m.setAssistant_mail(txt_assistant.getText());
            m.setDeadline(deadline);
            try {
                m.setMark(Integer.parseInt(txt_mark.getText()));
            } catch (NumberFormatException e){
                message.open("<html>Only numbers from 0-5 as mark <br> 0 as not marked");
                return;
            }
            HibernateSupport.beginTransaction();
            m.save();
            if(user.getAssistant() != null)
                user.getAssistant().save();
            else
                assistants.get(cb_assistant.getSelectedIndex()).save();
            HibernateSupport.commitTransaction();
            dispose();
        }
    }

    private void onSaveBachelor(){
        Date deadline;
        try {
            deadline = sdf.parse(txt_deadline.getText());
        } catch (ParseException e) {
            message.open(WRONG_DATE);
            return;
        }
        if(ifNew()){
            at.FH.Task.Bachelor bachelor = new at.FH.Task.Bachelor(null, ta_description.getText(), txt_name.getText(),
                    txt_assistant.getText(), deadline);
            if(user.getRole() == Role.ASSISTANT){
                user.getAssistant().addBachelor(bachelor);
                bachelor.setAssistant(user.getAssistant());
                HibernateSupport.beginTransaction();
                bachelor.save();
                user.getAssistant().save();
                HibernateSupport.commitTransaction();
            }
            else if(user.getRole() == Role.ADMIN){
                HibernateSupport.beginTransaction();
                bachelor.setAssistant(assistants.get(cb_assistant.getSelectedIndex()));
                bachelor.save();
                assistants.get(cb_assistant.getSelectedIndex()).save();
                HibernateSupport.commitTransaction();
            }
        } else {
            b.setDescription(ta_description.getText());
            b.setName(txt_name.getText());
            b.setDeadline(deadline);
            b.setAssistant_mail(txt_assistant.getText());
            try {
                b.setMark(Integer.parseInt(txt_mark.getText()));
            } catch (NumberFormatException e){
                message.open("<html>Only numbers from 0-5 as mark <br> 0 as not marked");
                return;
            }
            HibernateSupport.beginTransaction();
            b.save();
            if(user.getAssistant() != null)
                user.getAssistant().save();
            else
                assistants.get(cb_assistant.getSelectedIndex()).save();
            HibernateSupport.commitTransaction();
            dispose();
        }

    }


    /**
     * Close the project form
     */
    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * for new task
     * @param t Type of the task (Project, Bachelor or Master)
     * @param user The current user
     */
    public void open(at.FH.Task.Type t, LoggedInUser user){
        this.type = t;
        this.user = user;
        this.p = null;
        this.m = null;
        this.b = null;
        openPhaseTwo();
    }

    /**
     * open project details
     * @param p the Project
     * @param user The current user
     */
    public void open(at.FH.Task.Project p, LoggedInUser user){
        this.user = user;
        this.type = at.FH.Task.Type.PROJECT;
        this.p = p;
        setProjectDetails();
        openPhaseTwo();
    }

    /**
     * open bachelor thesis details
     * @param b the Bachelor thesis
     * @param user The current user
     */
    public void open(at.FH.Task.Bachelor b, LoggedInUser user){
        this.user = user;
        this.type = at.FH.Task.Type.BACHELOR;
        this.b = b;
        setBachelorDetails();
        openPhaseTwo();
    }

    /**
     * open master thesis details
     * @param m
     * @param user
     */
    public void open(at.FH.Task.Master m, LoggedInUser user){
        this.user = user;
        this.type = at.FH.Task.Type.MASTER;
        this.m = m;
        setMasterDetails();
        openPhaseTwo();
    }

    /**
     * initialize the user specific settings
     */
    private void openPhaseTwo(){

        switch (user.getRole()) {
            case ASSISTANT -> initializeAssistant();
            case STUDENT -> {
                lbl_student.setVisible(false);
                txt_student.setVisible(false);
            }
            case ADMIN ->{
                initializeAssistant();
                initializeAdmin();
            }
        }

        pack();
        set();
        setVisible(true);
    }

    /**
     * sets all necessary functions for the assistant in the form to visible and editable
     */
    private void initializeAssistant(){
        txt_assistant.setVisible(false);
        lbl_assistant.setVisible(false);
        txt_assistant.setEditable(false);
        if(user.getAssistant() != null)
            txt_assistant.setText(user.getAssistant().getEmail());
        txt_mark.setEditable(true);
        txt_name.setEditable(true);
        ta_description.setEditable(true);
        txt_deadline.setEditable(true);
        but_submit.setEnabled(true);
        lbl_student.setVisible(true);
        txt_student.setVisible(true);
    }

    /**
     * sets all necessary functions for the admin in the form
     * get all Assistants
     */
    private List<Assistant> assistants = new ArrayList<>();
    private void initializeAdmin(){
        txt_assistant.setVisible(true);
        lbl_assistant.setVisible(true);
        txt_assistant.setEditable(false);
        cb_assistant.setVisible(true);
        txt_student.setVisible(false);
        lbl_student.setVisible(false);
        List<Criterion> c = new ArrayList<>();
        assistants = HibernateSupport.readMoreObjects(Assistant.class, c);
        for(Assistant a : assistants)
            cb_assistant.addItem(a.toString());
    }

    /**
     * fill form with project information
     */
    private void setProjectDetails(){
        sb_description.setViewportView(ta_description);
        txt_name.setText(p.getName());
        if(p.getAssistant() != null)
            txt_assistant.setText(p.getAssistant().getEmail());
        txt_deadline.setText(p.getDeadline().toString());
        //TODO
        //but_deadline.setText(p.getDeadline().toString());
        if(p.getSubmit() != null){
            lbl_submit.setText(p.getSubmit().toString());
        }
        else
            lbl_submit.setText("not submitted");
        ta_description.setText(p.getDescription());
        if(p.getMark() != 0)
            txt_mark.setText(Integer.toString(p.getMark()));
        else
            txt_mark.setText("Ungraded");
        txt_student.setText(p.getEmail());

    }

    /**
     * fill form with bachelor information
     */
    private void setBachelorDetails(){
        sb_description.setViewportView(ta_description);
        txt_name.setText(b.getName());
        if(b.getAssistant() != null)
            txt_assistant.setText(b.getAssistant().getEmail());
        txt_deadline.setText(b.getDeadline().toString());
        //TODO
        //but_deadline.setText(p.getDeadline().toString());
        if(b.getSubmit() != null){
            lbl_submit.setText(b.getSubmit().toString());
        }
        else
            lbl_submit.setText("not submitted");
        ta_description.setText(b.getDescription());
        if(b.getMark() != 0)
            txt_mark.setText(Integer.toString(b.getMark()));
        else
            txt_mark.setText("Ungraded");
        txt_student.setText(b.getEmail());

    }

    /**
     * fill form with master information
     */
    private void setMasterDetails(){
        sb_description.setViewportView(ta_description);
        txt_name.setText(m.getName());
        if(m.getAssistant() != null)
            txt_assistant.setText(m.getAssistant().getEmail());
        txt_deadline.setText(m.getDeadline().toString());
        //TODO
        //but_deadline.setText(p.getDeadline().toString());
        if(m.getSubmit() != null){
            lbl_submit.setText(m.getSubmit().toString());
        }
        else
            lbl_submit.setText("not submitted");
        ta_description.setText(m.getDescription());
        if(m.getMark() != 0)
            txt_mark.setText(Integer.toString(m.getMark()));
        else
            txt_mark.setText("Ungraded");

        txt_student.setText(m.getEmail());
    }

    /**
     * set settings and design for form
     */
    private void set(){
        setTitle("Login");

        style.setMainPanel(contentPane);


        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width / 4,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height / 2);
        Dimension dim = new Dimension(450, 450);
        setMinimumSize(dim);

        setAlwaysOnTop(true);

        setResizable(false);
        setLocationRelativeTo(null);
    }
}

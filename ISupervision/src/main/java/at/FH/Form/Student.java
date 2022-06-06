package at.FH.Form;

import at.FH.Database.HibernateSupport;
import at.FH.General.Check;
import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;
import at.FH.User.Assistant;
import at.FH.User.LoggedInUser;
import at.FH.User.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Student extends JFrame {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JTextField txt_email;
    private JTextField txt_firstname;
    private JTextField txt_secondname;
    private JTextField txt_password1;
    private JButton but_update;
    private JCheckBox cb_change;
    private JTextField txt_password2;
    private JLabel lbl_firstAccess;
    private JLabel lbl_lastAccess;
    private JButton but_projectEnrol;
    private JList list_projects;
    private JTextArea ta_projectDescription;
    private JPanel p_personalInformation;
    private JPanel p_pDetails;
    private JLabel lbl_projectName;
    private JLabel lbl_projectAssistant;
    private JLabel lbl_projectDeadline;
    private JList list_profileProject;
    private JList list_profileBachelor;
    private JList list_profileMaster;
    private JScrollPane sp_profileProject;
    private JScrollPane sp_profileBachelor;
    private JScrollPane sp_profileMaster;
    private JScrollPane sp_projects;
    private JButton but_addProject;
    private JButton but_deleteProject;
    private JButton but_users;
    private JPanel p_bDetails;
    private JScrollPane sp_bachelor;
    private JList list_master;
    private JPanel p_mDetails;
    private JScrollPane sp_master;
    private JList list_bachelor;
    private JButton but_addBachelor;
    private JButton but_deleteBachelor;
    private JButton but_addMaster;
    private JButton but_deleteMaster;
    private JLabel lbl_bachelorName;
    private JLabel lbl_bachelorAssistant;
    private JLabel lbl_bachelorDeadline;
    private JTextArea ta_bachelorDescription;
    private JButton but_bachelorEnrol;
    private JLabel lbl_masterName;
    private JLabel lbl_masterAssistant;
    private JLabel lbl_masterDeadline;
    private JTextArea ta_masterDescription;
    private JButton but_masterEnrol;
    private JSpinner spinner_project;
    private JSpinner spinner_bachelor;
    private JSpinner spinner_master;
    private JLabel lbl_projectLimit;
    private JLabel lbl_bachelorLimit;
    private JLabel lbl_masterLimit;
    private static final Style style = new Style();
    private final Check check = new Check();
    private final Message message = new Message();
    private final DefaultListModel<at.FH.Task.Project> dlmProjects = new DefaultListModel<>();
    private final DefaultListModel<at.FH.Task.Bachelor> dlmBachelor= new DefaultListModel<>();
    private final DefaultListModel<at.FH.Task.Master> dlmMaster = new DefaultListModel<>();
    private final DefaultListModel dlmProfileProjects = new DefaultListModel();
    private final DefaultListModel dlmProfileBachelor = new DefaultListModel();
    private final DefaultListModel dlmProfileMaster = new DefaultListModel();
    private static final List<Criterion> criterions = new ArrayList<>();
    private List<Project> projectList = new ArrayList<>();
    private List<Master> masterList = new ArrayList<>();
    private List<Bachelor> bachelorList = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger();
    private static final at.FH.Form.Project project = new at.FH.Form.Project();

    private LoggedInUser user;


    /**
     * Set Listener and other standard properties
     */
    public Student(){
        but_update.setEnabled(false);
        txt_email.setEditable(false);
        criterions.add(Restrictions.isNull("email"));


        list_projects.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting())
            {
                setProjectDetails(list_projects.getSelectedIndex());
                p_pDetails.setVisible(true);
            }
        });

        list_bachelor.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting())
            {
                setBachelorDetails(list_bachelor.getSelectedIndex());
                p_bDetails.setVisible(true);
            }
        });

        list_master.addListSelectionListener(e -> {
            if(!e.getValueIsAdjusting())
            {
                setMasterDetails(list_master.getSelectedIndex());
                p_mDetails.setVisible(true);
            }
        });


        list_profileProject.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    openProjectDetails();
                }
            }
        });

        list_profileMaster.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    openMasterDetails();
                }
            }
        });

        list_profileBachelor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2) {
                    openBachelorDetails();
                }
            }
        });

        cb_change.addChangeListener(e -> but_update.setEnabled(cb_change.isSelected()));

        but_users.addActionListener(e ->{
            Members members = new Members();
            members.open();
        });

        but_update.addActionListener(e -> onUpdate());

        but_projectEnrol.addActionListener(e -> onEnrol(list_projects.getSelectedIndex(), at.FH.Task.Type.PROJECT, at.FH.Task.Project.class));
        but_masterEnrol.addActionListener(e -> onEnrol(list_master.getSelectedIndex(), at.FH.Task.Type.MASTER, at.FH.Task.Master.class));
        but_bachelorEnrol.addActionListener(e -> onEnrol(list_bachelor.getSelectedIndex(), at.FH.Task.Type.BACHELOR, at.FH.Task.Bachelor.class));

        but_addProject.addActionListener(e -> onAddProject());
        but_addMaster.addActionListener(e -> onAddMaster());
        but_addBachelor.addActionListener(e -> onAddBachelor());

        but_deleteProject.addActionListener(e -> {
            switch (user.getRole()){
                case ASSISTANT -> onDeleteProject();
                case ADMIN -> onDeleteProjectAdmin();
            }
        });
        but_deleteBachelor.addActionListener(e -> {
            switch (user.getRole()){
                case ASSISTANT -> onDeleteBachelor();
                case ADMIN -> onDeleteBachelorAdmin();
            }
        });
        but_deleteMaster.addActionListener(e -> {
            switch (user.getRole()){
                case ASSISTANT -> onDeleteMaster();
                case ADMIN -> onDeleteMasterAdmin();
            }
        });

    }

    /**
     * open form and set initialisation
     * @param user is the currently logged-in user
     */
    public void open(LoggedInUser user){
        this.user = user;

        switch (user.getRole()){
            case ADMIN: initializeAdmin();
            case ASSISTANT: initializeAssistant();
            case STUDENT: initialize();
        }

        add(contentPane);
        pack();
        set();
        setVisible(true);
        logger.info("Form successfully opened");
    }

    /**
     * reprint the form
     */
    private void initialize(){
        setUser();
        setProject();
        setMaster();
        setBachelor();
        p_pDetails.setVisible(false);
        p_bDetails.setVisible(false);
        p_mDetails.setVisible(false);
        repaint();
    }

    /**
     * sets all necessary functions for the assistant in the form to visible and editable
     */
    private void initializeAssistant(){
        but_addProject.setVisible(true);
        but_deleteProject.setVisible(true);
        but_addBachelor.setVisible(true);
        but_deleteBachelor.setVisible(true);
        but_addMaster.setVisible(true);
        but_deleteMaster.setVisible(true);
        but_bachelorEnrol.setVisible(false);
        but_projectEnrol.setVisible(false);
        but_masterEnrol.setVisible(false);
        if(user.getRole() == Role.ASSISTANT){
            spinner_bachelor.setVisible(true);
            spinner_project.setVisible(true);
            spinner_master.setVisible(true);
            lbl_bachelorLimit.setVisible(true);
            lbl_masterLimit.setVisible(true);
            lbl_projectLimit.setVisible(true);
        }
    }

    /**
     * sets all necessary functions for the admin in the form to visible and editable
     */
    private void initializeAdmin(){
        but_users.setVisible(true);
    }



    private void onDeleteProject(){
        if(user.getAssistant().getProjectByID(dlmProjects.getElementAt(list_projects.getSelectedIndex()).getTid()) != null){
            HibernateSupport.beginTransaction();
            user.getAssistant().removeProject(user.getAssistant().getProjectByID(dlmProjects.getElementAt(list_projects.getSelectedIndex()).getTid()));
            user.getAssistant().save();
            HibernateSupport.commitTransaction();
            dlmProjects.remove(list_projects.getSelectedIndex());
            setProject();
            setUser();
        }  else {
            message.open("<html> You can not delete this project.<br>It belongs to "+ dlmProjects.getElementAt(list_projects.getSelectedIndex()).getAssistant().getEmail());
        }

    }

    private void onDeleteBachelor(){
        if(user.getAssistant().getBachelorByID(dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getTid()) != null){
            HibernateSupport.beginTransaction();
            user.getAssistant().removeBachelor(user.getAssistant().getBachelorByID(dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getTid()));
            user.getAssistant().save();
            HibernateSupport.commitTransaction();
            dlmMaster.remove(list_master.getSelectedIndex());
            setBachelor();
            setUser();
        }  else {
            message.open("<html> You can not delete this bachelor thesis.<br>It belongs to "+ dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getAssistant().getEmail());
        }
    }

    private void onDeleteMaster(){
        if(user.getAssistant().getMasterByID(dlmMaster.getElementAt(list_master.getSelectedIndex()).getTid()) != null) {
            HibernateSupport.beginTransaction();
            user.getAssistant().removeMaster(user.getAssistant().getMasterByID(dlmMaster.getElementAt(list_master.getSelectedIndex()).getTid()));
            user.getAssistant().save();
            HibernateSupport.commitTransaction();
            dlmMaster.remove(list_master.getSelectedIndex());
            setMaster();
            setUser();
        }  else {
            message.open("<html> You can not delete this bachelor thesis.<br>It belongs to "+ dlmMaster.getElementAt(list_master.getSelectedIndex()).getAssistant().getEmail());
        }
    }


    private void onAddProject(){
        switch (user.getRole()){
            case ASSISTANT -> {
                if(user.getAssistant().getProjects().size() < user.getAssistant().getProjectLimit()){
                    at.FH.Form.Project project = new at.FH.Form.Project();
                    project.open(at.FH.Task.Type.PROJECT, user);
                    setProject();
                    setUser();
                }else{
                    message.open("You can accompany a maximum of " + user.getAssistant().getProjectLimit() + " projects");
                }
            }
            case ADMIN -> {
                at.FH.Form.Project project = new at.FH.Form.Project();
                project.open(at.FH.Task.Type.PROJECT, user);
                setProject();
                setUser();
            }
        }


    }

    private void onAddMaster(){
        switch (user.getRole()){
            case ASSISTANT -> {
                if(user.getAssistant().getMasters().size() < user.getAssistant().getMasterLimit()) {
                    at.FH.Form.Project project = new at.FH.Form.Project();
                    project.open(at.FH.Task.Type.MASTER, user);
                    setMaster();
                    setUser();
                }else{
                    message.open("You can accompany a maximum of " + user.getAssistant().getMasterLimit() + " master thesis");
                }
            }
            case ADMIN -> {
                at.FH.Form.Project project = new at.FH.Form.Project();
                project.open(at.FH.Task.Type.MASTER, user);
                setMaster();
                setUser();
            }
        }

    }

    private void onAddBachelor(){
        switch (user.getRole()){
            case ASSISTANT -> {
                if(user.getAssistant().getBachelors().size() < user.getAssistant().getBachelorLimit()){
                    at.FH.Form.Project project = new at.FH.Form.Project();
                    project.open(at.FH.Task.Type.BACHELOR, user);
                    setBachelor();
                    setUser();
                }else{
                    message.open("You can accompany a maximum of " + user.getAssistant().getBachelorLimit() + " bachelor thesis");
                }
            }
            case ADMIN -> {
                at.FH.Form.Project project = new at.FH.Form.Project();
                project.open(at.FH.Task.Type.BACHELOR, user);
                setBachelor();
                setUser();
            }
        }


    }


    private void onEnrol(int i, at.FH.Task.Type t, Class<?> c){
        switch (t){
            case PROJECT ->{
                if(check.checkPositiveProject(user)){
                    if(HibernateSupport.readOneObjectByID(c, projectList.get(i).getEmail()) == null){
                        projectList.get(i).setEmail(user.getStudent().getEmail());
                        user.getStudent().addProject(projectList.get(i));
                        HibernateSupport.beginTransaction();
                        projectList.get(i).save();
                        user.getStudent().save();
                        HibernateSupport.commitTransaction();
                        dlmProfileProjects.addElement(projectList.get(i));
                        dlmProjects.remove(i);
                        repaint();
                        message.open("Successfully enrolled. From now on you will find your project under your profile");
                    }else{
                        message.open("This project is no longer available.");
                    }
                }else {
                    message.open("You do not meet the requirements to enroll here");
                }
            }
            case MASTER ->{
                if(check.checkPositiveProject(user) && check.checkPositiveBachelor(user) && check.checkPositiveMaster(user)){
                    if(HibernateSupport.readOneObjectByID(c, masterList.get(i).getEmail()) == null){
                        masterList.get(i).setEmail(user.getStudent().getEmail());
                        user.getStudent().addMasters(masterList.get(i));
                        HibernateSupport.beginTransaction();
                        masterList.get(i).save();
                        user.getStudent().save();
                        HibernateSupport.commitTransaction();
                        dlmProfileMaster.addElement(masterList.get(i));
                        dlmMaster.remove(i);
                        repaint();
                        message.open("Successfully enrolled. From now on you will find your project under your profile");
                    }else{
                        message.open("This project is no longer available.");
                    }
                } else {
                    message.open("You do not meet the requirements to enroll here");
                }
            }
            case BACHELOR ->{
                if(check.checkPositiveProject(user) && check.checkPositiveBachelor(user)){
                    if(HibernateSupport.readOneObjectByID(c, bachelorList.get(i).getEmail()) == null){
                        bachelorList.get(i).setEmail(user.getStudent().getEmail());
                        user.getStudent().addBachelor(bachelorList.get(i));
                        HibernateSupport.beginTransaction();
                        bachelorList.get(i).save();
                        user.getStudent().save();
                        HibernateSupport.commitTransaction();
                        dlmProfileBachelor.addElement(bachelorList.get(i));
                        dlmBachelor.remove(i);
                        repaint();
                        message.open("Successfully enrolled. From now on you will find your project under your profile");
                    }else{
                        message.open("This project is no longer available.");
                    }
                } else {
                    message.open("You do not meet the requirements to enroll here");
                }
            }
        }
    }

    private void onUpdate(){

        cb_change.setSelected(false);

        for(Component c : p_personalInformation.getComponents())
            if(c instanceof JTextField textField){
                if(check.notEmpty(textField.getText())){
                    style.setTabbedPane(tabbedPane1);
                    return;
                }
            }

        if (!check.checkEmailRegex(txt_email.getText())){
            message.open("Wrong E-Mail regex");
            return;
        }


        if(!txt_password1.getText().equals(txt_password2.getText())){
            message.open("Password One and Two are not equals");
            return;
        }


        switch (user.getRole()){
            case STUDENT -> {
                user.getStudent().setFirstname(txt_firstname.getText());
                user.getStudent().setLastname(txt_secondname.getText());
                user.getStudent().setEmail(txt_email.getText());
                user.getStudent().setPassword(txt_password1.getText());
                HibernateSupport.beginTransaction();
                user.getStudent().save();
                HibernateSupport.commitTransaction();
                logger.info("Information from " + user.getStudent().getEmail() + " got updated");
            }
            case ASSISTANT -> {
                user.getAssistant().setFirstname(txt_firstname.getText());
                user.getAssistant().setLastname(txt_secondname.getText());
                user.getAssistant().setEmail(txt_email.getText());
                user.getAssistant().setPassword(txt_password1.getText());
                user.getAssistant().setProjectLimit((int)spinner_project.getValue());
                user.getAssistant().setMasterLimit((int)spinner_master.getValue());
                user.getAssistant().setBachelorLimit((int)spinner_bachelor.getValue());
                HibernateSupport.beginTransaction();
                user.getAssistant().save();
                HibernateSupport.commitTransaction();
                logger.info("Information from " + user.getAssistant().getEmail() + " got updated");
            }
            case ADMIN -> {
                user.getAdmin().setFirstname(txt_firstname.getText());
                user.getAdmin().setLastname(txt_secondname.getText());
                user.getAdmin().setEmail(txt_email.getText());
                user.getAdmin().setPassword(txt_password1.getText());
                HibernateSupport.beginTransaction();
                user.getAdmin().save();
                HibernateSupport.commitTransaction();
                logger.info("Information from " + user.getAdmin().getEmail() + " got updated");
            }
        }
        message.open("Successfully updated");
    }

    private void set(){
        setTitle("Form");

        setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width / 2,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height / 2);
        Dimension dim = new Dimension(550, 450);
        setMinimumSize(dim);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);

        style.setMainPanel(contentPane);
        style.setTabbedPane(tabbedPane1);
        logger.info("Form successfully settled");
    }

    /**
     * fill form with the information of the user
     */
    private void setUser(){
        dlmProfileProjects.removeAllElements();
        dlmProfileMaster.removeAllElements();
        dlmProfileBachelor.removeAllElements();
        switch (user.getRole()){
            case STUDENT -> {
                txt_firstname.setText(user.getStudent().getFirstname());
                txt_secondname.setText(user.getStudent().getLastname());
                txt_email.setText(user.getStudent().getEmail());
                lbl_lastAccess.setText(user.getStudent().getLastAccess().toString());
                lbl_firstAccess.setText(user.getStudent().getFirstAccess().toString());
                dlmProfileProjects.addAll(user.getStudent().getProjects());
                dlmProfileBachelor.addAll(user.getStudent().getBachelors());
                dlmProfileMaster.addAll(user.getStudent().getMasters());
            }
            case ASSISTANT -> {
                txt_firstname.setText(user.getAssistant().getFirstname());
                txt_secondname.setText(user.getAssistant().getLastname());
                txt_email.setText(user.getAssistant().getEmail());
                lbl_lastAccess.setText(user.getAssistant().getLastAccess().toString());
                lbl_firstAccess.setText(user.getAssistant().getFirstAccess().toString());
                spinner_master.setValue(user.getAssistant().getMasterLimit());
                spinner_project.setValue(user.getAssistant().getProjectLimit());
                spinner_bachelor.setValue(user.getAssistant().getBachelorLimit());
                dlmProfileProjects.addAll(user.getAssistant().getProjects());
                dlmProfileBachelor.addAll(user.getAssistant().getBachelors());
                dlmProfileMaster.addAll(user.getAssistant().getMasters());
            }
            case ADMIN -> {
                txt_firstname.setText(user.getAdmin().getFirstname());
                txt_secondname.setText(user.getAdmin().getLastname());
                txt_email.setText(user.getAdmin().getEmail());
                lbl_lastAccess.setText(user.getAdmin().getLastAccess().toString());
                lbl_firstAccess.setText(user.getAdmin().getFirstAccess().toString());
                dlmProfileProjects.addAll(user.getAdmin().getProjects());
                dlmProfileMaster.addAll(user.getAdmin().getMasters());
                dlmProfileBachelor.addAll(user.getAdmin().getBachelors());
            }
        }

        txt_password1.setText("Enter new password");
        txt_password2.setText("Repeat new password");

        sp_profileProject.setViewportView(list_profileProject);
        sp_profileMaster.setViewportView(list_profileMaster);
        sp_profileBachelor.setViewportView(list_profileBachelor);

        list_profileProject.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_profileProject.setModel(dlmProfileProjects);

        list_profileMaster.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_profileMaster.setModel(dlmProfileMaster);

        list_profileBachelor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_profileBachelor.setModel(dlmProfileBachelor);

    }


    private void setProject(){
        projectList = HibernateSupport.readMoreObjects(Project.class, criterions);

        sp_projects.setViewportView(list_projects);

        dlmProjects.removeAllElements();
        dlmProjects.addAll(projectList);
        list_projects.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_projects.setModel(dlmProjects);
    }

    private void setMaster(){
        masterList = HibernateSupport.readMoreObjects(Master.class, criterions);

        sp_master.setViewportView(list_master);
        dlmMaster.removeAllElements();
        dlmMaster.addAll(masterList);
        list_master.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_master.setModel(dlmMaster);
    }

    private void setBachelor(){
        bachelorList = HibernateSupport.readMoreObjects(Bachelor.class, criterions);

        sp_bachelor.setViewportView(list_bachelor);
        dlmBachelor.removeAllElements();
        dlmBachelor.addAll(bachelorList);
        list_bachelor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_bachelor.setModel(dlmBachelor);
    }

    /**
     * @param i the selected index in the Jlist
     */
    private void setProjectDetails(int i){
        lbl_projectName.setText(projectList.get(i).getName());
        lbl_projectAssistant.setText(projectList.get(i).getAssistant().getEmail());
        lbl_projectDeadline.setText(projectList.get(i).getDeadline().toString());
        ta_projectDescription.setText(projectList.get(i).getDescription());
    }

    /**
     * @param i the selected index in the Jlist
     */
    private void setBachelorDetails(int i){
        lbl_bachelorName.setText(bachelorList.get(i).getName());
        lbl_bachelorAssistant.setText(bachelorList.get(i).getAssistant().getEmail());
        lbl_bachelorDeadline.setText(bachelorList.get(i).getDeadline().toString());
        ta_bachelorDescription.setText(bachelorList.get(i).getDescription());
    }

    /**
     * @param i the selected index in the Jlist
     */
    private void setMasterDetails(int i){
        lbl_masterName.setText(masterList.get(i).getName());
        lbl_masterAssistant.setText(masterList.get(i).getAssistant().getEmail());
        lbl_masterDeadline.setText(masterList.get(i).getDeadline().toString());
        ta_masterDescription.setText(masterList.get(i).getDescription());
    }


    private void openProjectDetails(){
        switch (user.getRole()){
            case STUDENT -> project.open(user.getStudent().getProjects().get(list_profileProject.getSelectedIndex()), user);
            case ADMIN -> project.open(user.getAdmin().getProjects().get(list_profileProject.getSelectedIndex()), user);
            case ASSISTANT -> project.open(user.getAssistant().getProjects().get(list_profileProject.getSelectedIndex()), user);
        }
    }

    private void openBachelorDetails(){
        switch (user.getRole()){
            case STUDENT -> project.open(user.getStudent().getBachelors().get(list_profileBachelor.getSelectedIndex()), user);
            case ADMIN -> project.open(user.getAdmin().getBachelors().get(list_profileBachelor.getSelectedIndex()), user);
            case ASSISTANT -> project.open(user.getAssistant().getBachelors().get(list_profileBachelor.getSelectedIndex()), user);
        }
    }

    private void openMasterDetails(){
        switch (user.getRole()){
            case STUDENT -> project.open(user.getStudent().getMasters().get(list_profileMaster.getSelectedIndex()), user);
            case ADMIN -> project.open(user.getAdmin().getMasters().get(list_profileMaster.getSelectedIndex()), user);
            case ASSISTANT -> project.open(user.getAssistant().getMasters().get(list_profileMaster.getSelectedIndex()), user);
        }
    }

    /**
     * Delete all constraints from this task in the DB
     * deletion happens automatically due to hibernate settings
     */
    private void onDeleteProjectAdmin() {
        List<Criterion> c = new ArrayList<>();
        c.add(Restrictions.eq("assistant_mail", dlmProjects.getElementAt(list_projects.getSelectedIndex()).getAssistant_mail()));
        Assistant assistant;
        assistant = HibernateSupport.readOneObjectByID(Assistant.class, dlmProjects.getElementAt(list_projects.getSelectedIndex()).getAssistant_mail());
        List<at.FH.Task.Project> list;
        list = HibernateSupport.readMoreObjects(Project.class, c);

        HibernateSupport.beginTransaction();
        assistant.setProjects(list);

        assistant.getProjectByID(dlmProjects.getElementAt(list_projects.getSelectedIndex()).getTid()).save();
        assistant.getProjectByID(dlmProjects.getElementAt(list_projects.getSelectedIndex()).getTid()).delete();
        assistant.removeProject(assistant.getProjectByID(dlmProjects.getElementAt(list_projects.getSelectedIndex()).getTid()));
        assistant.save();
        HibernateSupport.commitTransaction();

        dlmProjects.remove(list_projects.getSelectedIndex());
        setProject();
        setUser();
    }

    private void onDeleteBachelorAdmin() {
        List<Criterion> c = new ArrayList<>();
        c.add(Restrictions.eq("assistant_mail", dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getAssistant_mail()));
        Assistant assistant;
        assistant = HibernateSupport.readOneObjectByID(Assistant.class, dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getAssistant_mail());
        List<Bachelor> list;
        list = HibernateSupport.readMoreObjects(Bachelor.class, c);

        HibernateSupport.beginTransaction();
        assistant.setBachelors(list);

        assistant.getBachelorByID(dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getTid()).save();
        assistant.getBachelorByID(dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getTid()).delete();
        assistant.removeBachelor(assistant.getBachelorByID(dlmBachelor.getElementAt(list_bachelor.getSelectedIndex()).getTid()));
        assistant.save();
        HibernateSupport.commitTransaction();

        dlmBachelor.remove(list_bachelor.getSelectedIndex());
        setBachelor();
        setUser();
    }

    private void onDeleteMasterAdmin() {
        List<Criterion> c = new ArrayList<>();
        c.add(Restrictions.eq("assistant_mail", dlmMaster.getElementAt(list_master.getSelectedIndex()).getAssistant_mail()));
        Assistant assistant;
        assistant = HibernateSupport.readOneObjectByID(Assistant.class, dlmMaster.getElementAt(list_master.getSelectedIndex()).getAssistant_mail());
        List<Master> list;
        list = HibernateSupport.readMoreObjects(Master.class, c);

        HibernateSupport.beginTransaction();
        assistant.setMasters(list);

        assistant.getMasterByID(dlmMaster.getElementAt(list_master.getSelectedIndex()).getTid()).save();
        assistant.getMasterByID(dlmMaster.getElementAt(list_master.getSelectedIndex()).getTid()).delete();
        assistant.removeMaster(assistant.getMasterByID(dlmMaster.getElementAt(list_master.getSelectedIndex()).getTid()));
        assistant.save();
        HibernateSupport.commitTransaction();

        dlmMaster.remove(list_master.getSelectedIndex());
        setMaster();
        setUser();
    }
}

package at.FH.Form;

import at.FH.Database.HibernateSupport;
import at.FH.User.Admin;
import at.FH.User.Assistant;
import at.FH.User.Registration;
import at.FH.User.Student;
import org.hibernate.criterion.Criterion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Members extends JDialog {
    private JPanel contentPane;
    private JButton but_cancle;
    private JTable table_members;
    private JScrollPane sp_table;
    private JRadioButton rb_admin;
    private JRadioButton rb_assistant;
    private JRadioButton rb_student;
    private JButton but_refresh;
    private static final Style style = new Style();
    private DefaultTableModel dtm = new DefaultTableModel();
    private List<Registration> registrationList = new ArrayList<>();

    /**
     * Constructor and listeners
     */
    public Members() {
        setContentPane(contentPane);
        setModal(true);
        sp_table.setViewportView(table_members);

        but_cancle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        but_refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                Members m = new Members();
                m.open();
            }
        });

        table_members.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                e.getSource();
                Point point = e.getPoint();
                int row = table_members.rowAtPoint(point);
                if(e.getClickCount() == 2 && table_members.getSelectedRow() != -1){
                    newMember(table_members.getSelectedRow());
                }
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

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    /**
     * open the members form
     */
    public void open(){
        pack();
        set();
        setTableModelRegistration();
        setVisible(true);
    }

    /**
     * set all properties of the members form
     */
    private void set(){
        setTitle("Members");

        setAlwaysOnTop(true);

        style.setMainPanel(contentPane);
        Dimension minSize = new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width / 4,
                GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height / 2);
        Dimension dim = new Dimension(850, 750);
        setSize(dim);
        setMinimumSize(minSize);

        setAlwaysOnTop(true);

        setResizable(true);
        setLocationRelativeTo(null);
    }

    /**
     * ColumnNames
     */
    private void setTableModelRegistration() {
        dtm.addColumn("E-Mail");
        dtm.addColumn("Firstname");
        dtm.addColumn("Lastname");
        dtm.addColumn("Message");
        dtm.addColumn("Problem");
        initialiseNewMember();
        table_members.setModel(dtm);
    }

    /**
     * insert new members (unverified) into the table
     */
    private void initialiseNewMember(){
        List<Criterion> c = new ArrayList<>();
        registrationList = HibernateSupport.readMoreObjects(Registration.class, c);
        for(Registration reg : registrationList){
            Object[] row = {reg.getEmail(), reg.getFirstname(), reg.getSecondname(), reg.getMessage(), reg.getProblem()};
            dtm.addRow(row);
        }
    }

    /**
     *
     * @param i The row in which the member is to be created
     */
    private void newMember(int i){
        Registration reg = registrationList.get(i);
        if(rb_admin.isSelected()){
            Admin admin = new Admin(reg.getEmail(), reg.getFirstname(), reg.getPassword(), reg.getSecondname(), new Date());
            HibernateSupport.beginTransaction();
            admin.save();
            reg.delete();
            HibernateSupport.commitTransaction();
        } else if(rb_assistant.isSelected()){
            Assistant assistant = new Assistant(reg.getEmail(), reg.getFirstname(), reg.getPassword(), reg.getSecondname(), new Date());
            HibernateSupport.beginTransaction();
            assistant.save();
            reg.delete();
            HibernateSupport.commitTransaction();
        }else if(rb_student.isSelected()){
            Student student = new Student(reg.getEmail(), reg.getFirstname(), reg.getPassword(), reg.getSecondname(), new Date());
            HibernateSupport.beginTransaction();
            student.save();
            reg.delete();
            HibernateSupport.commitTransaction();
        }
    }
}

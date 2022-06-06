package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoggedInUser{


    private Admin admin;
    private Student student;
    private Assistant assistant;
    private static final List<Criterion> criterions = new ArrayList<>();
    private final Role role;


    public LoggedInUser(Admin admin){
        this.admin = admin;
        role = Role.ADMIN;
        criterions.add(Restrictions.isNotNull("email"));
        this.admin.setProjects(HibernateSupport.readMoreObjects(Project.class, criterions));
        this.admin.setBachelors(HibernateSupport.readMoreObjects(Bachelor.class, criterions));
        this.admin.setMasters(HibernateSupport.readMoreObjects(Master.class, criterions));

        at.FH.Form.Student form = new at.FH.Form.Student();
        form.open(this);

        this.admin.setLastAccess(new Date());
        HibernateSupport.beginTransaction();
        this.admin.save();
        HibernateSupport.commitTransaction();

    }

    public LoggedInUser(Student student){
        this.student = student;
        role = Role.STUDENT;

        criterions.add(Restrictions.eq("email", this.student.getEmail()));
        this.student.setProjects(HibernateSupport.readMoreObjects(Project.class, criterions));
        this.student.setBachelors(HibernateSupport.readMoreObjects(Bachelor.class, criterions));
        this.student.setMasters(HibernateSupport.readMoreObjects(Master.class, criterions));

        at.FH.Form.Student form = new at.FH.Form.Student();
        form.open(this);

        this.student.setLastAccess(new Date());
        HibernateSupport.beginTransaction();
        this.student.save();
        HibernateSupport.commitTransaction();
    }

    public LoggedInUser(Assistant assistant){
        this.assistant = assistant;
        role = Role.ASSISTANT;

        criterions.add(Restrictions.eq("assistant_mail", this.assistant.getEmail()));
        this.assistant.setProjects(HibernateSupport.readMoreObjects(Project.class, criterions));
        this.assistant.setBachelors(HibernateSupport.readMoreObjects(Bachelor.class, criterions));
        this.assistant.setMasters(HibernateSupport.readMoreObjects(Master.class, criterions));

        at.FH.Form.Student form = new at.FH.Form.Student();
        form.open(this);

        this.assistant.setLastAccess(new Date());
        HibernateSupport.beginTransaction();
        this.assistant.save();
        HibernateSupport.commitTransaction();

    }


    public Student getStudent() {
        return student;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public Admin getAdmin() {
        return admin;
    }

    public Role getRole() {
        return role;
    }

}

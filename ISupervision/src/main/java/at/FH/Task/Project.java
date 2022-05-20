package at.FH.Task;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;
import at.FH.User.Role;
import at.FH.User.Student;
import jakarta.transaction.Transactional;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "project")
public class Project extends Task implements ISaveAndDelete {

    @ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
    private Student student;

    @SuppressWarnings("unused")
    public Project(){

    }

    public Project(String email, String description){
        super(email, description);
    }

    @Override
    public boolean save() {
        if(!HibernateSupport.commit(this))
            return false;
        return true;
    }

    @Override
    public void delete() {
        HibernateSupport.deleteObject(this);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

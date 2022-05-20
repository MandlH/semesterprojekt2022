package at.FH.Task;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;
import at.FH.User.Student;

import javax.persistence.*;

@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Task implements ISaveAndDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int tid;


    private String email;

    private String description;

    public Task(){

    }

    public Task(String email, String description){
        this.email = email;
        this.description = description;
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

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

package at.FH.Task;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "task")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Task implements ISaveAndDelete {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int tid;

    private String email;
    private String description;
    private String name;
    private Date submit;
    private Date deadline;
    private int mark;



    public Task(){

    }

    public Task(String email, String description, String name, Date deadline){
        this.email = email;
        this.description = description;
        this.name = name;
        this.deadline = deadline;
    }

    @Override
    public boolean save() {
        return HibernateSupport.commit(this);
    }

    @Override
    public void delete() {
        HibernateSupport.deleteObject(this);
    }

    @Override
    public String toString() {
        return getName() + " Student: " + getEmail();
    }

    public int getTid() {
        return tid;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getSubmit() {
        if(submit != null){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            return sdf.format(submit);
        } else
            return "no submit";

    }

    public void setSubmit(Date submit) {
        this.submit = submit;
    }
}

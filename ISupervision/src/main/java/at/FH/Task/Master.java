package at.FH.Task;

import at.FH.User.Assistant;
import at.FH.User.Student;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "master")
public class Master extends Task{

    @ManyToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
    private Student student;

    private String assistant_mail;

    @ManyToOne(optional=false)
    @JoinColumn(name = "assistant_mail", insertable = false, updatable = false)
    private Assistant assistant;

    @SuppressWarnings("unused")
    public Master(){

    }

    public Master(String email, String description, String name, String assistant_mail, Date deadline){
        super(email, description, name, deadline);
        this.assistant_mail = assistant_mail;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public String getAssistant_mail() {
        return assistant_mail;
    }

    public void setAssistant_mail(String assistant_mail) {
        this.assistant_mail = assistant_mail;
    }

    public Assistant getAssistant() {
        return assistant;
    }

    public void setAssistant(Assistant assistant) {
        this.assistant = assistant;
    }
}

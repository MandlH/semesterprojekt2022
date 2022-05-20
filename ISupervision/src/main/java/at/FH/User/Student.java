package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ILoggedIn;
import at.FH.General.ISaveAndDelete;
import at.FH.Task.Project;
import com.mchange.util.StringObjectMap;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.transaction.Transactional;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "student")
public class Student extends User implements ISaveAndDelete, ILoggedIn {

    @SuppressWarnings("unused")
    public Student(){

    }

    @Enumerated(EnumType.STRING)
    private Role role;

    private int matriculationNumber;
    private Date vintage;
    private String Course;

    @OneToMany
    @JoinColumn(name = "email")
    private List<Project> projects;


    public Student(String email, String firstname, String password, String secondname){
        super(email, firstname, password, secondname);
        this.role = Role.STUDENT;
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

    @Override
    public String show() {
        return getFirstname();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getMatrikelnumber() {
        return matriculationNumber;
    }

    public void setMatrikelnumber(int matrikelnumber) {
        this.matriculationNumber = matrikelnumber;
    }

    public Date getVintage() {
        return vintage;
    }

    public void setVintage(Date vintage) {
        this.vintage = vintage;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public int getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(int matriculationNumber) {
        this.matriculationNumber = matriculationNumber;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}

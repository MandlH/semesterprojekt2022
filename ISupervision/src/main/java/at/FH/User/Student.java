package at.FH.User;

import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "student")
public class Student extends User {

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Master> masters = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Bachelor> bachelors = new ArrayList<>();


    @SuppressWarnings("unused")
    public Student(){

    }

    private Date vintage;

    public Student(String email, String firstname, String password, String lastname, Date firstAccess){
        super(email, firstname, password, lastname, firstAccess);
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project){
        this.projects.add(project);
    }

    public List<Master> getMasters() {
        return masters;
    }

    public void setMasters(List<Master> masters) {
        this.masters = masters;
    }

    public void addMasters(Master master) {
        this.masters.add(master);
    }

    public List<Bachelor> getBachelors() {
        return bachelors;
    }

    public void setBachelors(List<Bachelor> bachelors) {
        this.bachelors = bachelors;
    }

    public void addBachelor(Bachelor bachelor) {
        this.bachelors.add(bachelor);
    }

    public List<Project> getProjects() {
        return projects;
    }

}

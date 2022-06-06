package at.FH.User;

import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "admin")
public class Admin extends User {

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Project> projects = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Master> masters = new ArrayList<>();

    @OneToMany(cascade = { CascadeType.ALL }, orphanRemoval = true)
    private List<Bachelor> bachelors = new ArrayList<>();


    @SuppressWarnings("unused")
    public Admin(){

    }

    public Admin(String email, String firstname, String password, String lastname, Date firstAccess){
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


    public void removeProject(Project p){
        projects.remove(p);
        p.setAssistant(null);
        p.setStudent(null);
    }

    public void removeBachelor(Bachelor b){
        bachelors.remove(b);
        b.setAssistant(null);
        b.setStudent(null);
    }

    public void removeMaster(Master m){
        masters.remove(m);
        m.setAssistant(null);
        m.setStudent(null);
    }

    public Bachelor getBachelorByID(int id){
        for(Bachelor b : bachelors){
            if(id == b.getTid())
                return b;
        }
        return null;
    }

    public Master getMasterByID(int id){
        for(Master m : masters){
            if(id == m.getTid())
                return m;
        }
        return null;
    }

    public Project getProjectByID(int id){
        for(Project b : projects){
            if(id == b.getTid())
                return b;
        }
        return null;
    }


}

package at.FH.User;

import at.FH.Database.HibernateSupport;
import at.FH.General.ISaveAndDelete;
import at.FH.Task.Bachelor;
import at.FH.Task.Master;
import at.FH.Task.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User implements ISaveAndDelete {

    @Id
    private String email;

    private String firstname, lastname, password;
    private Date firstAccess, lastAccess;



    public User(){

    }

    public User(String email, String firstname, String password, String lastname, Date firstAccess){
        this.email = email;
        this.firstname = firstname;
        this.password = password;
        this.lastname = lastname;
        this.lastAccess = new Date();
        this.firstAccess = firstAccess;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getFirstAccess() {
        return firstAccess;
    }

    public void setFirstAccess(Date firstAccess) {
        this.firstAccess = firstAccess;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }


}
